package org.example.domain;

public class HumanPlayer implements Player {
    private final String name;
    private final char symbol = 'X';

    public HumanPlayer(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public void makeMove(final Board board, final Move move) {
        board.placeSymbol(move.getRow(), move.getCol(), this.getSymbol());
    }
}
