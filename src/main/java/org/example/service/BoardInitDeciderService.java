package org.example.service;

import org.example.init.BoardInit;
import org.example.init.ConsoleBoardInit;

public class BoardInitDeciderService {
    private final ConsoleService consoleService;

    public BoardInitDeciderService(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public BoardInit getMapInitInstance() {
        final int option = consoleService.readIntFromConsole("Please provide number '1' for manual setup or '2' for loading from a file.");

        return switch (option) {
            case 1 -> new ConsoleBoardInit(consoleService);
            //case 2 -> new FileMapInit();
            default -> new ConsoleBoardInit(consoleService);
        };
    }
}
