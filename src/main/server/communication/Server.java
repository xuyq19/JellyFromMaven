package main.server.communication;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread {
    private ServerSocket serverSocket;
    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Server(){
        try{
            this.serverSocket = new ServerSocket(8080);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        while(true){
            try{
                new ServerThread(serverSocket.accept()).start();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
