package org.example.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.database.DatabaseManager;

public class HighScoreService {

    private final ConsoleService consoleService;

    public HighScoreService(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public void findOrCreatePlayer(final String name) {
        final String select = "SELECT games_won FROM highscore WHERE player_name = ?";
        final String insert = "INSERT INTO highscore(player_name, games_won) VALUES(?, 0)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(select)) {

            preparedStatement.setString(1, name);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                try (PreparedStatement ins = conn.prepareStatement(insert)) {
                    ins.setString(1, name);
                    ins.executeUpdate();
                }
            }

        } catch (SQLException e) {
            consoleService.print("Adatbázis hiba történt.");
            consoleService.print(String.valueOf(e));
        }
    }

    public void addWin(final String name) {
        final String update = "UPDATE highscore SET games_won = games_won + 1 WHERE player_name = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(update)) {

            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            consoleService.print("Nem sikerült növelni a győzelmek számát.");
            consoleService.print(String.valueOf(e));
        }
    }

    public void printAll() {
        final String sql = "SELECT player_name, games_won FROM highscore ORDER BY games_won DESC";

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            consoleService.print("=== HIGHSCORE ===");
            while (resultSet.next()) {
                consoleService.print(
                        resultSet.getString("player_name") + " - " + resultSet.getInt("games_won")
                );
            }

        } catch (SQLException e) {
            consoleService.print("Nem sikerült lekérni a highscore listát.");
            consoleService.print(String.valueOf(e));
        }
    }
}