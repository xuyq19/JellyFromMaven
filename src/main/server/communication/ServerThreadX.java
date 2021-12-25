package main.server.communication;

import main.server.database.LoginCheck;
import main.server.user.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.net.*;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author xuyuq
 */
public class ServerThreadX extends Thread {
    private static Socket socket;
    private static InputStream inputStream;
    private static OutputStream outputStream;

    public ServerThreadX(Socket socket) {
        ServerThreadX.socket = socket;
    }

    @Override
    public void run() {
        try {
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
            loginFlag = main.server.database.ReadSingle.checkInformation(userId, password);
            adminFlag = main.server.admin.Functions.isAdmin(userId, password);
            if (loginFlag) {
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
                if(main.server.admin.Functions.isAdmin(userId, password)){
                    message = "admin login, please choose the admin function,or press '0' to exit to user function";
                    outputStream.write(message.getBytes());
                    outputStream.flush();
                    while(true){
                        inputStream = socket.getInputStream();
                        message = inputStream.toString();
                        if("0".equals(message)){
                            break;
                        }
                        /**
                         * message = "importFromExcel" means import from excel
                         * message = "exportToExcel" means export to excel
                         * message = "conclude" means conclude
                         */
                        switch (message){
                            case "importData":
                                /**
                                 * flush the message and outputStream
                                 */
                                outputStream.flush();
                                /**
                                 * messsaage = file name
                                 */
                                inputStream = socket.getInputStream();
                                message = inputStream.toString();
                                /**
                                 * import from excel
                                 */
                                main.server.admin.Functions.importData(message);
                                break;
                            case "exportData":
                                /**
                                 * flush the message and outputStream
                                 */
                                outputStream.flush();
                                /**
                                 * export to excel
                                 */
                                main.server.admin.Functions.exportData();
                                break;
                            case "conclude":
                                /**
                                 * flush the message and outputStream
                                 */
                                outputStream.flush();
                                /**
                                 * conclude
                                 */
                                main.server.admin.Functions.conclude();
                                break;
                            default:
                                return;
                        }


                    }
                }
                switch (message) {
                    case "transferToBankAccount":
                        /**
                         * send message to client
                         */
                        message = "Please input the account number you want to transfer";
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
                        main.server.user.Functions.transferToBankAccount(user0, bankAccountUserId, amount);
                        /**
                         * send message to client
                         */
                        message = "Transfer Success";
                        outputStream.write(message.getBytes());
                        /**
                         * flush the message and outputStream
                         */
                        outputStream.flush();
                        break;
                    case "setUserInfo":
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
                        message = main.server.user.Functions.setUserInfo(user0, selection, information);
                        outputStream.write(message.getBytes());
                        /**
                         * flush the message and outputStream
                         */
                        outputStream.flush();
                        break;
                    case "depositMoney":
                        /**
                         * input the amount you want to deposit
                         */
                        inputStream = socket.getInputStream();
                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        message = new String(buffer, 0, bytesRead);
                        /**
                         * message = amount you want to deposit
                         */
                        double depositAmount = Double.parseDouble(message);
                        /**
                         * start deposit
                         */
                        main.server.user.Functions.depositMoney(user0, depositAmount);
                        /**
                         * send message to client
                         */
                        message = "Deposit Success";
                        outputStream.write(message.getBytes());
                        /**
                         * flush the message and outputStream
                         */
                        outputStream.flush();
                        break;
                    case "withdrawMoney":
                        /**
                         * input the amount you want to withdraw
                         */
                        inputStream = socket.getInputStream();
                        buffer = new byte[1024];
                        bytesRead = inputStream.read(buffer);
                        message = new String(buffer, 0, bytesRead);
                        /**
                         * message = amount you want to withdraw
                         */
                        double withdrawAmount = Double.parseDouble(message);
                        /**
                         * start withdraw and send message to client
                         */
                        message = main.server.user.Functions.withdrawMoney(user0, withdrawAmount) + "and now the balance is " + user0.getBankAccountBalance();
                        outputStream.write(message.getBytes());
                        /**
                         * flush the message and outputStream
                         */
                        outputStream.flush();
                        break;
                    case "getBankAccountInfo":
                        /**
                         * send message to client
                         */
                        message = main.server.user.Functions.getBankAccountInfo(user0);
                        outputStream.write(message.getBytes());
                        /**
                         * flush the message and outputStream
                         */
                        outputStream.flush();
                        break;
                    default:
                        /**
                         * send message to client
                         */
                        message = "Invalid Input";
                        outputStream.write(message.getBytes());
                        /**
                         * flush the message and outputStream
                         */
                        outputStream.flush();
                        break;
                }
            }else{
                message = "Login Failed";
            }
            outputStream.write(message.getBytes());
    } catch(
                IOException | InvalidFormatException e)

    {
        e.printStackTrace();
    } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
