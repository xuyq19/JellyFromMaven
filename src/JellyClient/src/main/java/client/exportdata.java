package client;

import java.io.*;
import java.net.Socket;

public class exportdata {
    Socket socket = null;

    public exportdata (Socket socket) throws IOException {
        this.socket = socket;
    }

    /**
     * use the socket to download data from server
     */
    static InputStream inputStream = null;
    static OutputStream outputStream = null;
    static FileOutputStream fileOutputStream = null;

    public static void exportdata (Socket socket) throws IOException {
        /**
         * delete if the file existed
         */
        File file = new File("exportdata.xls");
        if (file.exists()) {
            file.delete();
        }
        /**
         * send message to server
         */

        outputStream = socket.getOutputStream();
        String message = "exportData";
        DataOutputStream dos = new DataOutputStream(outputStream);
        dos.writeUTF(message);
        dos.flush();
//        outputStream.write(message.getBytes());
//        outputStream.flush();
        inputStream = socket.getInputStream();
        //outputStream = socket.getOutputStream();
        /**
         * get current directory
         */
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Export Data Dir:" + currentDirectory);
        fileOutputStream = new FileOutputStream(currentDirectory + "exportdata.xlsx");
        System.out.println("message: start to download data");
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            System.out.println("Message:Reading data.Length is " + len);
            fileOutputStream.write(buffer, 0, len);
        }
        /**
         * exit the program
         */
        System.out.println("message: download data successfully");
        System.out.println("message: exit the program");
        outputStream.write(message.getBytes());
        outputStream.flush();
        fileOutputStream.close();
        inputStream.close();
        outputStream.close();
        System.exit(0);


    }
}
