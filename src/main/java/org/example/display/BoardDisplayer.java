package org.example.display;

import org.example.domain.Board;
import org.example.service.ConsoleService;

public class BoardDisplayer {
    private final ConsoleService consoleService;

    public BoardDisplayer(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }


    public void display(final Board board) {
        final int rows = board.getRow();
        final int cols = board.getCol();
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("   "); // üres hely a sor sorszámának
        for (int c = 0; c < cols; c++) {
            stringBuilder.append((char) ('A' + c)).append(" ");
        }
        stringBuilder.append("\n");

        for (int r = 0; r < rows; r++) {
            stringBuilder.append(r + 1);
            stringBuilder.append(r + 1 < 10 ? "  " : " "); // Szép igazítás, ha 1 számjegyű a sor

            for (int c = 0; c < cols; c++) {
                stringBuilder.append(board.getCell(r, c)).append(" ");
            }
            stringBuilder.append("\n");
        }
        consoleService.print("\n" + stringBuilder.toString());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
