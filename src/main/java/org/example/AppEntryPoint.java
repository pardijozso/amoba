package org.example;


import org.example.Display.BoardDisplayer;
import org.example.domain.Board;

public class AppEntryPoint {
    public static void main( String[] args ) {
        Board board = new Board(6, 7);

        board.placeSymbol(1, 1, 'X');
        board.placeSymbol(1, 2, 'O');
        board.placeSymbol(2, 3, 'X');
        board.placeSymbol(4, 5, 'O');

        BoardDisplayer displayer = new BoardDisplayer();
        displayer.display(board);



    }
}
