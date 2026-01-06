package org.example.domain;

public final class Board {
    private final int row;
    private final int col;

    public Board(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /* public static final char EMPTY = '~';
    private final char[][] grid;

    public Board(int rows, int cols) {
        grid = new char[rows][cols];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = EMPTY;
            }
        }
    }
    public boolean isFieldEmpty(int row, int col) {
        return grid[row][col] == EMPTY;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    } */


}