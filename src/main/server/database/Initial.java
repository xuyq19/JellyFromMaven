package main.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static main.server.Main.*;

public class Initial {
    public static void main(String[] args) throws Exception {
        Initial initial = new Initial();
        initial.createDatabase();
        initial.createTable();
    }
    private void createDatabase() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        try {
            /**
             * Register the driver
             */
            Class.forName(JDBC_DRIVER);
            /**
             * Connect to the database
             */
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            /**
             * Create the database if it does not exist
             */
            String sql = "CREATE DATABASE IF NOT EXISTS '" + DATABASE_NAME + "'";
            stmt.executeUpdate(sql);
            /**
             * use the database
             */
            String sql2 = "USE '" + DATABASE_NAME + "'";
            stmt.executeUpdate(sql2);
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    private void createTable() throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        try {
            /**
             * Register the driver
             */
            Class.forName(JDBC_DRIVER);
            /**
             * Connect to the database
             */
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            /**
             * Create the table if it does not exist
             */
            String sql3 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    "bankAccountUserId VARCHAR(255) PRIMARY KEY," +
                    "bankAccountName VARCHAR(255) NOT NULL," +
                    "bankAccountPassword VARCHAR(255) NOT NULL,"+
                    "bankAccountRealId VARCHAR(255) NOT NULL," +
                    "bankAccountPhoneNumber VARCHAR(255) NOT NULL," +
                    "bankAccountSex VARCHAR (2)NOT NULL," +
                    "bankAccountBirthDate VARCHAR(255) NOT NULL," +
                    "bankAccountBalance INTEGER NOT NULL,"
                    ;
            stmt.executeUpdate(sql3);
            stmt.close();
            conn.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }

    }
}
