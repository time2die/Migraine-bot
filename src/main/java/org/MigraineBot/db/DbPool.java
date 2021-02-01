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

        try {

            Connection conn = DriverManager.getConnection(getDNS(), "postgres", System.getenv("DB_PSWD")) ;

            if (conn != null) {
                System.out.println("Connected to the database!");
                dbConn = conn;
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

    private static String getDNS() {
        return "jdbc:postgresql://"+System.getenv("DB_HOST")+"/migrene_mvp_bot";
    }
}
