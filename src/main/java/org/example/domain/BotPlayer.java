package org.example.domain;

public class BotPlayer implements Player {
    private final String name;
    private  final char symbol = 'O';

    public BotPlayer() {
        this.name = "Bot";
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public void makeMove(final Board board, final Move move) {
        board.placeSymbol(move.getRow(), move.getCol(), this.getSymbol());
    }
}
