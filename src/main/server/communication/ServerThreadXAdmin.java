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
            /**
             * receive file stream from the client, and save it in the server
             */
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String message = new String(buffer, 0, bytesRead);
            String[] str = message.split(" ");
            String fileName = str[0];
            /**
             * create  the file path if not exist
             */
            String filePath = "/home/xizhilang/bank/" + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            /**
             * receive the file stream from the client
             */
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (true) {
                bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            fileOutputStream.close();
            outputStream.write("success".getBytes());
            main.server.fileio.excel.Reader.readAll(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public static void exportData() {
        try {
            main.server.admin.Functions.exportData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            outputStream = socket.getOutputStream();
            File file = new File("/home/xizhilang/bank/exportData.xlsx");
            InputStream inputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream("/home/xizhilang/bank/exportData.xlsx");
            /**
             * 发送文件给客户端
             */
            int len=-1;
            byte[] buffer = new byte[1024];
            while ((len=inputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void conclusion() throws IOException {
        try {
            main.server.admin.Functions.conclude();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * send file to client;
         */
        File file = new File("/home/xizhilang/bank/conclusion.pdf");
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = socket.getOutputStream();

        }
    }