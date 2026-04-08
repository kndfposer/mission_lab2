package org.example.ui;

import org.example.report.*;

import java.util.Scanner;

public class ReportTypeChooser {
    private final Scanner scanner;

    public ReportTypeChooser(Scanner scanner) {
        this.scanner = scanner;
    }

    public MissionReportStrategy chooseStrategy() {
        System.out.println("Выберите тип отчёта:");
        System.out.println("1 - краткий");
        System.out.println("2 - детализированный");
        System.out.println("3 - отчёт по рискам");
        System.out.println("4 - статистический");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine().trim();
        return switch (choice) {
            case "2" -> new DetailedMissionReportStrategy();
            case "3" -> new RiskMissionReportStrategy();
            case "4" -> new StatisticsMissionReportStrategy();
            default -> new SummaryMissionReportStrategy();
        };
    }
}
