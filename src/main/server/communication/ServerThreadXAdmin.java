package main.server.communication;

import java.io.*;
import java.net.Socket;

public class ServerThreadXAdmin extends Thread {
    private static Socket socket;
    private static InputStream inputStream;
    private static OutputStream outputStream;
    @Override
    public void run(){
        try {
            inputStream=socket.getInputStream();
            outputStream=socket.getOutputStream();
            String message="Welcome";
            outputStream.write(message.getBytes());
            outputStream.flush();

            if(login()){
                while (true) {

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
        inputStream =socket.getInputStream();
        outputStream=socket.getOutputStream();
        byte[] buffer=new byte[1024];
        int bytesRead = inputStream.read(buffer);
        message=new String(buffer,0,bytesRead);
        if("login".equals(message)){
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
                message="success";
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
            main.server.fileio.excel.Reader.read(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static  void exportData(){
        /**
         * flush the message and outputStream
         */
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * export to excel
         */
        try {
            main.server.admin.Functions.exportData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file0 = new File("/home/xizhilang/bank/output.xlsx");
        OutputStream outputStream1 = null;
        try {
            outputStream1 = new FileOutputStream(file0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buffer0 = new byte[1024];
        int length = 0;
        while (true) {
            try {
                if (!((length = inputStream.read(buffer0)) != -1)) {
                    break;}
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream1.write(buffer0, 0, length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream outputStream2 = null;
        try {
            outputStream2 = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream2.write("Success".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void conclusion() throws IOException {
        try {
            main.server.fileio.pdf.Export.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file1 = new File("/home/xizhilang/bank/conclusion.pdf");
        OutputStream outputStream3 = null;
        try {
            outputStream3 = new FileOutputStream(file1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buffer2 = new byte[1024];
        int length1=0;
        while (true) {
            try {
                if (!((length1 = inputStream.read(buffer2)) != -1)) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            outputStream3.write(buffer2, 0, length1);
        }
        OutputStream outputStream4 = null;
        try {
            outputStream4 = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream4.write("Success".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}