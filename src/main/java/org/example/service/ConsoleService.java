package org.example.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleService.class);
    private final Scanner scanner;

    public ConsoleService(final Scanner scanner) {
        this.scanner = scanner;
    }

    public int readIntFromConsole(final String message) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            try {
                print(message);
                value = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                print("Érvénytelen bevitel! Csak számot adjon meg.");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }

        return value;
    }

    public String readStringFromConsole(final String message) {
        LOGGER.info(message);
        return scanner.next();
    }

    public void print(final String message) {
        LOGGER.info(message);
    }

}