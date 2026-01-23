package org.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.example.domain.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("PMD.AtLeastOneConstructor")
public class GameSaverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameSaverService.class);
    private static final String FILE_NAME = "map.txt";

    public void saveToFile(final Board board) {
        try {
            final List<String> lines = new ArrayList<>();

            for (int r = 0; r < board.getRow(); r++) {
                final StringBuilder stringBuilder = new StringBuilder();
                for (int c = 0; c < board.getCol(); c++) {
                    stringBuilder.append(board.getCell(r, c));
                }
                lines.add(stringBuilder.toString());
            }

            Files.write(Path.of(FILE_NAME), lines);
            LOGGER.info("Játék mentve: {}", FILE_NAME);

        } catch (IOException e) {
            LOGGER.error("Hiba a mentés során", e);
        }
    }
}