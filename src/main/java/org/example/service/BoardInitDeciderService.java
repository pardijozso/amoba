package org.example.service;

import org.example.init.BoardInit;
import org.example.init.ConsoleBoardInit;
import org.example.init.FileBoardInit;

public class BoardInitDeciderService {
    private final ConsoleService consoleService;

    public BoardInitDeciderService(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public BoardInit getMapInitInstance() {
        int option = 0;
        boolean valid = false;

        while (!valid) {
            try {
                option = consoleService.readIntFromConsole(
                        "Manuális pálya létrehozás (1), mentett játék betöltése (2): ");

                if (option != 1 && option != 2) {
                    throw new IllegalArgumentException("Csak 1 vagy 2 választható!");
                }

                valid = true;
            } catch (IllegalArgumentException e) {
                consoleService.print(e.getMessage());
            }
        }

        return switch (option) {
            case 1 -> new ConsoleBoardInit(consoleService);
            case 2 -> new FileBoardInit(consoleService);
            default -> throw new IllegalStateException();
        };
    }
}
