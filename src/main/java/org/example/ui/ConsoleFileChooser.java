package org.example.ui;

import java.io.File;
import java.util.Scanner;

public final class ConsoleFileChooser {
    private ConsoleFileChooser() {}

    public static File choose(Scanner scanner) {
        System.out.print("Введите путь к файлу миссии: ");
        String path = scanner.nextLine().trim();
        if ("0".equals(path)) {
            return null;
        }
        return new File(path);
    }
}
