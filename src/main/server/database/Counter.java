package main.server.database;
import java.sql.*;
import java.util.*;
public class Counter {
    /**
     * This is used to keep track of the number of accounts in the database.
     */
    int counter=0;
    public static int getCounter() {
        /**
         * Connects to the database and gets the number of accounts in the database.
         */
        Connection conn = null;
        Statement stmt = null;
        try {
            /**
             * Registers the driver.
             */
            Class.forName("org.sqlite.JDBC");
            /**
             * Connects to the database.
             */
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            /**
             * Creates a statement.
             */
            stmt = conn.createStatement();
            /**
             * Executes the query.
             */
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM accounts");
            /**
             * Gets the number of accounts in the database.
             */
            while (rs.next()) {
                counter = rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        /**
         * Closes the connection.
         */
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
        /**
         * Returns the number of accounts in the database.
         */
        return counter;
    }

}
