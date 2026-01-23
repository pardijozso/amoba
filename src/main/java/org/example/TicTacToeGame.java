package org.example;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.example.database.DatabaseManager;
import org.example.display.BoardDisplayer;
import org.example.display.DisplayTheWinner;
import org.example.domain.Board;
import org.example.domain.BotPlayer;
import org.example.domain.Game;
import org.example.domain.HumanPlayer;
import org.example.init.BoardInit;
import org.example.init.PlayerInit;
import org.example.service.BoardInitDeciderService;
import org.example.service.ConsoleService;
import org.example.service.GameService;
import org.example.service.GameStateCheckingService;
import org.example.service.HighScoreService;
import org.example.service.SteppingService;


public class TicTacToeGame {
    public static void main(String[] args) {
        DatabaseManager.initDatabase();
        final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        final ConsoleService consoleService = new ConsoleService(scanner);
        final BoardInitDeciderService boardDecider = new BoardInitDeciderService(consoleService);
        final HighScoreService highScoreService = new HighScoreService(consoleService);
        final BoardInit boardInit = boardDecider.getMapInitInstance();
        final PlayerInit playerInit = new PlayerInit(consoleService);
        final Board board = boardInit.readBoardDetails();
        final HumanPlayer player = playerInit.readHumanPlayer();
        final BotPlayer bot = playerInit.prepareBotPlayer();
        final BoardDisplayer displayer = new BoardDisplayer(consoleService);
        final DisplayTheWinner displayTheWinner = new DisplayTheWinner(consoleService);
        final SteppingService steppingService = new SteppingService(consoleService);
        final GameStateCheckingService gameStateCheck = new GameStateCheckingService(board);
        final GameService gameService = new GameService(steppingService, gameStateCheck, displayer, highScoreService);
        final Game game = new Game(board, player, bot);

        if (boardInit.getBoardInitType() == 1) {
            gameService.startNewGame(game);
        } else if (boardInit.getBoardInitType() == 2) {
            gameService.startGameFromFile(game);
        }
        displayTheWinner.print(game.getWinner());
        highScoreService.printAll();
        scanner.close();
    }
}
