package main.server.database;

import java.sql.*;

/**
 * this class is used to count the number of users
 */
public class Counter{

    public Counter() {}
    static Statement stmt = null;
    static ResultSet rs = null;
    /**
     * connect to database
     */
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://219.246.65.67/bank/";
    final static String USER = "xizhilang";
    final static String PASS = "123456";

    public static int count() throws SQLException{
        Connection conn = null;
        int count = 0;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM sheet1";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }


}