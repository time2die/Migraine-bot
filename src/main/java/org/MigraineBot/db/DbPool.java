package org.MigraineBot.db;

import lombok.Getter;
import org.MigraineBot.tg.MigraineBot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DbPool {

    private static volatile DbPool instance;

    public static DbPool getInstance() {
        DbPool localInstance = instance;
        if (localInstance == null) {
            synchronized (MigraineBot.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DbPool();
                }
            }
        }
        return localInstance;
    }


    public static void main(String[] args) {
        new DbPool();
    }

    @Getter
    private Connection dbConn;

    public DbPool() {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://91.235.136.131:15432/migrene_mvp_bot", "postgres", "sx6sRgkE9ej8V2vk")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
