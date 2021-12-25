import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SendMessageToServer{
    OutputStream outputStream;
    String string;
    public SendMessageToServer(OutputStream outputStream, String string) {
        this.outputStream = outputStream;
        this.string = string;
    }
    public void sendMessageToServer(InputStream inputStream, String string) throws IOException {
        byte[] bytes = string.getBytes();
        outputStream.write(bytes);
        outputStream.write(13);
        outputStream.write(10);
        outputStream.flush();
    }
}
