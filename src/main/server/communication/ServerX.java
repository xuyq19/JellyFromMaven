package main.server.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static main.server.Main.SERVER_PORT;

public class ServerX {
    public static void main() throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        while (true) {
            Socket client = serverSocket.accept();
            ServerThreadX serverThread = new ServerThreadX(client);
            serverThread.start();
        }
    }
}
