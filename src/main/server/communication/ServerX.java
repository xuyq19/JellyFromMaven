package main.server.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerX {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket client = serverSocket.accept();
            ServerThreadX serverThread = new ServerThreadX(client);
            serverThread.start();
        }
    }
}
