package org.example.service;

import org.example.domain.Board;

public class GameStateCheckingService {

    private final Board board;

    public GameStateCheckingService(Board board) {
        this.board = board;
    }

    private boolean hasFourFrom(int row, int col, int rowDir, int colDir, char symbol) {
        for (int i = 0; i < 5; i++) {
            int r = row + i * rowDir;
            int c = col + i * colDir;

            if (r < 0 || r >= board.getRow() ||
                    c < 0 || c >= board.getCol() ||
                    board.getCell(r, c) != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean hasFourInARow(char symbol) {
        for (int row = 0; row < board.getRow(); row++) {
            for (int col = 0; col < board.getCol(); col++) {

                if (board.getCell(row, col) != symbol) continue;

                if (hasFourFrom(row, col, 0, 1, symbol)) return true;
                if (hasFourFrom(row, col, 1, 0, symbol)) return true;
                if (hasFourFrom(row, col, 1, 1, symbol)) return true;
                if (hasFourFrom(row, col, 1, -1, symbol)) return true;
            }
        }
        return false;
    }
}
