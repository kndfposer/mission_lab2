package org.example.manager;

import org.example.porter.DefHandlerRegistryFactory;
import org.example.porter.handler_interface.MissionLoader;
import org.example.porter.MissionHandlerRegistry;
import org.example.model.Mission;
import org.example.report_format.Strategy_p;
import org.example.report_format.SummaryStrategyP;
import org.example.console.ConsoleFileChooser;
import org.example.validator.Validator;

import java.io.File;
import java.util.Scanner;

public class MissionManager {
    private final Scanner scanner = new Scanner(System.in);
    private final MissionHandlerRegistry registry = DefHandlerRegistryFactory.createDefault();
    private final Validator validator = new Validator();
    private Strategy_p reportStrategy = new SummaryStrategyP();

    public void start() {
        System.out.println("Для выхода введите 0 вместо пути к файлу");
        System.out.println();

        while (true) {
            File file = ConsoleFileChooser.choose(scanner);
            if (file == null) {
                System.out.println("ПРОГРАММА ЗАВЕРШЕНА");
                return;
            }

            try {
                String detectedFormat = detectFileFormat(file);
                System.out.println();
                System.out.println("____________________________________________________________");
                System.out.println(" Файл: " + file.getName());
                System.out.println(" Определённый формат: " + detectedFormat.toUpperCase());
                System.out.println("____________________________________________________________");
                System.out.println();

                Mission mission = registry.load(file);
                validator.validate(mission);
                System.out.println(reportStrategy.generate(mission));
                System.out.println();

            } catch (Exception e) {
                System.out.println();
                System.out.println("ОШИБКА: " + e.getMessage());
                System.out.println();
            }
        }
    }


    private String detectFileFormat(File file) {
        String fileName = file.getName().toLowerCase();
        if (fileName.endsWith(".json")) return "json";
        if (fileName.endsWith(".xml")) return "xml";
        if (fileName.endsWith(".yaml") || fileName.endsWith(".yml")) return "yaml";
        if (fileName.endsWith(".txt")) return "txt";

        try {
            String firstLine = org.example.porter.fabric_p.FileContentUtils.firstNonBlankLine(file);
            if (firstLine.startsWith("{")) return "json";
            if (firstLine.startsWith("<")) return "xml";
            if (firstLine.startsWith("[MISSION]")) return "txt";
            if (firstLine.startsWith("MISSION_CREATED|") || firstLine.startsWith("CURSE_DETECTED|")) {
                return "type";
            }
            String content = org.example.porter.fabric_p.FileContentUtils.readAll(file);
            if (content.contains("missionId:") && content.contains("curse:")) {
                return "yaml";
            }
        } catch (Exception e) {
            return "неизвестный";
        }

        return "неизвестный";
    }

    public void setReportStrategy(Strategy_p strategy) {
        this.reportStrategy = strategy;
    }
}