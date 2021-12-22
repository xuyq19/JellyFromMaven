package main.server;
import java.sql.*;
import java.util.*;
import java.util.
/**
 * functions of users service with database
 */
public class UsersService {
    private static final int USER_NAME_LENGTH = 10;
    private static final int USER_ID_LENGTH = 12;
    private static final int USER_BIRTH_DATE_LENGTH = 16;
    private static final int USER_REAL_ID_LENGTH = 12;


    public UsersService() {}
    public void main(String[] args) throws Exception {

    }
    private void addUser(String bankAccountUserId, String bankAccountName, String bankAccountPassword, String bankAccountRealId, String bankAccountPhoneNumber, char bankAccountSex, String bankAccountBirthDate, int bankAccountBalance) {
        if(checkInfo(bankAccountUserId, bankAccountName, bankAccountPassword, bankAccountRealId, bankAccountPhoneNumber, bankAccountSex, bankAccountBirthDate, bankAccountBalance)){
            /**
             * add user into database
             */
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://219.246.65.10:3306/bank", "xizhilang", "123456");
                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO bankaccount(bankAccountUserId, bankAccountName, bankAccountPassword, bankAccountRealId, bankAccountPhoneNumber, bankAccountSex, bankAccountBirthDate, bankAccountBalance) VALUES ('" + bankAccountUserId + "', '" + bankAccountName + "', '" + bankAccountPassword + "', '" + bankAccountRealId + "', '" + bankAccountPhoneNumber + "', '" + bankAccountSex + "', '" + bankAccountBirthDate + "', '" + bankAccountBalance + "')";
                stmt.executeUpdate(sql);
                stmt.close();
                conn.close();

            }
            catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL JDBC Driver?");
            }
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");

            }
        }

    }

    private boolean checkInfo(String bankAccountUserId, String bankAccountName, String bankAccountPassword, String bankAccountRealId, String bankAccountPhoneNumber, char bankAccountSex, String bankAccountBirthDate, int bankAccountBalance) {
    }

    private void readDatabase() throws SQLException{
        /**
         * read user information from database
         */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "xizhilang", "123456");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM bankaccount";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String bankAccountUserId = rs.getString("bankAccountUserId");
                String bankAccountName = rs.getString("bankAccountName");
                String bankAccountPassword = rs.getString("bankAccountPassword");
                String bankAccountRealId = rs.getString("bankAccountRealId");
                String bankAccountPhoneNumber = rs.getString("bankAccountPhoneNumber");
                char bankAccountSex = rs.getString("bankAccountSex").charAt(0);
                String bankAccountBirthDate = rs.getString("bankAccountBirthDate");
                int bankAccountBalance = rs.getInt("bankAccountBalance");

            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    private  boolean isBankAccount

}
