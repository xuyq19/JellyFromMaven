package main.server.communication;

import main.server.database.LoginCheck;
import main.server.user.User;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.io.InputStream;
import java.io.OutputStream;

public class ServerThread extends Thread {
    private static Socket socket;
    private static InputStream inputStream;
    private static OutputStream outputStream;
    public ServerThread(Socket socket) {
        ServerThread.socket = socket;
    }
    @Override
    public void run() {
        try{
            /**
             * get the input and output streams
             */
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            /**
             * send the message to the client
             */
            String message = "Welcome to the server";
            outputStream.write(message.getBytes());
            /**
             * read the message from the client
             */
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String clientMessage = new String(buffer, 0, bytesRead);
            System.out.println("Client message: " + clientMessage);
            /**
             * close the connection
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isConnected() {
        return socket.isConnected();
    }
    public static String Login() throws IOException {
        String message = "";
        boolean flag = false;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            message = new String(buffer, 0, bytesRead);
            /**
             * 从message提取用户id和密码
             */
            String[] str = message.split(" ");
            String userId = str[0];
            String password = str[1];
            flag= LoginCheck.login(userId,password);
            if(flag) {
                /**
                 * return user from database
                 */
                User user0 = main.server.database.ReadSingle.returnUser(userId);
                message = "Login Success";
                /**
                 * send message to client
                 */
                outputStream.write(message.getBytes());
                /**
                 * flush the message and outputStream
                 */
                outputStream.flush();
                /**
                 * Choossing the next step
                 */
                message = "";
                inputStream = socket.getInputStream();
                message = inputStream.toString();
                if(message.equals("transferToBankAccount")) {
                    /**
                     * send message to client
                     */
                    message="Please input the account number you want to transfer";
                    outputStream.write(message.getBytes());
                    /**
                     * flush the message and outputStream
                     */
                    outputStream.flush();
                    /**
                     * input the account number
                     */
                    inputStream = socket.getInputStream();
                    buffer = new byte[1024];
                    bytesRead = inputStream.read(buffer);
                    message = new String(buffer, 0, bytesRead);
                    /**
                     * message = account number you want to transfer
                     */
                    String bankAccountUserId = message;
                    /**
                     * start transfer
                     */
                    message = "Please input the amount you want to transfer";
                    outputStream.write(message.getBytes());
                    /**
                     * flush the message and outputStream
                     */
                    outputStream.flush();
                    /**
                     * input the amount
                     */
                    inputStream = socket.getInputStream();
                    buffer = new byte[1024];
                    bytesRead = inputStream.read(buffer);
                    message = new String(buffer, 0, bytesRead);
                    /**
                     * message = amount you want to transfer
                     */
                    String amountString = message;
                    /**
                     * translate the amount to double
                     */
                    double amount = Double.parseDouble(amountString);

                    /**
                     * start transfer
                     */
                    main.server.user.Functions.transferToBankAccount(user0,bankAccountUserId,amount);
                    /**
                     * send message to client
                     */
                    message = "Transfer Success";
                    outputStream.write(message.getBytes());
                    /**
                     * flush the message and outputStream
                     */
                    outputStream.flush();
                } else if(message.equals("setUserInfo")) {
                    /**
                     * input thing you want to change
                     */
                    inputStream = socket.getInputStream();
                    buffer = new byte[1024];
                    bytesRead = inputStream.read(buffer);
                    message = new String(buffer, 0, bytesRead);
                    /**
                     * message = thing you want to change
                     */
                    int selection = Integer.parseInt(message);
                    main.server.user.Functions.setUserInfo(user0,selection);
                    /**
                     * input the information you want to change
                     */
                    inputStream = socket.getInputStream();
                    buffer = new byte[1024];
                    bytesRead = inputStream.read(buffer);
                    message = new String(buffer, 0, bytesRead);
                    /**
                     *
                     */


                }



            }else {
                message = "Login Failed";
            }
            outputStream.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
