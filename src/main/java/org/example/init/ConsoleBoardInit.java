package org.example.init;

import org.example.domain.Board;
import org.example.service.ConsoleService;

public class ConsoleBoardInit implements BoardInit{
    private final ConsoleService consoleService;

    public ConsoleBoardInit(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @Override
    public Board readBoardDetails() {
        final int row=consoleService.readIntFromConsole("Please provide how many rows you want for a map (4-25): ");
        final int col=consoleService.readIntFromConsole("Please provide how many coulms you want for a map (4-25): ");
        return new Board(row,col);
    }
}
