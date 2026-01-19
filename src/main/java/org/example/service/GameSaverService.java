package org.example.service;

import org.example.domain.Board;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameSaverService {
    private static final String FILE_NAME = "map.txt";

    public void saveToFile(Board board) {
        try {
            List<String> lines = new ArrayList<>();

            for (int r = 0; r < board.getRow(); r++) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < board.getCol(); c++) {
                    sb.append(board.getCell(r, c));
                }
                lines.add(sb.toString());
            }

            Files.write(Path.of(FILE_NAME), lines);
            System.out.println("Játék mentve: " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Hiba a mentés során: " + e.getMessage());
        }
    }
}
