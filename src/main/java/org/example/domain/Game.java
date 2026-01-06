package org.example.domain;

public class Game {
    private final Board board;
    private final Player player;
    private final Player bot;

    public Game(Board board, Player player, Player bot) {
        this.board = board;
        this.player = player;
        this.bot = bot;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getBot() {
        return bot;
    }
}
