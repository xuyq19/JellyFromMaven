package client;

import java.io.*;
import java.net.Socket;

public class conclusion {
    /**
     * use the socket to download data from server
     */
    static InputStream inputStream = null;
    static OutputStream outputStream = null;
    static FileOutputStream fileOutputStream = null;

    public void conclude (Socket socket) throws IOException {
        /**
         * testDir is current directory
         */
        String testDir = System.getProperty("user.dir");
        String acurallyDir = "/home/xizhilang/bank/";
        /**
         * delete if the file existed
         */
        File file = new File(testDir + "conclusion.pdf");
        if (file.exists()) {
            file.delete();
        }
        /**
         * send message to server
         */
        outputStream = socket.getOutputStream();
        String message = "conclusion";
        DataOutputStream dos = new DataOutputStream(outputStream);
        dos.writeUTF(message);
        dos.flush();
//        outputStream.write(message.getBytes());
//        outputStream.flush();
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        /**
         * get current directory
         */
        String currentDirectory = System.getProperty("user.dir");
        fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }
        message = "concludeSuccess";
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(message.getBytes());
        outputStream.flush();
        fileOutputStream.flush();
        fileOutputStream.close();
        inputStream.close();
        outputStream.close();
    }
}
