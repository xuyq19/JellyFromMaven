package main.server;

public class Main {
    public static final String DATABASE_NAME = "bank";
    public static final String TABLE_NAME = "bank_accounts";
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/'DATABASE_NAME'";
    public static final String USER = "xizhilang";
    public static final String PASS = "123456";
    public static final int SERVER_PORT = 8888;
    public static final int USER_ID_LENGTH= 10;
    public static final int USER_REAL_ID_LENGTH = 12;
    public static final int USER_NAME_MAX_LENGTH = 10;
    public static final int USER_PASS_MIN_LENGTH = 4;
    public static final String ADMIN_USER_NAME = "admin";
    public static final String ADMIN_PASSWORD = "123456";
    public static void main(String[] args) {
        Initial.main();
    }


}
