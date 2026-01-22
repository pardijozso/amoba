package org.example.service;


import org.example.display.BoardDisplayer;
import org.example.domain.Board;
import org.example.domain.BotPlayer;
import org.example.domain.Game;
import org.example.domain.HumanPlayer;
import org.example.domain.Move;


public class GameService {
    private final SteppingService steppingService;
    private final GameStateCheckingService checker;
    private final BoardDisplayer displayer;
    private final HighScoreService highScoreService;
    private boolean gameOver = false;

    public GameService(SteppingService steppingService, GameStateCheckingService checker,
                       BoardDisplayer displayer, HighScoreService highScoreService) {
        this.steppingService = steppingService;
        this.checker = checker;
        this.displayer = displayer;
        this.highScoreService = highScoreService;
    }

    private void checkGameOver(Game game, char symbol) {
        Board board = game.getBoard();

        if (checker.hasFourInARow(symbol)) {
            game.setWinner(symbol);
            gameOver = true;
        } else if (board.isBoardFull()) {
            gameOver = true;
        }
    }

    public void startGameFromFile(Game game) {
        final HumanPlayer player = game.getPlayer();
        final BotPlayer bot = game.getBot();
        final Board board = game.getBoard();

        highScoreService.findOrCreatePlayer(player.getName());
        displayer.display(game.getBoard());

        while (!gameOver) {
            try {
                Move playerMove = steppingService.getMoveDetails(board);
                player.makeMove(board, playerMove);
                displayer.display(game.getBoard());
            } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            checkGameOver(game, player.getSymbol());
            if (gameOver) {
                if (game.getWinner() == 'X') {
                    highScoreService.addWin(player.getName());
                }
                continue;
            }

            Move botMove = steppingService.calculateBotMove(board);
            bot.makeMove(board, botMove);
            checkGameOver(game, bot.getSymbol());
            displayer.display(game.getBoard());
        }
    }

    public void startNewGame(Game game) {
        final HumanPlayer player = game.getPlayer();
        final BotPlayer bot = game.getBot();
        final Board board = game.getBoard();

        highScoreService.findOrCreatePlayer(player.getName());
        displayer.display(game.getBoard());
        while (!gameOver) {
            Move botMove = steppingService.calculateBotMove(board);
            bot.makeMove(board, botMove);
            checkGameOver(game, bot.getSymbol());
            displayer.display(game.getBoard());
            if (gameOver) {
                continue;
            }

            try {
                Move playerMove = steppingService.getMoveDetails(board);
                player.makeMove(board, playerMove);
                displayer.display(game.getBoard());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
                continue;
            }
            checkGameOver(game, player.getSymbol());
            if (gameOver && game.getWinner() == 'X') {
                highScoreService.addWin(player.getName());
            }
        }
    }
}
