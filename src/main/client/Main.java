package main.client;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.Socket;
public abstract class Main extends JFrame implements ActionListener{
    private Socket socket;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton loginButton;
    private JLabel userId;
    private JLabel password;
    private JLabel welcome;
    public Main(Socket socket){
        this.socket = socket;
        this.setTitle("Login");
        this.setSize(600,400);
        this.setLayout(null);
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");
        userId = new JLabel("User ID");
        password = new JLabel("Password");
        welcome = new JLabel("Welcome to the Bank");
        JTextField textField1 = new JTextField();
        JPasswordField passwordField1 = new JPasswordField();
        welcome.setBounds(150,50,300,30);
        userId.setBounds(150,100,100,30);
        password.setBounds(150,150,100,30);
        textField1.setBounds(250,100,200,30);
        passwordField1.setBounds(250,150,200,30);
        this.add(welcome);
        this.add(userId);
        this.add(password);
        this.add(textField1);
        this.add(passwordField1);
        this.add(registerButton);
        this.add(loginButton);
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() == registerButton){
            Register register = new Register(socket);
            register.setVisible(true);
            /**
             * send Message to server
             */
            messageToServer("register");
        }
        if(e.getSource() == loginButton){
            Login login = new Login(socket,userId.getText(),password.getText());
            login.setVisible(true);
        }
    }
}
