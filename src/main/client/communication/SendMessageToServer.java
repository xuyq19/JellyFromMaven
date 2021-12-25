import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendMessageToServer {
    public static void sendMessageToServer(String message) {
        try {
            Socket socket = new Socket("localhost", 9999);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
