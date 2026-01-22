package org.example.domain;

public class Board {
    private final int row;
    private final int col;
    private final char[][] grid;

    public Board(int rows, int cols) {
        this.row = rows;
        this.col = cols;
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

    public boolean isValidMove(int row, int col) {
        for (int rowDiff = -1; rowDiff <= 1; rowDiff++) {
            for (int colDiff = -1; colDiff <= 1; colDiff++) {

                if (rowDiff == 0 && colDiff == 0) {
                    continue;
                }

                int neighborRow = row + rowDiff;
                int neighborCol = col + colDiff;

                if (neighborRow >= 0 && neighborRow < this.row &&
                        neighborCol >= 0 && neighborCol < this.col) {

                    if (grid[neighborRow][neighborCol] != '~') {
                        return true;
                    }
                }
            }
        }
        return false;
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