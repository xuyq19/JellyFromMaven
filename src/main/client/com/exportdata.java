package main.client.com;
import java.net.*;
import java.io.*;
import java.util.*;
public class exportdata {
    Socket socket=null;
    public exportdata(Socket socket) throws IOException {
        this.socket=socket;
    }
    /**
     * use the socket to download data from server
     */
    static InputStream inputStream=null;
    static OutputStream outputStream=null;
    static FileOutputStream fileOutputStream=null;
    public static void exportdata(Socket socket) throws IOException {
        /**
         * send message to server
         */
        outputStream=socket.getOutputStream();
        String message="exportData";
        outputStream.write(message.getBytes());
        outputStream.flush();
        inputStream=socket.getInputStream();
        outputStream=socket.getOutputStream();
        /**
         * get current directory
         */
        String currentDirectory=System.getProperty("user.dir");
        fileOutputStream=new FileOutputStream(currentDirectory+"exportdata.xls");
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=inputStream.read(buffer))!=-1){
            fileOutputStream.write(buffer,0,len);
        }
        fileOutputStream.close();
        inputStream.close();
        outputStream.close();
    }
}
