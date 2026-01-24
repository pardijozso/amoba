package org.example.init;

import org.example.domain.Board;
import org.example.service.ConsoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsoleBoardInitTest {

    @Mock
    private ConsoleService consoleService;

    private ConsoleBoardInit boardInit;

    @BeforeEach
    void setUp() {
        boardInit = new ConsoleBoardInit(consoleService);
    }

    @Test
    void getBoardInitType_returnsOne() {
        // when
        int type = boardInit.getBoardInitType();

        // then
        assertEquals(1, type);
    }

    @Test
    void readBoardDetails_validInput_createsBoardAndPlacesInitialSymbol() {
        // given
        when(consoleService.readIntFromConsole(anyString()))
                .thenReturn(6, 7); // row, col

        // when
        Board board = boardInit.readBoardDetails();

        // then
        assertNotNull(board);
        assertEquals(6, board.getRow());
        assertEquals(7, board.getCol());

        // minden cella '~'
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if (i == (6 - 1) / 2 && j == (7 - 1) / 2) {
                    assertEquals('X', board.getCell(i, j));
                } else {
                    assertEquals('~', board.getCell(i, j));
                }
            }
        }
    }

    @Test
    void readBoardDetails_invalidThenValidInput_retriesAndPrintsError() {
        // given
        when(consoleService.readIntFromConsole(anyString()))
                .thenReturn(2, 30,   // ❌ invalid
                        5, 5);   // ✅ valid

        // when
        Board board = boardInit.readBoardDetails();

        // then
        assertNotNull(board);
        assertEquals(5, board.getRow());
        assertEquals(5, board.getCol());

        verify(consoleService)
                .print("A soroknak és oszlopoknak 4-25 között kell lennie!");
    }
}