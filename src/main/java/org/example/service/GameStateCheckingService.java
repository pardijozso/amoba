package org.example.service;

import org.example.domain.Board;

@SuppressWarnings("PMD.OnlyOneReturn")
public class GameStateCheckingService {

    private final Board board;

    public GameStateCheckingService(final Board board) {
        this.board = board;
    }

    private boolean hasFourFrom(final int row, final int col, final int rowDir, final int colDir, final char symbol) {
        for (int i = 0; i < 5; i++) {
            final int rowrow = row + i * rowDir;
            final int colcol = col + i * colDir;

            if (rowrow < 0 || rowrow >= board.getRow() ||
                    colcol < 0 || colcol >= board.getCol() ||
                    board.getCell(rowrow, colcol) != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean hasFourInARow(final char symbol) {
        for (int row = 0; row < board.getRow(); row++) {
            for (int col = 0; col < board.getCol(); col++) {

                if (board.getCell(row, col) != symbol) {
                    continue;
                }

                if (hasFourFrom(row, col, 0, 1, symbol)) {
                    return true;
                }
                if (hasFourFrom(row, col, 1, 0, symbol)) {
                    return true;
                }
                if (hasFourFrom(row, col, 1, 1, symbol)) {
                    return true;
                }
                if (hasFourFrom(row, col, 1, -1, symbol)) {
                    return true;
                }
            }
        }
        return false;
    }
}
