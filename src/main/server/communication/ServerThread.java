package main.server.communication;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter writer = new PrintWriter(this.socket.getOutputStream(), true);
            String temp=null;
            String info="";
            while ((temp=reader.readLine())!=null){
                info+=temp;
                System.out.println(temp);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }