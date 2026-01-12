package org.example.domain;

public class Game {
    private final Board board;
    private final HumanPlayer humanPlayer;
    private final BotPlayer bot;
    private char winner='D';


    public Game(Board board, HumanPlayer humanPlayer, BotPlayer bot) {
        this.board = board;
        this.humanPlayer = humanPlayer;
        this.bot = bot;
    }

    public Board getBoard() {
        return board;
    }

    public HumanPlayer getPlayer() {
        return humanPlayer;
    }

    public BotPlayer getBot() {
        return bot;
    }

    public char getWinner() {
        return winner;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }
}
