package main.client.com;
import java.net.*;
import java.io.*;
import java.util.*;
public class conclusion {
    /**
     * use the socket to download data from server
     */
    static InputStream inputStream=null;
    static OutputStream outputStream=null;
    static FileOutputStream fileOutputStream=null;
    public  void conclude(Socket socket) throws IOException {
        /**
         * delete if the file existed
         */
        File file=new File("/home/xizhilang/bank/conclusion.pdf");
        if(file.exists()) {
            file.delete();
        }
        /**
         * send message to server
         */
        outputStream=socket.getOutputStream();
        String message="conclude";
        outputStream.write(message.getBytes());
        outputStream.flush();
        inputStream=socket.getInputStream();
        outputStream=socket.getOutputStream();
        /**
         * get current directory
         */
        String currentDirectory=System.getProperty("user.dir");
        fileOutputStream=new FileOutputStream("/home/xizhilang/bank/conclusion.pdf");
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=inputStream.read(buffer))!=-1){
            fileOutputStream.write(buffer,0,len);
        }
        message="concludeSuccess";
        OutputStream outputStream=socket.getOutputStream();
        outputStream.write(message.getBytes());
        outputStream.flush();
        fileOutputStream.flush();
        fileOutputStream.close();
        inputStream.close();
        outputStream.close();
    }
}
