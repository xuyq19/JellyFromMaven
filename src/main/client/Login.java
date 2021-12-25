package main.client;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Login {
    private String username;
    private String password;

    public Login(Socket socket, String username, String password) {
        this.username = username;
        this.password = password;
    }
    main.client.communication.SendMessageToServer sendMessageToServer = new main.client.communication.SendMessageToServer(socket);
}

