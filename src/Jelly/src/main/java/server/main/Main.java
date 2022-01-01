package server.main;

import server.admin.communication.ServerThreadXAdmin;
import server.user.ServerThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @apiNote still need to debug->fake muitple thread handler->which part print the "error".
 */
public class Main implements Runnable {
    private static InputStream inputStream;
    private OutputStream outputStream;
    private static String message;
    public static final String DATABASE_NAME = "bank";
    public static final String TABLE_NAME = "bank_accounts";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/'DATABASE_NAME'";
    public static final String PASS = "123456";
    public static final int SERVER_PORT = 4040;
    public static final int USER_ID_LENGTH = 10;
    public static final int USER_REAL_ID_LENGTH = 12;
    public static final int USER_NAME_MAX_LENGTH = 10;
    public static final int USER_PASS_MIN_LENGTH = 4;
    public static final String ADMIN_USER_NAME = "admin";
    public static final String ADMIN_PASSWORD = "123456";
    public static final String FILE_LOCATION = "/home/xizhilang/bank/";

    static Socket socket;

    Main(Socket socket) {
        this.socket = socket;
    }

    public static void main(String args[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is running on port " + SERVER_PORT + "listening...");
            while (true) {

                try {
                    Socket socket = serverSocket.accept();
                    //if sockrt is not null,then start a handle thread
                    if (!socket.isClosed()) {
                        System.out.println("A new client is connected");
                        new Thread(new Main(socket)).start();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
        public void run () {
            try {
                System.out.println("starting server,waiting for connection...");
                while (true) {
                    //Always accepting client request and start a thread to handle
                    System.out.println("client connected");
                    inputStream = socket.getInputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead = inputStream.read(buffer);
                    message = new String(buffer, 0, bytesRead);
                    switch (message) {
                        case "0" : {
                            System.out.println("message:choose user");
                            ServerThread sthread = new ServerThread(socket);
                            sthread.start();
                        }
                        case "1" : {
                            System.out.println("message:choose admin");
                            ServerThreadXAdmin threadXAdmin = new ServerThreadXAdmin(socket);
                            threadXAdmin.start();
                        }
                        default : {
                            System.out.println("message:exit or error");
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
