package server.admin.communication;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import com.graphbuilder.math.func.Function;
import server.admin.Functions;

import static java.lang.System.out;

public class ServerThreadXAdmin extends Thread {
    private static Socket socket;
    private static InputStream inputStream;
    private static OutputStream outputStream;

    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            if (login()) {
                System.out.println("Message:Login request");
                while (!socket.isClosed()) {
                    choose();
                }
            } else {
                /**
                 * send failed message to client
                 */
                outputStream.write("failed".getBytes());
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerThreadXAdmin(Socket socket) {
        ServerThreadXAdmin.socket = socket;
    }

    public static boolean isConnected() {
        return socket.isConnected();
    }

    public static boolean login() throws IOException {
        String message;
        boolean adminFlag = false;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        message = new String(buffer, 0, bytesRead);
        System.out.println("Message:transport login message | target:" + message + " | "
                + message.matches("^login\\s*\\d+\\s+\\d+$"));
        if (message.matches("^login\\s*\\d+\\s+\\d+$")) {
            try {
                System.out.println("Message:login request");
                message = message.replaceFirst("^login\\s*", "");
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                buffer = new byte[1024];
                //bytesRead = inputStream.read(buffer);
                //message = new String(buffer, 0, bytesRead);
                String[] str = message.split(" ");
                System.out.println("Message: messaggelogin:" + message);
                String userId = str[0];
                String password = str[1];
                adminFlag = server.admin.Functions.isAdmin(userId, password);
                if (adminFlag) {
                    message = "success";
                    outputStream.write(message.getBytes());
                    outputStream.flush();
                }
                System.out.println("Message:" + message);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("error");
            }
        }
        return adminFlag;
    }

    public static void choose() {
        System.out.println("choose");
	    //check if connected
        while (!socket.isClosed()) {
            try {
                /**
                 * receive message from client
                 */
                inputStream = socket.getInputStream();
                DataInputStream dis = new DataInputStream(inputStream);
                String message = dis.readUTF();
                System.out.println("Message:" + message);
                switch (message) {
                    case "importData": {
                        importData();
                        break;
                    }
                    case "exportData": {
                        System.out.println("MESSSAGE:" + "choose exportData");
                        exportData();
                        break;
                    }
                    case "conclusion": {
                        conclusion();
                        break;
                    }
                    default:
                        System.out.println("message:choose error.");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("error");
            }
        }
    }

    public static void importData() {
        try {
            System.out.println("MESSSAGE:" + "start importData");
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("/home/xizhilang/bank/importData.xlsx", true);
            /**
             * create excel file from inputStream
             */
            inputStream.close();
            inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                out.println("MESSSAGE:" + "create excel file from inputStream");
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            DataOutputStream dos = new DataOutputStream(outputStream);
            dos.writeUTF("exportDataSuccess");
            out.println("buffer size:" + bytesRead);
            dos.flush();
            dos.close();
            /**
             * close socket
             */


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }
        try {
            server.admin.Functions.importData("/home/xizhilang/bank/importData.xlsx");
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    public static void exportData() {
        try {
            System.out.println("Message:" + "exportData1");
            server.admin.Functions.exportData();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
        try {
            System.out.println("message: start output stream");
            outputStream = socket.getOutputStream();
            File file = new File("/home/xizhilang/bank/user.xlsx");
            InputStream inputStream = new FileInputStream(file);
            /**
             * 发送文件给客户端
             */
            int len = -1;
            byte[] buffer = new byte[1024];
            System.out.println("message: start output stream");
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            System.out.println("message: complete output stream");
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }

    }

    public static void conclusion() throws IOException {
        try {
            server.admin.Functions.conclude();
            server.admin.Functions.exportData();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
        try {
            outputStream = socket.getOutputStream();
            File file = new File("/home/xizhilang/bank/conclusion.pdf");
            InputStream inputStream = new FileInputStream(file);
            /**
             * 发送文件给客户端
             */
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String message = new String(buffer, 0, bytesRead);
            if (message.equals("concludeSuccess")) {
                inputStream.close();
                outputStream.flush();
                outputStream.close();
            }
            outputStream.flush();
            outputStream.close();
            System.out.println("Message:" + message);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }
}
