package main.server.database;

import main.server.user.User;

import java.sql.*;

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
    public static void createUser(User[] user) {
        /**
         * Register driver
         */
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            /**
             * import from user[] to database
             */
            for(int i = 0; i < user.length; i++) {
                String sql = "INSERT INTO sheet1 ((accountId, username, password, Id, phone, sex, birthday,money)" + " value( \"" + user[i].getBankAccountUserId() + "\",\"" + user[i].getBankAccountName() + "\",\"" + user[i].getBankAccountPassword() + "\",\"" + user[i].getBankAccountRealId() + "\",\"" + user[i].getBankAccountPhoneNumber() + "\",\"" + user[i].getBankAccountSex() + "\",\"" + user[i].getBankAccountBirthDate() + "\"," + user[i].getBankAccountBalance() + ")";
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}