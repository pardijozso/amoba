package org.example.domain;

@SuppressWarnings("PMD.ShortClassName")
public class Game {
    private final Board board;
    private final HumanPlayer humanPlayer;
    private final BotPlayer bot;
    private char winner = 'D';


    public Game(final Board board, final HumanPlayer humanPlayer, final BotPlayer bot) {
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

    public void setWinner(final char winner) {
        this.winner = winner;
    }
}
