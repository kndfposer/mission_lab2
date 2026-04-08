package org.example.app;

import org.example.loader.DefaultMissionLoaderRegistryFactory;
import org.example.loader.MissionLoaderRegistry;
import org.example.model.Mission;
import org.example.report.MissionReportStrategy;
import org.example.report.SummaryMissionReportStrategy;
import org.example.ui.ConsoleFileChooser;
import org.example.validator.MissionValidator;

import java.io.File;
import java.util.Scanner;

public class MissionManager {
    private final Scanner scanner = new Scanner(System.in);
    private final MissionLoaderRegistry loaderRegistry = DefaultMissionLoaderRegistryFactory.createDefault();
    private final MissionValidator validator = new MissionValidator();
    private final MissionReportStrategy reportStrategy = new SummaryMissionReportStrategy();

    public void start() {
        System.out.println("Введите 0 вместо пути, чтобы выйти");

        while (true) {
            File file = ConsoleFileChooser.choose(scanner);
            if (file == null) {
                System.out.println("ПРОГРАММА ЗАВЕРШЕНА");
                return;
            }

            try {
                Mission mission = loaderRegistry.load(file);
                validator.validate(mission);

                System.out.println();
                System.out.println(reportStrategy.generate(mission));
                System.out.println();

            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println();
            }
        }
    }
}