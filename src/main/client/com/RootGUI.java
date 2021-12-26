package main.client.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RootGUI extends JFrame implements ActionListener {
    public RootGUI(){
        this.setTitle("log in");
        this.setBounds(100,100,500,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        JButton button0 = new JButton("Connet to Server");
        JButton button1 = new JButton("Client login");
        JButton button2 = new JButton("Manager login");

//        this.add(button0);
        this.add(button1);
        this.add(button2);

        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jdbc_driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/fmbank?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
                String username = "root";
                String password = "210718";
                Connection conn = null;
                try{
                    //加载驱动
                    Class.forName(jdbc_driver);
                    //连接数据库
                    conn = DriverManager.getConnection(url, username, password);
                }catch(SQLException e1){
                    e1.printStackTrace();
                }catch(Exception e2){
                    e2.printStackTrace();
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client_login clientlogin_interface = new Client_login();

            }
        });
//        button2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Manager_login managerlogin_interface = new managerlogin_interface();
//            }
//        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("处理中");
    }
}
