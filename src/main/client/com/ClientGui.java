package main.client.com;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientGui {
    static OutputStream out2server = null;
    static InputStream infromserver = null;
    static Scanner keyin = new Scanner(System.in);
    static String  ClientAcc;
    static String ClientPassword;
    static fmResident thisResident = null;
    public static void main(String[] args) {
//        String servername = args[0];
//        int port = Integer.parseInt(args[1]);

        try {
//            System.out.println("连接到主机" + servername +",端口为" + port);
            Socket client = new Socket("127.0.0.1", 4040);
            System.out.println("远程主机地址：" + client.getLocalAddress());

            new root(client);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
