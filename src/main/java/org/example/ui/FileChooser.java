package org.example.ui;

import java.io.File;
import java.util.Scanner;

public class FileChooser {
    private final Scanner scanner;

    public FileChooser(Scanner scanner) {
        this.scanner = scanner;
    }

    public File chooseFile() {
        System.out.print("Введите путь к файлу миссии: ");
        String path = scanner.nextLine().trim();
        if ("0".equals(path)) {
            return null;
        }
        return new File(path);
    }
}
