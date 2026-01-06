package org.example.domain;

public class Step {
    private final int row;
    private final int col;

    public Step(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
