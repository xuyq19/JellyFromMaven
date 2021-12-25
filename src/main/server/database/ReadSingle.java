package main.server.database;

import main.server.user.User;

import java.sql.*;

import static main.server.Main.*;

/**
 * This class is used to read a single user data from the database.
 * @author xuyuq
 */
public class ReadSingle {

    public static boolean checkInformation(String bankAccountUserId, String bankAccountPassword) {
        boolean isSuccess = false;
        try {
            /**
             * Register the driver.
             */
            Class.forName(JDBC_DRIVER);
            /**
             * Establish the connection.
             */
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            /**
             * Create the statement.
             */
            Statement stmt = conn.createStatement();
            /**
             * Choose the database.
             */
            stmt.execute("USE " + DATABASE_NAME + ";");
            /**
             * Choose the table.
             */
            stmt.execute("USE " + TABLE_NAME + ";");
            /**
             * Create the query.
             */
            String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + bankAccountUserId + " = '" + bankAccountUserId + "' AND " + bankAccountPassword + " = '" + bankAccountPassword + "';";
            /**
             * Execute the query.
             */
            ResultSet resultSet = stmt.executeQuery(sql);
            /**
             * Check if the query is successful.
             * If the query is successful, the user information will be stored in the ResultSet.
             */
            if (resultSet.next()) {
                isSuccess = true;
            }
            /**
             * Close the connection.
             * Close the statement.
             */
            resultSet.close();
            stmt.close();
            conn.close();

    } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
        public static User returnUser(String bankAccountUserId) {
        User user = new User();
        try {
            /**
             * Register the driver.
             */
            Class.forName(JDBC_DRIVER);
            /**
             * Establish the connection.
             */
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            /**
             * Create the statement.
             */
            Statement stmt = conn.createStatement();
            /**
             * Choose the database.
             */
            stmt.execute("USE " + DATABASE_NAME + ";");
            /**
             * Choose the table.
             */
            stmt.execute("USE " + TABLE_NAME + ";");
            /**
             * Create the query.
             */
            String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + bankAccountUserId + " = '" + bankAccountUserId + "';";
            /**
             * Execute the query.
             */
            ResultSet resultSet = stmt.executeQuery(sql);
            /**
             * Check if the query is successful.
             * If the query is successful, the user information will be stored in the ResultSet.
             */
            if (resultSet.next()) {
                user.setBankAccountUserId(resultSet.getString(1));
            }
            /**
             * Close the connection.
             */
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}