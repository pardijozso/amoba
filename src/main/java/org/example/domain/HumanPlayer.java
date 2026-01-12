package org.example.domain;

public class HumanPlayer implements Player{
    private final String name;
    private  final char symbol='X';

    public HumanPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public void makeMove(Board board,Move move){
        board.placeSymbol(move.getRow(), move.getCol(),this.getSymbol());
    }
}
