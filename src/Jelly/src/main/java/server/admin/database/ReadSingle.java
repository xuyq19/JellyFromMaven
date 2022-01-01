package server.admin.database;

import server.admin.user.User;

import java.sql.*;

import static server.admin.Main.*;

/**
 * This class is used to read a single user data from the database.
 *
 * @author xuyuq
 */
public class ReadSingle {
    final static String DB_USER = "xizhilang";
    final static String DB_PASS = "123456";

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
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
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
            /**
             * Execute the query.
             */
            /**
             * "accountId"=bankAccountUserId and "password"=bankAccountPassword
             */
            String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + bankAccountUserId + " = '" + bankAccountUserId + "' AND " + bankAccountPassword + " = '" + bankAccountPassword + "';";
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
            System.out.println("ReadSingle: Successfully read the user information.");

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: " + throwables.getMessage());
        }
        return isSuccess;
    }
}