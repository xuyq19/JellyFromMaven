package main.server;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
public class Initial {
    public Initial() {}
    public static void main(String[] args) {

    }

    /**
     * Connect the database
     * @return
     */
    private void createDatabase() throws Exception {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/";
        final String USER = "xizhilang";
        final String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS xizhilang";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
