package org.example.service;


import org.example.domain.*;

public class GameService {
    private SteppingService SteppingService;
    private GameStateCheckingService checker;
    private boolean gameOver = false;
    private Player Currentplayer;

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

    public void startGame(Game game) {
        final HumanPlayer player= game.getPlayer();
        final BotPlayer bot= game.getBot();
        final Board board= game.getBoard();
        while (!gameOver) {
            try {
                Move playerMove = SteppingService.getMoveDetails(board);
                board.placeSymbol(playerMove.getRow(), playerMove.getCol(), player.getSymbol());
            } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            checkGameOver(game, player.getSymbol());
            if (gameOver) continue;

            Move botMove = SteppingService.calculateBotMove(board);
            board.placeSymbol(botMove.getRow(), botMove.getCol(), bot.getSymbol());
            checkGameOver(game, bot.getSymbol());
        }
    }
}
