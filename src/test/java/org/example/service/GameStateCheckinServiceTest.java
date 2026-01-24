package org.example.service;

import org.example.domain.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameStateCheckinServiceTest {

    @Mock
    Board board;

    GameStateCheckingService service;

    @BeforeEach
    void setUp() {
        service = new GameStateCheckingService(board);

    }

    @Test
    void hasFourInARow_horizontal_returnsTrue() {
        // given
        when(board.getRow()).thenReturn(6);
        when(board.getCol()).thenReturn(7);

        when(board.getCell(anyInt(), anyInt())).thenReturn(' ');

        char X = 'X';
        for (int col = 0; col < 5; col++) {
            when(board.getCell(0, col)).thenReturn(X);
        }

        // when
        boolean result = service.hasFourInARow(X);

        // then
        assertTrue(result);
    }

    @Test
    void hasFourInARow_vertical_returnsTrue() {
        // given
        when(board.getRow()).thenReturn(6);
        when(board.getCol()).thenReturn(7);

        when(board.getCell(anyInt(), anyInt())).thenReturn(' ');

        char O = 'O';
        for (int row = 0; row < 5; row++) {
            when(board.getCell(row, 2)).thenReturn(O);
        }

        // when
        boolean result = service.hasFourInARow(O);

        // then
        assertTrue(result);
    }

    @Test
    void hasFourInARow_diagonalDownRight_returnsTrue() {
        // given
        when(board.getRow()).thenReturn(6);
        when(board.getCol()).thenReturn(7);

        when(board.getCell(anyInt(), anyInt())).thenReturn(' ');

        char X = 'X';
        for (int i = 0; i < 5; i++) {
            when(board.getCell(i, i)).thenReturn(X);
        }

        // when
        boolean result = service.hasFourInARow(X);

        // then
        assertTrue(result);
    }

    @Test
    void hasFourInARow_diagonalDownLeft_returnsTrue() {
        // given
        when(board.getRow()).thenReturn(6);
        when(board.getCol()).thenReturn(7);

        when(board.getCell(anyInt(), anyInt())).thenReturn(' ');

        char X = 'X';
        for (int i = 0; i < 5; i++) {
            when(board.getCell(i, 4 - i)).thenReturn(X);
        }

        // when
        boolean result = service.hasFourInARow(X);

        // then
        assertTrue(result);
    }

    @Test
    void hasFourInARow_noFiveInARow_returnsFalse() {
        // given
        when(board.getRow()).thenReturn(6);
        when(board.getCol()).thenReturn(7);

        when(board.getCell(anyInt(), anyInt())).thenReturn(' ');

        // when
        boolean result = service.hasFourInARow('X');

        // then
        assertFalse(result);
    }


}
