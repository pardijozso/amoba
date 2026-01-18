package org.example.service;


import org.example.Display.BoardDisplayer;
import org.example.domain.*;

public class GameService {
    private final SteppingService SteppingService;
    private final GameStateCheckingService checker;
    private final BoardDisplayer displayer;
    private boolean gameOver = false;

    public GameService(SteppingService steppingService, GameStateCheckingService checker, BoardDisplayer displayer) {
        this.SteppingService = steppingService;
        this.checker = checker;
        this.displayer = displayer;
    }

    private void checkGameOver(Game game, char symbol) {
        Board board = game.getBoard();

        if (checker.hasFourInARow(symbol)) {
            game.setWinner(symbol);
            gameOver = true;
        }
        else if (board.isBoardFull()) {
            gameOver = true;
        }
    }

    public void startGameFromFile(Game game) {
        final HumanPlayer player= game.getPlayer();
        final BotPlayer bot= game.getBot();
        final Board board= game.getBoard();
        while (!gameOver) {
            try {
                displayer.display(game.getBoard());
                Move playerMove = SteppingService.getMoveDetails(board);
                player.makeMove(board,playerMove);
            } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            checkGameOver(game, player.getSymbol());
            if (gameOver) continue;

            displayer.display(game.getBoard());
            Move botMove = SteppingService.calculateBotMove(board);
            bot.makeMove(board,botMove);
            checkGameOver(game, bot.getSymbol());
        }
    }

    public void startNewGame(Game game) {
        final HumanPlayer player= game.getPlayer();
        final BotPlayer bot= game.getBot();
        final Board board= game.getBoard();
        while (!gameOver) {
            displayer.display(game.getBoard());
            Move botMove = SteppingService.calculateBotMove(board);
            bot.makeMove(board,botMove);
            checkGameOver(game, bot.getSymbol());
            if (gameOver) continue;

            try {
                displayer.display(game.getBoard());
                Move playerMove = SteppingService.getMoveDetails(board);
                player.makeMove(board,playerMove);
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
                continue;
            }
            checkGameOver(game, player.getSymbol());
        }
    }
}
