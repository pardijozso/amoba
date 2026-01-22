package org.example.Display;

import org.example.domain.Board;
import org.example.service.ConsoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDisplayer {
    final ConsoleService consoleService;

    public BoardDisplayer(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }


    public void display(Board board) {
        int rows = board.getRow();
        int cols = board.getCol();

        StringBuilder sb = new StringBuilder();

        sb.append("   "); // üres hely a sor sorszámának
        for (int c = 0; c < cols; c++) {
            sb.append((char) ('A' + c)).append(" ");
        }
        sb.append("\n");

        for (int r = 0; r < rows; r++) {
            sb.append(r + 1);
            sb.append(r + 1 < 10 ? "  " : " "); // Szép igazítás, ha 1 számjegyű a sor

            for (int c = 0; c < cols; c++) {
                sb.append(board.getCell(r, c)).append(" ");
            }
            sb.append("\n");
        }
        consoleService.print("\n" + sb.toString());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
