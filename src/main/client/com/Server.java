package main.client.com;
import com.mysql.cj.x.protobuf.MysqlxNotice;
import main.server.communication.ServerThreadXAdmin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static InputStream inputStream;
    private OutputStream outputStream;
    private static String message;

    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(4040);
            System.out.println("服务端启动...等待连接");
            while (true){
                //一直接收客户端的请求并加入到线程中
                Socket client = server.accept();
                System.out.println("连接成功");
                inputStream=client.getInputStream();
                byte[] buffer=new byte[1024];
                int bytesRead=inputStream.read(buffer);
                message=new String(buffer,0,bytesRead);
                if(message.equals("0")){
                    ServerThread sthread = new ServerThread(client);
                    sthread.start();
                }else if(message.equals("1")){
                    ServerThreadXAdmin threadXAdmin = new ServerThreadXAdmin(client);
                    threadXAdmin.start();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
