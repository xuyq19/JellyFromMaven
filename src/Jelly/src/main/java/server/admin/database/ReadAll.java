package server.admin.database;

import server.main.User;

import java.sql.*;

import static server.admin.Main.*;

/**
 * This class is used to read all the data from the database.
 */
public class ReadAll {

    final static String DB_USER ="xizhilang";
    final static String DB_PASS ="123456";
    /**
     * This method is used to read all the data from the database.
     *
     * @return a string array that contains all the data from the database.
     */
    public static User[] readAll() throws Exception {

        /**
         * This string array is used to store all the data from the database.
         */
        server.main.User[] usersArray = new server.main.User[Counter.count()];
        /**
         * This string is used to store the connection string.
         */
        try {
            Class.forName(JDBC_DRIVER);
            /**
             * connect database
             */
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            /**
             * read all users from database
             */

            /**
             *  store the data from the database in the string array
             */
            ResultSet resultSet=statement.executeQuery("SELECT * FROM bank");


            for (int i = 0; resultSet.next(); ++i) {
                usersArray[i] = new User();
                usersArray[i].setBankAccountUserId(resultSet.getString(1));
                System.out.println(usersArray[i].getBankAccountUserId());
                usersArray[i].setBankAccountName(resultSet.getString(2));
                usersArray[i].setBankAccountPassword(resultSet.getString(3));
                usersArray[i].setBankAccountRealId(resultSet.getString(4));
                usersArray[i].setBankAccountPhoneNumber(resultSet.getString(5));
                usersArray[i].setBankAccountSex(resultSet.getString(6));
                usersArray[i].setBankAccountBirthDate(resultSet.getString(7));
                usersArray[i].setBankAccountBalance(resultSet.getDouble(8));
            }
            System.out.println("read all users from database");


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("read all users from database failed");
        }
        return usersArray;
    }
}