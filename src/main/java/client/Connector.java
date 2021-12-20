package client;
import java.util.*;
import java.io.*;
import java.net.*;
import client.User;

/**
 * @author xuyuqi
 */
public class Connector {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("cloud.lzuoss.club", 8888);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("hello\n");
            bufferedWriter.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String message = bufferedReader.readLine();
            System.out.println("Server" + message);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
