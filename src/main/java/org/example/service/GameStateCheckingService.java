package org.example.service;

public class GameStateCheckingService {


    private boolean hasFourFrom(int row, int col, int rowDir, int colDir, char symbol) {
        for (int i = 0; i < 4; i++) {
            int r = row + i * rowDir;
            int c = col + i * colDir;

            if (r < 0 || r >= grid.length ||
                    c < 0 || c >= grid[r].length ||
                    grid[r][c] != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean hasFourInARow(char symbol) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                if (grid[row][col] != symbol) {
                    continue;
                }

                if (hasFourFrom(row, col, 0, 1, symbol)) return true;
                if (hasFourFrom(row, col, 1, 0, symbol)) return true;
                if (hasFourFrom(row, col, 1, 1, symbol)) return true;
                if (hasFourFrom(row, col, 1, -1, symbol)) return true;
            }
        }
        return false;
    }
}
