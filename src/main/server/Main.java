package main.server;

public class Main {
    public static final String DATABASE_NAME = "bank";
    public static final String TABLE_NAME = "bank_accounts";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/'DATABASE_NAME'";
    public static final String USER = "xizhilang";
    public static final String PASS = "123456";
    public static void main(String[] args) {
        Initial.main();
    }


}
