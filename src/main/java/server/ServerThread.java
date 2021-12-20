package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    public static InputStream inputStream;
    public static OutputStream outputStream;
    private Socket socket;
    static String clientAccount=null;
    static String clientPassword=null;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    /**
     * This method is used to run the server thread
     */
    public void run()  {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            String message = "Welcome to FMBank";
            sendMessage(outputStream,message);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     *This method is used to mark wether the client is logged in or not
     */
    public static void isLogin(){
        String message = null;
        boolean isLogin = false;
        try {
            sendMessage(outputStream, "login");
            message="name";
            sendMessage(outputStream, message);
            clientAccount =readMessage(inputStream);
            message="password";
            sendMessage(outputStream, message);
            clientPassword = readMessage(inputStream);
            isLogin=loginChecker(clientAccount,clientPassword);
            sendMessage(outputStream, String.valueOf(isLogin));
        } catch (IOException e) {
            e.printStackTrace();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean loginChecker(String account,String password){
        boolean isLogin = false;
        try {
            sendMessage(outputStream, "loginChecker");
            sendMessage(outputStream, account);
            sendMessage(outputStream, password);
            isLogin = Boolean.valueOf(readMessage(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isLogin;
    }
    public static String readMessage(InputStream inputStream) throws IOException {
        int value = inputStream.read();
        String string="";
        while (value != 10) {
            if(value == -1){
                throw new IOException();
            }
            string += (char) value;
            value = inputStream.read();
        }
        string = string.trim();
        return string;

    }
    public static void sendMessage(OutputStream outputStream, String message) throws IOException {
        byte[] bytes= message.getBytes();
        outputStream.write(bytes);
        outputStream.write(13);
        outputStream.write(10);
        outputStream.flush();

    }

}
