package org.example.manager;

import org.example.porter.DefHandlerRegistryFactory;
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
    private final MissionHandlerRegistry loaderRegistry = DefHandlerRegistryFactory.createDefault();
    private final Validator validator = new Validator();
    private final Strategy_p reportStrategy = new SummaryStrategyP();

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