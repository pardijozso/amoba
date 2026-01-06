package org.example.domain;

public class Player {
    private final String name;
    private  final char symbol;

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol=symbol;
    }
}
