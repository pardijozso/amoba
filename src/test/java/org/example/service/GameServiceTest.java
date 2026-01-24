package org.example.service;

import org.example.display.BoardDisplayer;
import org.example.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    SteppingService steppingService;
    @Mock
    GameStateCheckingService checker;
    @Mock
    BoardDisplayer displayer;
    @Mock
    HighScoreService highScoreService;

    @Mock
    Game game;
    @Mock
    HumanPlayer player;
    @Mock
    BotPlayer bot;
    @Mock
    Board board;

    @Mock
    Move playerMove;
    @Mock
    Move botMove;

    @InjectMocks
    GameService gameService;

    @BeforeEach
    void setUp() {
        when(game.getPlayer()).thenReturn(player);
        when(game.getBot()).thenReturn(bot);
        when(game.getBoard()).thenReturn(board);
    }

    @Test
    void startGameFromFile_playerWins_botDoesNotMove_andHighScoreUpdated() {
        // given
        when(player.getName()).thenReturn("TesztElek");
        when(player.getSymbol()).thenReturn('X');

        when(steppingService.getMoveDetails(board)).thenReturn(playerMove);

        when(checker.hasFourInARow('X')).thenReturn(true);
        when(game.getWinner()).thenReturn('X');

        // when
        gameService.startGameFromFile(game);

        // then
        verify(player).makeMove(board, playerMove);
        verify(bot, never()).makeMove(any(), any());
        verify(highScoreService).addWin("TesztElek");
    }

    @Test
    void startGameFromFile_botMoves() {
        // given
        when(player.getSymbol()).thenReturn('X');
        when(bot.getSymbol()).thenReturn('O');

        when(steppingService.getMoveDetails(board)).thenReturn(playerMove);
        when(steppingService.calculateBotMove(board)).thenReturn(botMove);

        when(checker.hasFourInARow(anyChar())).thenReturn(false);
        when(board.isBoardFull()).thenReturn(false, true); // 2. kör → vége

        // when
        gameService.startGameFromFile(game);

        // then
        verify(bot).makeMove(board, botMove);
    }

    @Test
    void startNewGame_botWins_noHighScoreAdded() {
        // given
        when(bot.getSymbol()).thenReturn('O');
        when(steppingService.calculateBotMove(board)).thenReturn(botMove);

        when(checker.hasFourInARow('O')).thenReturn(true);


        // when
        gameService.startNewGame(game);

        // then
        verify(bot).makeMove(board, botMove);
        verify(highScoreService, never()).addWin(anyString());
    }
}
