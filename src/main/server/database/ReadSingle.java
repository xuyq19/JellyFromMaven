package main.server.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This class is used to read a single user data from the database.
 * @author xuyuq
 */
public class ReadSingle {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/3306/xizhilang";

    public static boolean checkInformation(String bankAccountUserId, String bankAccountPassword) {
        boolean isSuccess = false;
        try {
            Class.forName(JDBC_DRIVER);
            java.sql.Connection conn = DriverManager.getConnection(DB_URL, "root", "123456");
            if (conn != null) {
                String sql = "SELECT * FROM bankAccountUserId WHERE bankAccountUserId = ? AND bankAccountPassword = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, bankAccountUserId);
                pstmt.setString(2, bankAccountPassword);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    isSuccess = true;
                }
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    public static String[] returnUser(String bankAccountUserId) {
        String[] user = new String[8];
        try {
            Class.forName(JDBC_DRIVER);
            java.sql.Connection conn = DriverManager.getConnection(DB_URL, "root", "123456");
            if (conn != null) {
                String sql = "SELECT * FROM bankAccountUserId WHERE bankAccountUserId = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, bankAccountUserId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    user[0] = rs.getString("bankAccountUserId");
                    user[1] = rs.getString("bankAccountPassword");
                    user[2] = rs.getString("bankAccountName");
                    user[3] = rs.getString("bankAccountRealId");

                }
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * This method is used to read a single user data from the database, and return the user data with a string array.
     * @param bankAccountUserId
     * @return
     */
}
