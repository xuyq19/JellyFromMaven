package main.server.connection;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    private Socket socket;
    public ServerThread(Socket socket) throws IOException {
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));} catch (IOException ioException) {
            String line=bufferedReader.readLine();
            System.out.println(line);
            bufferedReader.close();
            socket.close();
        } catch(Exception e){
            e.printStackTrace();
        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}
