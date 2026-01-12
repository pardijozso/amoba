package org.example.domain;

public interface Player {

    char getSymbol();

    void makeMove(Board board, Move move);
}
