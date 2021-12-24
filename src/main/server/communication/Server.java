package main.server.communication;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server{
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while(true){
            Socket client = serverSocket.accept();
            ServerThread serverThread = new ServerThread(client);
            serverThread.start();
        }
    }
}
