package org.example.domain;

public class Board {
    private final int row;
    private final int col;
    private final char[][] grid;

    public Board(int rows, int cols) {
        this.row=rows;
        this.col=cols;
        // ezt a részt majd consolmapinitbe kell kitenni
        grid = new char[rows][cols];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '~';
            }
        }
        grid[rows/2][cols/2]='X';
    }

    public boolean isBoardFull() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '~') {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeSymbol(int row, int col, char symbol) {
        if (row < 1 || row > this.row || col < 1 || col > this.col) {
            throw new IllegalStateException("Hely kívül esik a pályán!");
        }
        if (grid[row-1][col-1] != '~') {
            throw new IllegalStateException("Ez a mező már foglalt!");
        }
        grid[row-1][col-1] = symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char[][] getGrid() {
        return grid;
    }
    public char getCell(int row, int col) {
        return grid[row][col];
    }
}