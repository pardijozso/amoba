package org.example.service;

import java.util.Random;

import org.example.domain.Board;
import org.example.domain.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SteppingService {

    private final ConsoleService consoleService;
    private final Random random = new Random();
    private final GameSaverService saver = new GameSaverService();

    public SteppingService(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    /**
     * Bekéri a felhasználó lépését loggerrel
     */
    public Move getMoveDetails(Board board) {
        int rowIndex = -1;
        int colIndex = -1;

        boolean valid = false;

        while (!valid) {
            try {
                String colInput = consoleService.readStringFromConsole(
                        "Add meg az oszlopot (A-" + (char) ('A' + board.getCol() - 1) + ") vagy ESC a kilépéshez:");

                if (colInput.equalsIgnoreCase("ESC")) {
                    consoleService.print("Kilépés a játékból...");
                    saver.saveToFile(board);
                    System.exit(0);
                }

                colIndex = columnLetterToIndex(colInput, board.getCol());
                String rowInputStr = consoleService.readStringFromConsole(
                        "Add meg a sort (1-" + board.getRow() + ") vagy ESC a kilépéshez:");

                if (rowInputStr.equalsIgnoreCase("ESC")) {
                    consoleService.print("Kilépés a játékból...");
                    saver.saveToFile(board);
                    System.exit(0);
                }

                rowIndex = Integer.parseInt(rowInputStr) - 1;

                if (rowIndex < 0 || rowIndex >= board.getRow()) {
                    consoleService.print("Érvénytelen sor! 1 és " + board.getRow() + " között kell lennie.");
                    continue;
                }

                if (board.getCell(rowIndex, colIndex) != '~') {
                    throw new IllegalArgumentException("Ez a mező foglalt");
                }

                if (!board.isValidMove(rowIndex, colIndex)) {
                    throw new IllegalStateException("Érvénytelen lépés!");
                }

                valid = true;

            } catch (NumberFormatException e) {
                consoleService.print("Érvénytelen szám! Próbáld újra.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                consoleService.print(e.getMessage());
            }
        }

        return new Move(rowIndex, colIndex);
    }

    public Move calculateBotMove(Board board) {
        int row;
        int col;
        int maxRow = board.getRow();
        int maxCol = board.getCol();

        while (true) {
            row = random.nextInt(maxRow);
            col = random.nextInt(maxCol);

            if (board.getCell(row, col) == '~' && board.isValidMove(row, col)) {
                consoleService.print("Bot lépése: " + (char) ('A' + col) + (row + 1));
                return new Move(row, col);
            }
        }
    }

    /**
     * Betűből 0-index
     */
    private int columnLetterToIndex(String input, int maxCols) {
        input = input.toUpperCase();
        char letter = input.charAt(0);
        int index = letter - 'A';
        if (index < 0 || index >= maxCols) {
            throw new IllegalArgumentException("Érvénytelen oszlop: " + input);
        }
        return index;
    }
}