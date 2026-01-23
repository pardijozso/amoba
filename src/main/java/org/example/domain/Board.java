package org.example.domain;

@SuppressWarnings("PMD.OnlyOneReturn")
public class Board {
    private final int row;
    private final int col;
    private final char[][] grid;

    public Board(final int rows, final int cols) {
        this.row = rows;
        this.col = cols;
        grid = new char[rows][cols];
    }

    public boolean isBoardFull() {
        for (final char[] chars : grid) {
            for (final char aChar : chars) {
                if (aChar == '~') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidMove(final int row, final int col) {
        for (int rowDiff = -1; rowDiff <= 1; rowDiff++) {
            for (int colDiff = -1; colDiff <= 1; colDiff++) {

                if (rowDiff == 0 && colDiff == 0) {
                    continue;
                }

                final int neighborRow = row + rowDiff;
                final int neighborCol = col + colDiff;

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

    public void placeSymbol(final int row, final int col, final char symbol) {
        grid[row][col] = symbol;
    }

    public void setCell(final int row, final int col, final char symbol) {
        grid[row][col] = symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getCell(final int row, final int col) {
        return grid[row][col];
    }
}