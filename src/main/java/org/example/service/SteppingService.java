package org.example.service;

import org.example.domain.Board;
import org.example.domain.Move;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SteppingService {

    private static final Logger logger = LoggerFactory.getLogger(SteppingService.class);

    private final ConsoleService consoleService;
    private final Random random = new Random();
    private boolean isCorrectMove=false;

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

        while (!valid) { // normál feltétel, nem while(true)
            try {
                // --- OSZLOP BEKÉRÉSE ---
                String colInput = consoleService.readStringFromConsole(
                        "Add meg az oszlopot (A-" + (char) ('A' + board.getCol() - 1) + ") vagy ESC a kilépéshez:");

                if (colInput.equalsIgnoreCase("ESC")) {
                    logger.info("Kilépés a játékból...");
                    System.exit(0);
                }

                colIndex = columnLetterToIndex(colInput, board.getCol());

                // --- SOR BEKÉRÉSE ---
                String rowInputStr = consoleService.readStringFromConsole(
                        "Add meg a sort (1-" + board.getRow() + ") vagy ESC a kilépéshez:");

                if (rowInputStr.equalsIgnoreCase("ESC")) {
                    logger.info("Kilépés a játékból...");
                    System.exit(0);
                }

                rowIndex = Integer.parseInt(rowInputStr) - 1;

                // Sor validáció
                if (rowIndex < 0 || rowIndex >= board.getRow()) {
                    logger.warn("Érvénytelen sor! 1 és " + board.getRow() + " között kell lennie.");
                    continue; // vissza a ciklus elejére
                }

                // Ha minden ok, állítsuk a flag-et
                valid = true;

            } catch (NumberFormatException e) {
                logger.warn("Érvénytelen szám! Próbáld újra.");
            } catch (IllegalArgumentException e) {
                logger.warn(e.getMessage());
            }
        }

        return new Move(rowIndex, colIndex);
    }

    /**
     * Random bot lépés
     */
    public Move calculateBotMove(Board board) {
        int row, col;
        int maxRow = board.getRow();
        int maxCol = board.getCol();

        while (true) {
            row = random.nextInt(maxRow);  // 0-index
            col = random.nextInt(maxCol);  // 0-index

            if (board.getCell(row, col) == '~') {
                logger.info("Bot lépése: {}{}", (char)('A' + col), row + 1);
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