package client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import javax.swing.*;


public class root extends JFrame implements ActionListener {
    private static OutputStream outputStream;
    private InputStream inputStream;
    private static String message = null;

    JButton bu1, bu2, bu3;
    //创建按钮
    JLabel jlb1;
    Socket client = null;
    private static OutputStream out2server = null;
    private static DataOutputStream out = null;
    private static InputStream infromserver = null;
    private static DataInputStream in = null;

    public root (Socket client) {
        System.out.println("message:connect to server");
        this.client = client;
        try {
            out2server = client.getOutputStream();
            out = new DataOutputStream(out2server);
            infromserver = client.getInputStream();
            in = new DataInputStream(infromserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bu1 = new JButton("user");
        bu2 = new JButton("administrator");
        bu3 = new JButton("exit");
        jlb1 = new JLabel("Please select your identity:");

        bu1.addActionListener(this);
        //事件监听
        bu2.addActionListener(this);
        bu3.addActionListener(this);


        this.setTitle("Feima Bank");  //设置窗体标题
        this.setSize(600, 500);         //设置窗体大小
        this.setLocation(400, 200);     //设置位置
        this.setLayout(null);           //设置布局，不采用布局


        //设置按钮的位置和大
        bu1.setBounds(65, 250, 170, 30);
        bu2.setBounds(365, 250, 170, 30);
        bu3.setBounds(200, 300, 170, 30);

        //设置标签的位置和大小
        jlb1.setBounds(150, 100, 300, 50);
        jlb1.setFont(new java.awt.Font("Dialog", 1, 23));
        //加入窗体
        this.add(bu1);
        this.add(bu2);
        this.add(bu3);
        this.add(jlb1);
        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小
    }


    @Override
    public void actionPerformed (ActionEvent e) {

        if (e.getActionCommand().equals("user")) {
            try {
                outputStream = client.getOutputStream();
                byte[] buffer = new byte[1024];
                buffer = "0".getBytes();
                message = new String(buffer);
                outputStream.write(message.getBytes());
                outputStream.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            dispose();
            new user(client);

        } else if (e.getActionCommand().equals("administrator")) {
            try {
                outputStream = client.getOutputStream();
                byte[] buffer = new byte[1024];
                buffer = "1".getBytes();
                message = new String(buffer);
                outputStream.write(message.getBytes());
                outputStream.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            dispose();
            new administrator(client);
        } else if (e.getActionCommand().equals("exit")) {
            try {
                client.close();
                dispose();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void sendMsg2server (OutputStream os, String s) throws IOException {
        byte[] bytes = s.getBytes();
        os.write(bytes);
        os.write(13);
        os.write(10);
        os.flush();
    }

    public static String readMegfserver (InputStream ins) throws Exception {
        int value = ins.read();
        String str = "";
        while (value != 10) {
            if (value == -1) {
                throw new Exception();
            }
            str = str + ((char) value);
            value = ins.read();
        }
        str = str.trim();
        return str;
    }


}
