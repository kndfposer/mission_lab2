package org.example.app;


import org.example.factory.MissionLoaderFactory;
import org.example.loader.MissionLoader;
import org.example.model.Mission;
import org.example.report.MissionReportStrategy;
import org.example.ui.FileChooser;
import org.example.ui.ReportTypeChooser;

import java.io.File;
import java.util.Scanner;

public class MissionManager {
    private final Scanner scanner = new Scanner(System.in);
    private final MissionLoaderFactory loaderFactory = new MissionLoaderFactory();

    public void start() {
        System.out.println("Для выхода введите 0 вместо пути к файлу.");

        while (true) {
            FileChooser fileChooser = new FileChooser(scanner);
            File file = fileChooser.chooseFile();
            if (file == null) {
                System.out.println("ПРОГРАММА ЗАВЕРШЕНА");
                return;
            }

            if (!file.exists()) {
                System.out.println("Файл не найден.");
                continue;
            }

            try {
                MissionLoader loader = loaderFactory.getLoader(file);
                Mission mission = loader.load(file);
                MissionReportStrategy strategy = new ReportTypeChooser(scanner).chooseStrategy();
                System.out.println();
                System.out.println(strategy.createReport(mission));
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            System.out.print("Нажмите Enter для продолжения или 0 для выхода: ");
            if ("0".equals(scanner.nextLine().trim())) {
                System.out.println("ПРОГРАММА ЗАВЕРШЕНА");
                return;
            }
        }
    }
}
