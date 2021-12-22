package main.server.connection;
import java.io.IOException;
import java.net.*;

public class Connector {
    public  Connector() {}
    public void main(String[] args)
    {
        /**
         * Connect to the client
         */
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            while (clientSocket != null) {
                clientSocket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientSocket);
                serverThread.start();
            }

        }
        catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
