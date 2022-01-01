package server.admin.database;

import java.sql.*;
import static  server.admin.Main.*;

/**
 * this class is used to count the number of users
 */
public class Counter{

    final static String DB_USER ="xizhilang";
    final static String DB_PASS ="123456";
    public Counter() {}
    static Statement stmt = null;
    static ResultSet rs = null;
    /**
     * connect to database
     */
    public static int count() throws SQLException{
        Connection conn = null;
        int count = 0;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,DB_USER, DB_PASS);
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM bank";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
            System.out.println("Total number of users: " + count);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Sorry,can`t find the Driver!");
        } finally {
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet closed!");
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement closed!");
            }
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed!");
            }
        }
        return count;
    }


}