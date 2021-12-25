package main.server.database;

import main.server.user.User;

import java.sql.*;
import java.util.*;
import java.io.*;

import static main.server.Main.*;

/**
 * Write is a class that is used to write to the database.
 *
 * @author xuyuqi
 * @version 1.0
 * @since 2021-12-22
 */
public class Write {
    /**
     * The connection to the database.
     */
    private Connection conn;
    /**
     * The statement to write to the database.
     */
    private Statement stmt;
    /**
     * The result set to read from the database.
     */
    private ResultSet rs;

    /**
     * The constructor of the class.
     *
     * @param conn the connection to the database
     */
    public Write(Connection conn) {
        this.conn = conn;
    }
    /**
     * The method to write to the database.
     * @param sql the sql statement to write to the database
     * @return the result set
     */
    /**
     * The method to write to the database.
     */
    public static void createUser(User user) {
        /**
         * The sql statement to write to the database.
         */
        Connection conn = null;
        Statement stmt = null;
        try {
            /**
             * Register JDBC driver
             */
            Class.forName(JDBC_DRIVER);
            /**
             * Open a connection
             */
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            /**
             * Choose the database
             */
            stmt.execute("USE " + DATABASE_NAME);
            /**
             * Choose the table
             */
            stmt.execute("USE " + TABLE_NAME + ";");
            /**
             * add the user to the database
             * @param user the user to add
             */
            String sql = "INSERT INTO " + TABLE_NAME + " (bankAccountUserId, bankAccountUserName, bankAccountUserPassword,bankAccountRealId, bankAccountPhoneNumber, bankAccountSex, bankAccountBirthDate, bankAccountBalance" + ";";
            stmt.execute(sql);
            /**
             * Close the connection
             */
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method to write to the database.
     *
     * @param user the sql statement to write to the database
     * @return the result set
     */
    public static void setUserInfo(User user, int selection) {
        /**
         * Selections:
         * 0: set the user's bank account name
         * 1: set the user's bank account password
         * 3: set the user's bank account phone number
         * 4: set the user's bank account sex
         * 5: set the user's bank account birth date
         * 6: set the user's balance
         */
        /**
         * The sql statement to write to the database.
         */
        Connection conn = null;
        Statement stmt = null;
        try {
            /**
             * Register JDBC driver
             */
            Class.forName(JDBC_DRIVER);
            /**
             * Open a connection
             */
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            /**
             * Choose the database
             */
            stmt.execute("USE " + DATABASE_NAME);
            /**
             * Choose the table
             */
            stmt.execute("USE " + TABLE_NAME + ";");
            /**
             * Set the value of the column
             */
            switch (selection) {
                case 0:
                    stmt.execute("UPDATE " + TABLE_NAME + " SET bankAccountUserPassName = '" + user.getBankAccountName() + "' WHERE bankAccountUserId = '" + user.getBankAccountUserId() + "';");
                    break;
                case 1:
                    stmt.execute("UPDATE " + TABLE_NAME + " SET bankAccountUserPassWord = '" + user.getBankAccountPassword() + "' WHERE bankAccountUserId = '" + user.getBankAccountUserId() + "';");
                    break;
                case 2:
                    break;
                case 3:
                    stmt.execute("UPDATE " + TABLE_NAME + " SET bankAccountPhoneNumber = '" + user.getBankAccountPhoneNumber() + "' WHERE bankAccountUserId = '" + user.getBankAccountUserId() + "';");
                    break;
                case 4:
                    stmt.execute("UPDATE " + TABLE_NAME + " SET bankAccountSex = '" + user.getBankAccountSex() + "' WHERE bankAccountUserId = '" + user.getBankAccountUserId() + "';");
                    break;
                case 5:
                    stmt.execute("UPDATE " + TABLE_NAME + " SET bankAccountBirthDate = '" + user.getBankAccountBirthDate() + "' WHERE bankAccountUserId = '" + user.getBankAccountUserId() + "';");
                    break;
                case 6:
                    stmt.execute("UPDATE " + TABLE_NAME + " SET bankAccountBalance = '" + user.getBankAccountBalance() + "' WHERE bankAccountUserId = '" + user.getBankAccountUserId() + "';");
                    break;
                default:
                    break;
            }
            /**
             * Close the connection
             */
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static String deleteUser(User user) {
        /**
         * Register the driver
         **/
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            /**
             * Open a connection
             **/
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            /**
             * Choose the database
             **/
            stmt.execute("USE " + DATABASE_NAME);
            /**
             * Choose the table
             **/
            stmt.execute("USE " + TABLE_NAME + ";");
            /**
             * Delete the user
             **/
            stmt.execute("DELETE FROM " + TABLE_NAME + " WHERE bankAccountUserId = '" + user.getBankAccountUserId() + "';");
            /**
             * Close the connection
             **/
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "success";
    }
    /**
     * The method to change information of database.
     * @param user the sql statement to rewrite to the database
     * @return the result set
     */

}