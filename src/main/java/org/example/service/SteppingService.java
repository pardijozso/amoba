package org.example.service;

import org.example.domain.Board;
import org.example.domain.Move;
import java.util.Random;


public class SteppingService {
    private final ConsoleService consoleService;
    private final Random random = new Random();


    public SteppingService(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public Move getMoveDetails() {
        final int row = consoleService.readIntFromConsole("Please provide the next move row:");
        final int col = consoleService.readIntFromConsole("Please provide the next move col:");
        return new Move(row, col);
    }

    public Move calculateBotMove(Board board){
        int row, col;
        int maxRow = board.getRow();
        int maxCol = board.getCol();

        while (true) {
            row = random.nextInt(maxRow) + 1;
            col = random.nextInt(maxCol) + 1;
            if (board.getCell(row, col) == '~') {
                return new Move(row, col);
            }
        }
    }

}
