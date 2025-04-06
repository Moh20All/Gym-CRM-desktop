package com.example.ramzigym.Helper;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:sqlite:GymCRM.db";  // SQLite database file path

    // Check if the database file exists
    public static boolean isDatabaseExists() {
        File dbFile = new File("GymCRM.db");
        return dbFile.exists();
    }

    // Establish the database connection
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Create the clients table (only if the database is newly created)
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS clients (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " name TEXT NOT NULL,\n"
                + " email TEXT NOT NULL UNIQUE\n"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // Execute SQL statement
            stmt.execute(sql);
            System.out.println("Clients table has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Main method to check and create the database and table if necessary
    public static void setupDatabase() {
        if (!isDatabaseExists()) {
            System.out.println("Database does not exist. Creating database and tables...");
            createTable();  // Create table only if the database is being created for the first time
        } else {
            System.out.println("Database already exists. Skipping table creation.");
        }
    }

    public static void main(String[] args) {
        setupDatabase();  // Ensure database setup is performed
    }
}
