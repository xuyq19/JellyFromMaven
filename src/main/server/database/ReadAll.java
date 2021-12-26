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
        User[] usersArray = new User[Counter.count()];
        /**
         * This string is used to store the connection string.
         */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            /**
             * connect database
             */
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            /**
             * read all users from database
             */

            /**
             *  store the data from the database in the string array
             */
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            for(int i = 0; i < resultSet.getFetchSize(); i++){
                resultSet.next();
                usersArray[i].setBankAccountUserId(resultSet.getString("accountId"));
                usersArray[i].setBankAccountName(resultSet.getString("username"));
                usersArray[i].setBankAccountPassword(resultSet.getString("password"));
                usersArray[i].setBankAccountRealId(resultSet.getString("Id"));
                usersArray[i].setBankAccountPhoneNumber(resultSet.getString("phone"));
                usersArray[i].setBankAccountSex(resultSet.getString("sex"));
                usersArray[i].setBankAccountBirthDate(resultSet.getString("birthday"));
                usersArray[i].setBankAccountBalance(resultSet.getDouble("money"));
                resultSet.next();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersArray;
    }
}