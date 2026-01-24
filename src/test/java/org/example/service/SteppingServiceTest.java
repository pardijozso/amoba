package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.example.domain.Board;
import org.example.domain.Move;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SteppingServiceTest {

    @Mock
    private ConsoleService consoleService;

    @Mock
    private Board board;

    private SteppingService steppingService;

    @BeforeEach
    void setUp() {
        steppingService = new SteppingService(consoleService);
    }

    @Test
    void getMoveDetails_validInput_returnsMove() {
        // GIVEN
        when(board.getRow()).thenReturn(5);
        when(board.getCol()).thenReturn(5);

        // User input: oszlop A, sor 1
        when(consoleService.readStringFromConsole(anyString()))
                .thenReturn("A")
                .thenReturn("1");

        when(board.getCell(0, 0)).thenReturn('~');
        when(board.isValidMove(0, 0)).thenReturn(true);

        // WHEN
        Move move = steppingService.getMoveDetails(board);

        // THEN
        assertEquals(0, move.getRow());
        assertEquals(0, move.getCol());

        verify(consoleService, never()).print(contains("Érvénytelen"));
    }

    @Test
    void getMoveDetails_occupiedCell_retriesUntilValid() {
        when(board.getRow()).thenReturn(5);
        when(board.getCol()).thenReturn(5);

        when(consoleService.readStringFromConsole(anyString()))
                .thenReturn("A") // 1. próbálkozás
                .thenReturn("1")
                .thenReturn("B") // 2. próbálkozás
                .thenReturn("2");

        when(board.getCell(0, 0)).thenReturn('X'); // foglalt
        when(board.getCell(1, 1)).thenReturn('~');

        when(board.isValidMove(1, 1)).thenReturn(true);

        Move move = steppingService.getMoveDetails(board);

        assertEquals(1, move.getRow());
        assertEquals(1, move.getCol());

        verify(consoleService).print("Ez a mező foglalt");
    }

    @Test
    void getMoveDetails_invalidRowNumber_printsError() {
        when(board.getRow()).thenReturn(5);
        when(board.getCol()).thenReturn(5);

        when(consoleService.readStringFromConsole(anyString()))
                .thenReturn("A")
                .thenReturn("abc") // hibás sor
                .thenReturn("A")
                .thenReturn("1");

        when(board.getCell(0, 0)).thenReturn('~');
        when(board.isValidMove(0, 0)).thenReturn(true);

        Move move = steppingService.getMoveDetails(board);

        assertEquals(0, move.getRow());
        assertEquals(0, move.getCol());

        verify(consoleService).print("Érvénytelen szám! Próbáld újra.");
    }


}