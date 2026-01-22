package org.example.init;

import org.example.domain.Board;
import org.example.service.ConsoleService;

public class ConsoleBoardInit implements BoardInit {
    private final int boardInitType = 1;
    private final ConsoleService consoleService;

    public ConsoleBoardInit(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @Override
    public int getBoardInitType() {
        return boardInitType;
    }

    @Override
    public Board readBoardDetails() {
        int row = 0;
        int col = 0;
        boolean valid = false;

        while (!valid) {
            try {
                row = consoleService.readIntFromConsole("Add meg a pálya sorainak számát (4-25): ");
                col = consoleService.readIntFromConsole("Add meg a pálya oszlopainak számát (4-25): ");

                if (row < 4 || row > 25 || col < 4 || col > 25) {
                    throw new IllegalArgumentException("A soroknak és oszlopoknak 4-25 között kell lennie!");
                }

                valid = true;

            } catch (IllegalArgumentException e) {
                consoleService.print(e.getMessage());
            }
        }

        Board board = new Board(row, col);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    board.setCell(i, j, '~');
                }
            }

        board.placeSymbol((row - 1) / 2, (col - 1) / 2, 'X');
        return board;
    }
}
