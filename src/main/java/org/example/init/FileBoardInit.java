package org.example.init;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.example.domain.Board;
import org.example.service.ConsoleService;

@SuppressWarnings("PMD.OnlyOneReturn")
public class FileBoardInit implements BoardInit {
    private final int boardInitType = 2;
    private final ConsoleService consoleService;
    private static final String FILE_NAME = "map.txt"; // fix fájl a resources könyvtárban

    public FileBoardInit(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @Override
    public int getBoardInitType() {
        return boardInitType;
    }

    @Override
    public Board readBoardDetails() {
        final Path path = Path.of(FILE_NAME);
        try {
            final List<String> lines = Files.readAllLines(path);

            if (lines.isEmpty()) {
                consoleService.print("Nincs mentett játék.");
                return null;
            }

            final int rows = lines.size();
            final int cols = lines.get(0).length();

            final Board board = new Board(rows, cols);

            for (int r = 0; r < rows; r++) {
                final String row = lines.get(r);
                for (int c = 0; c < cols; c++) {
                    board.setCell(r, c, row.charAt(c));
                }
            }

            consoleService.print("Játék sikeresen betöltve.");
            return board;

        } catch (IOException | NullPointerException e) {
            consoleService.print("Nem sikerült beolvasni a fájlt: " + FILE_NAME);
            return null;
        }
    }
}

