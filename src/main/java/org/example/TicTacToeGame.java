package org.example;


import org.example.Display.BoardDisplayer;
import org.example.domain.Board;
import org.example.domain.BotPlayer;
import org.example.domain.Game;
import org.example.domain.HumanPlayer;
import org.example.init.BoardInit;
import org.example.init.PlayerInit;
import org.example.service.*;

import java.util.Scanner;

public class TicTacToeGame {
    public static void main( String[] args ) {
        final Scanner scanner = new Scanner(System.in);
        final ConsoleService consoleService = new ConsoleService(scanner);
        final BoardInitDeciderService BoardDecider = new BoardInitDeciderService(consoleService);
        final BoardInit boardInit = BoardDecider.getMapInitInstance();
        final PlayerInit playerInit = new PlayerInit(consoleService);

        final Board board = boardInit.readBoardDetails();
        final HumanPlayer player = playerInit.readHumanPlayer();
        final BotPlayer bot = playerInit.prepareBotPlayer();
        final BoardDisplayer displayer = new BoardDisplayer(consoleService);
        final SteppingService steppingService = new SteppingService(consoleService);
        final GameStateCheckingService gameStateCheck = new GameStateCheckingService(board);
        final GameService gameService = new GameService(steppingService,gameStateCheck,displayer);

        if(boardInit.getBoardInitType() == 1){
            gameService.startNewGame(new Game(board, player, bot));
        } else if (boardInit.getBoardInitType() == 2) {
            gameService.startGameFromFile(new Game(board, player, bot));
        }
        scanner.close();
    }
}
