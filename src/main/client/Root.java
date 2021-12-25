package main.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Root {
    static OutputStream out2server = null;
    static InputStream infromserver = null;
    static Scanner keyin = new Scanner(System.in);
    static String  ClientAcc;
    static String ClientPassword;
    final static String SERVER_ADDRESS = "219.246.65.67";
    final static int PORT=8080;
    static fmResident thisResident = null;
    public static void main(String[] args) {
//        String servername = args[0];
//        int port = Integer.parseInt(args[1]);

        try {
//            System.out.println("连接到主机" + servername +",端口为" + port);
            Socket client = new Socket(SERVER_ADDRESS, PORT);
            System.out.println("远程主机地址：" + client.getLocalAddress());

            new root(client);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static class OutputStream {
    }
}
