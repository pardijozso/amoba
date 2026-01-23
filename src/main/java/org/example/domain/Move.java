package org.example.domain;

@SuppressWarnings("PMD.ShortClassName")
public class Move {
    private final int row;
    private final int col;

    public Move(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
