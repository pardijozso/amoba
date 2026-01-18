package org.example.init;

import org.example.domain.Board;
import org.example.service.ConsoleService;

public class ConsoleBoardInit implements BoardInit{
    private final int boardInitType =1;
    private final ConsoleService consoleService;

    public ConsoleBoardInit(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }
    @Override
    public int getBoardInitType() {
        return boardInitType;
    }

    @Override
    public Board readBoardDetails() {
        final int row=consoleService.readIntFromConsole("Please provide how many rows you want for a map (4-25): ");
        final int col=consoleService.readIntFromConsole("Please provide how many coulms you want for a map (4-25): ");
        Board board= new Board(row,col);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    board.setCell(i,j,'~');
                }
            }
        board.placeSymbol((row-1)/2,(col-1)/2,'X');
        return board;
    }
}
