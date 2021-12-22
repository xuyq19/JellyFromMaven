package main.server.user;
import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;

/**
 * user functions class,processed with database
 */
public class Functions {
    private static final int USER_ID_LENGTH = 12;
    private static final int USER_NAME_LENGTH = 10;
    private static final int USER_REAL_ID_LENGTH = 12;
    private static final int USER_BIRTH_DATE_LENGTH = 16;


    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    public Functions(){}
    public void main(String[] args){

    }
    User user;

    /**
     * get all user information from database
     * @return a arraylist of user
     */
    private void readAll(){
        conn = null;
        stmt = null;
        rs = null;
        sql = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","xizhilang","123456");
            stmt = conn.createStatement();
            sql = "SELECT * FROM bankaccount";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                user = new User(rs.getString("bankAccountUserId"),rs.getString("name"),rs.getString("password"),rs.getString("realId"),rs.getString("phoneNumber"),rs.getString("
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void readSingle(){

    }

    /**
     * write single user information to database
     * @param bankAccountUserId
     * @param bankAccountName
     * @param bankAccountPassword
     * @param bankAccountRealId
     * @param bankAccountPhoneNumber
     * @param bankAccountSex
     * @param bankAccountBirthDate
     * @param bankAccountBalance
     */
    private void writeSingle(String bankAccountUserId, String bankAccountName, String bankAccountPassword, String bankAccountRealId, String bankAccountPhoneNumber, char bankAccountSex, String bankAccountBirthDate, int bankAccountBalance){
        /**
         * connect to the database
         */
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","123456");
            stmt = conn.createStatement();
            sql = "INSERT INTO bankaccount VALUES('"+bankAccountUserId+"','"+bankAccountName+"','"+bankAccountPassword+"','"+bankAccountRealId+"','"+bankAccountPhoneNumber+"','"+bankAccountSex+"','"+bankAccountBirthDate+"','"+bankAccountBalance+"')";
            stmt.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private boolean checkInfo(String bankAccountUserId, String bankAccountName, String bankAccountPassword, String bankAccountRealId, String bankAccountPhoneNumber, char bankAccountSex, String bankAccountBirthDate, int bankAccountBalance){
        if(bankAccountUserId.length()!=USER_ID_LENGTH){
            System.out.println("The length of User ID must be 10!");
            return false;
        }
        /**
         * Test the length of User Name
         */
        for(int i = 0;i < bankAccountUserId.length();i++){
            if(bankAccountUserId.charAt(i) > '9' || bankAccountUserId.charAt(i) < '0'){
                return false;
            }
            /**
             * Test User Real ID
             */
        }
        if(bankAccountName.length()>USER_NAME_LENGTH){
            System.out.println("The length of user name must be shorter than 10!");
            return false;
        }
        /**
         * Test the length of User Name
         */
        if(bankAccountRealId.length()!=USER_REAL_ID_LENGTH) {
            System.out.println("The length of real ID must be 12!");
            return false;
        }
        /**
         * Test User Phone Number
         */
        for(int i = 0;i < bankAccountRealId.length();i++){
            if(bankAccountRealId.charAt(i) > '9' ||bankAccountRealId.charAt(i) < '0'){
                System.out.println("The id must be numbers!");
                return false;
            }
        }
        if(bankAccountSex != 'F' && bankAccountSex != 'M'){
            System.out.println("The sex must be F or M");
        }
        if(bankAccountBirthDate.length()!=USER_BIRTH_DATE_LENGTH){
            System.out.println("The format of birthday must be YYYY-MM-DD");
            return false;
        }
        if(bankAccountBirthDate.charAt(4)!='-'||bankAccountBirthDate.charAt(7)!='-'){
            System.out.println("The format of birthday must be YYYY-MM-DD");
            return false;
        }
        return true;}
}