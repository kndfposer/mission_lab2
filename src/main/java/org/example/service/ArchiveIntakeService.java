package org.example.service;

import org.example.model.Mission;
import org.example.porter.DefHandlerRegistryFactory;
import org.example.porter.MissionHandlerRegistry;
import org.example.porter.fabric_p.MissionFormatSniffer;
import org.example.validator.Validator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ArchiveIntakeService {
    private final MissionHandlerRegistry registry = DefHandlerRegistryFactory.createDefault();
    private final Validator validator = new Validator();

    public IntakeBundle parseAndCheck(MultipartFile multipartFile) throws IOException {
        File temp = Files.createTempFile("mission-upload-", ".tmp").toFile();
        multipartFile.transferTo(temp);

        try {
            String format = MissionFormatSniffer.detect(temp);
            if ("unknown".equals(format)) {
                throw new IllegalArgumentException("Формат файла не распознан");
            }

            Mission mission;
            try {
                mission = registry.load(temp);
            } catch (IOException e) {
                throw new IllegalArgumentException("Не удалось прочитать или распарсить файл");
            }

            validator.validate(mission);
            return new IntakeBundle(mission, format);
        } finally {
            temp.delete();
        }
    }

    public record IntakeBundle(Mission mission, String format) {}
}
