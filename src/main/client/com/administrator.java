package main.client.com;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.*;


public class administrator extends JFrame implements ActionListener{

    JButton bu1;  //按钮
    JLabel jlb1, jlb2,jlb3;  //标签
    JTextField jtf1,jtf2;   //文本框
    JPanel jp1,jp2,jp3,jp4;        //面板
    Socket client = null;
    OutputStream outputStream = null;
    InputStream inputStream = null;
    public static String message;
    public administrator(Socket client) {
        this.client = client;
        //按钮
        bu1 = new JButton("sure");
        //设置按钮监听
        bu1.addActionListener(this);
        //标签信息

        jlb1 = new JLabel("accountid");
        jlb2 = new JLabel("password ");
        jlb3= new JLabel("please input accountid and password");
        jlb3.setFont(new   java.awt.Font("Dialog",   1,   21));

        //文本信息
        jtf1 = new JTextField(13);
        jtf2 = new JPasswordField(13);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4= new JPanel();

        //将对应信息加入面板中
        jp1.add(jlb3);

        jp2.add(jlb1);
        jp2.add(jtf1);

        jp3.add(jlb2);
        jp3.add(jtf2);

        jp4.add(bu1);


        //将JPane加入JFrame中
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        //设置布局
        this.setTitle("register");
        this.setLayout(new GridLayout(4, 1));
        this.setSize(600, 500);   //设置窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand()=="sure")
        {
            try {
                outputStream = client.getOutputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            buffer = "login".getBytes();
            message=new String(buffer);
            try {
                outputStream.write(message.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                outputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String account = jtf1.getText();
            String password = jtf2.getText();
            String message =account+" "+password;
            try {
                outputStream = client.getOutputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            buffer = new byte[1024];
            buffer = message.getBytes();
            message=new String(buffer);
            try {
                outputStream.write(message.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                outputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try{
                inputStream = client.getInputStream();
                buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                message = new String(buffer, 0, bytesRead);
                if(message.equals("success"))
                {
                    dispose();
                    new menu2(client);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
