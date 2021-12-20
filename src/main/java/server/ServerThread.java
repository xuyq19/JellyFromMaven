package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    public Socket socket;
    public InputStream inputStream;
    public OutputStream outputStream;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            String message = "Welcome to LZUOSS";
            sendMessage(outputStream, message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(OutputStream outputStream, String message) {
        try {
            outputStream.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
