package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:amoba.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initDatabase() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS highscore (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        player_name TEXT UNIQUE,
                        games_won INTEGER
                    );
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
