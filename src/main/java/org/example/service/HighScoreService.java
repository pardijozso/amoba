package org.example.service;

import org.example.database.DatabaseManager;

import java.sql.*;

public class HighScoreService {

    private final ConsoleService consoleService;

    public HighScoreService(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public void findOrCreatePlayer(String name) {
        String select = "SELECT games_won FROM highscore WHERE player_name = ?";
        String insert = "INSERT INTO highscore(player_name, games_won) VALUES(?, 0)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement ps = conn.prepareStatement(select)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                try (PreparedStatement ins = conn.prepareStatement(insert)) {
                    ins.setString(1, name);
                    ins.executeUpdate();
                }
            }

        } catch (SQLException e) {
            consoleService.print("Adatbázis hiba történt.");
            e.printStackTrace();
        }
    }

    public void addWin(String name) {
        String update = "UPDATE highscore SET games_won = games_won + 1 WHERE player_name = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement ps = conn.prepareStatement(update)) {

            ps.setString(1, name);
            ps.executeUpdate();

        } catch (SQLException e) {
            consoleService.print("Nem sikerült növelni a győzelmek számát.");
            e.printStackTrace();
        }
    }

    public void printAll() {
        String sql = "SELECT player_name, games_won FROM highscore ORDER BY games_won DESC";

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            consoleService.print("=== HIGHSCORE ===");
            while (rs.next()) {
                consoleService.print(
                        rs.getString("player_name") + " - " + rs.getInt("games_won")
                );
            }

        } catch (SQLException e) {
            consoleService.print("Nem sikerült lekérni a highscore listát.");
            e.printStackTrace();
        }
    }
}