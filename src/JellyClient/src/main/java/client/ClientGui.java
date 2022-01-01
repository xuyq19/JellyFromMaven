package client;

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
        try {
            System.out.println("connect to the remote server" + "123.60.106.3" +",port" + 4040);
            Socket client = new Socket("123.60.106.3", 4040);
            System.out.println("remote server：" + client.getRemoteSocketAddress());

            new root(client);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
