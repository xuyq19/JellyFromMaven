package main.server.communication;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.net.Socket;

public class ServerThreadXAdmin extends Thread {
    private static Socket socket;
    private static InputStream inputStream;
    private static OutputStream outputStream;

    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            String message = "Welcome";
            outputStream.write(message.getBytes());
            outputStream.flush();

            if (login()) {
                while (true) {
                    choose();
                }
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
        boolean loginFlag = false;
        boolean adminFlag = false;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        message = new String(buffer, 0, bytesRead);
        if ("login".equals(message)) {
            try {
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                buffer = new byte[1024];
                bytesRead = inputStream.read(buffer);
                message = new String(buffer, 0, bytesRead);
                String[] str = message.split(" ");
                String userId = str[0];
                String password = str[1];
                loginFlag = main.server.database.ReadSingle.checkInformation(userId, password);
                adminFlag = main.server.admin.Functions.isAdmin(userId, password);
                message = "success";
                outputStream.write(message.getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return adminFlag;
    }

    public static void choose() {
        while (true) {
            try {
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String message = new String(buffer, 0, bytesRead);
                switch (message) {
                    case "importData": {
                        importData();
                        break;
                    }
                    case "exportData": {
                        exportData();
                        break;
                    }
                    case "conclusion": {
                        conclusion();
                        break;
                    }
                    default:
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void importData() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("/home/xizhilang/bank/importData.xlsx",true);
            /**
             * read String message from input stream
             */
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String message = new String(buffer, 0, bytesRead);
            if (message.equals("importData")) {
                /**
                 * start to receive file input stream
                 */
                inputStream = socket.getInputStream();
                int length = 0;
                buffer = new byte[1024];
                while ((length = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, length);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                message = "success";
                outputStream.write(message.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            main.server.admin.Functions.importData("/home/xizhilang/bank/importData.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public static void exportData() {
        try {
            main.server.admin.Functions.conclude();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            outputStream = socket.getOutputStream();
            File file = new File("/home/xizhilang/bank/exportData.xlsx");
            InputStream inputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream("/home/xizhilang/bank/exportData.xlsx", true);
            /**
             * 发送文件给客户端
             */
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String message = new String(buffer, 0, bytesRead);
            if (message.equals("exportDataSuccess")) {
                inputStream.close();
                outputStream.flush();
                outputStream.close();
            }
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void conclusion() throws IOException {
        try {
            main.server.admin.Functions.exportData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            outputStream = socket.getOutputStream();
            File file = new File("/home/xizhilang/bank/conclusion.pdf");
            InputStream inputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream("/home/xizhilang/bank/conclusion.pdf", true);
            /**
             * 发送文件给客户端
             */
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
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

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
