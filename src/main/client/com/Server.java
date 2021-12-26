package main.client.com;
import com.mysql.cj.x.protobuf.MysqlxNotice;
import main.server.communication.ServerThreadXAdmin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static InputStream inputStream;
    private OutputStream outputStream;
    private static String message;
    public static final String DATABASE_NAME = "bank";
    public static final String TABLE_NAME = "bank_accounts";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/'DATABASE_NAME'";
    public static final String USER = "xizhilang";
    public static final String PASS = "123456";
    public static final int SERVER_PORT = 4040;
    public static final int USER_ID_LENGTH = 10;
    public static final int USER_REAL_ID_LENGTH = 12;
    public static final int USER_NAME_MAX_LENGTH = 10;
    public static final int USER_PASS_MIN_LENGTH = 4;
    public static final String ADMIN_USER_NAME = "admin";
    public static final String ADMIN_PASSWORD = "123456";
    public static final String FILE_LOCATION = "/home/xizhilang/bank/";

    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(4040);
            System.out.println("服务端启动...等待连接");
            while (true){
                //一直接收客户端的请求并加入到线程中
                Socket client = server.accept();
                System.out.println("连接成功");
                inputStream=client.getInputStream();
                byte[] buffer=new byte[1024];
                int bytesRead=inputStream.read(buffer);
                message=new String(buffer,0,bytesRead);
                if(message.equals("0")){
                    ServerThread sthread = new ServerThread(client);
                    sthread.start();
                }else if(message.equals("1")){
                    ServerThreadXAdmin threadXAdmin = new ServerThreadXAdmin(client);
                    threadXAdmin.start();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
