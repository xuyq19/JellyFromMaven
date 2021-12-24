package main.server.communication;

import main.server.database.LoginCheck;
import main.server.user.User;

import java.io.IOException;
import java.net.*;
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
    public static void Login() throws IOException {
        String message = "";
        boolean loginFlag = false;
        boolean adminFlag = false;
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
            loginFlag= LoginCheck.login(userId,password);
            adminFlag= main.server.admin.Functions.isAdmin(userId,password);
            if(loginFlag){
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
                    /**
                     * Selections:
                     * 0: set the user's bank account name
                     * 1: set the user's bank account password
                     * 3: set the user's bank account phone number
                     * 4: set the user's bank account sex
                     * 5: set the user's bank account birth date
                     */
                    inputStream = socket.getInputStream();
                    buffer = new byte[1024];
                    bytesRead = inputStream.read(buffer);
                    message = new String(buffer, 0, bytesRead);
                    /**
                     * message = thing you want to change
                     */
                    int selection = Integer.parseInt(message);
                    inputStream = socket.getInputStream();
                    buffer = new byte[1024];
                    bytesRead = inputStream.read(buffer);
                    message = new String(buffer, 0, bytesRead);
                    String information = message;
                    /**
                     * send message to client
                     */
                    message = main.server.user.Functions.setUserInfo(user0,selection,information);
                    outputStream.write(message.getBytes());
                    /**
                     * flush the message and outputStream
                     */
                    outputStream.flush();
                }else if(message.equals(""))




            }else {
                message = "Login Failed";
            }
            outputStream.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
