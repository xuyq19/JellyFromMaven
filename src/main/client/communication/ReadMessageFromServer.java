import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ReadMessageFromServer {
    /*
        This class is designed to receive and read message from server.
     */
    InputStream inputStream;

    public ReadMessageFromServer(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String readMessageFromServer() throws Exception {
        /*
            This method is designed to read message from server.
         */
        int value = inputStream.read();
        String str = "";
        while (value != 10) {
            if (value == -1) {
                throw new Exception("Server has closed the connection.");
            }
            str += (char) value;
            value = inputStream.read();
        }
        str = str.trim();
        return str;
    }
}
