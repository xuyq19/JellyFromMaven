import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ReadMessageFromServer {
    /*
        This class is designed to receive and read message from server.
     */
    public static String readMessage(SocketChannel socketChannel){
        /*
            This method is designed to read message from server.
            @param socketChannel: the socket channel to read message from server.
            @return: the message from server.
         */
        try{
            // Create a new buffer to read message from server.
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // Read message from server.
            socketChannel.read(buffer);
            // Get the message from buffer.
            String message = new String(buffer.array());
            // Return the message.
            return message;
        }catch (Exception e){
            // If there is any error, return null.
            return null;
        }
    }
}
