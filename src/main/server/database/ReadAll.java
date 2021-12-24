package main.server.database;

import main.server.user.User;

import java.sql.*;

import static main.server.Main.*;

/**
 * This class is used to read all the data from the database.
 */
public class ReadAll {
    /**
     * This method is used to read all the data from the database.
     *
     * @return a string array that contains all the data from the database.
     */
    public static User[] readAll() throws Exception {
        /**
         * This string array is used to store all the data from the database.
         */
        User[] usersArray = new User[Counter.getCounter()];
        /**
         * This string is used to store the connection string.
         */
        try {
            Class.forName(JDBC_DRIVER);
            /**
             * This is the connection to the database.
             */
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            /**
             * This is the statement that is used to read the data from the database.
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
             * This is the query that is used to read the data from the database.
             */
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            /**
             * This is the counter used to store the data from the database.
             */
            int index = 0;
            /**
             * Use User[] to store the data from the database.
             */
            for (index = 0; rs.next(); index++) {
                /**
                 * This is the bankAccountUserId from the database.
                 */
                String bankAccountSexString = rs.getString("bankAccountSex");
                char bankAccountSex = bankAccountSexString.charAt(0);
                usersArray[index] = new User (
                        rs.getString("bankAccountUserId"),
                        rs.getString("bankAccountName"),
                        rs.getString("bankAccountPassword"),
                        rs.getString("bankAccountRealId"),
                        rs.getString("bankAccountPhoneNumber"),
                        bankAccountSex,
                        rs.getString("bankAccountBirthDate"),
                        rs.getDouble("bankAccountBalance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersArray;
    }
}
