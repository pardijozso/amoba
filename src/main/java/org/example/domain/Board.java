package org.example.domain;

public class Board {
    private final int row;
    private final int col;
    private final char[][] grid;

    public Board(int rows, int cols) {
        this.row=rows;
        this.col=cols;
        grid = new char[rows][cols];
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
        grid[row][col] = symbol;
    }

    public void setCell(int row, int col, char symbol) {
        grid[row][col] = symbol;
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