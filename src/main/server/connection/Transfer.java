package main.server.connection;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * @author xuyuqi
 * @date 2021-12-23
 * @describe This is a class used to transport data between client and server.
 */
public class Transfer extends ServerSocket{
    private ServerSocket serverSocket;

    public Transfer(ServerSocket serverSocket) throws IOException {
        super();
    }
}
