package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class modify extends JFrame implements ActionListener{

    JButton bu1,bu2,bu3;  //创建按钮
    JLabel jlb1;

    Socket client = null;
    OutputStream out2server = null;
    DataOutputStream out = null;
    InputStream infromserver = null;
    DataInputStream in = null;
    String ClientAcc = null;
    String ClientPassword = null;

    public modify(Socket client, String ClientAcc, String ClientPassword)
    {
        this.client = client;
        this.ClientAcc = ClientAcc;
        this.ClientPassword = ClientPassword;
        bu1 = new JButton("username");//开户
        bu2 = new JButton("password");//修改信息
        bu3 = new JButton("phone");//销户


        bu1.addActionListener(this);   //事件监听
        bu2.addActionListener(this);
        bu3.addActionListener(this);


        jlb1 = new JLabel("please select:");

        this.setTitle("modify message");  //设置窗体标题
        this.setSize(400, 500);         //设置窗体大小
        this.setLocation(400, 200);     //设置位置
        this.setLayout(null);           //设置布局，不采用布局


        //设置按钮的位置和大小
        bu1.setBounds( 40,250,100,30);
        bu2.setBounds( 150,250,100,30);
        bu3.setBounds( 260,250,100,30);

        //设置标签的位置和大小
        jlb1.setBounds(100,120,150,50);
        jlb1.setFont(new   java.awt.Font("Dialog",   1,   23));

        //加入窗体
        this.add(bu1);
        this.add(bu2);
        this.add(bu3);
        this.add(jlb1);

        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口
        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小

        try {
            out2server = client.getOutputStream();
            out = new DataOutputStream(out2server);
            infromserver = client.getInputStream();
            in = new DataInputStream(infromserver);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("username"))
        {
            new md1(client);
        }
        else if (e.getActionCommand().equals("password"))
        {
            new md2(client,ClientAcc,ClientPassword);
        }
        else if (e.getActionCommand().equals("phone"))
        {
            new md3(client);
        }
    }
    public void sendMsg2server(OutputStream os,String s)throws IOException {
        byte[] bytes = s.getBytes();
        os.write(bytes);
        os.write(13);
        os.write(10);
        os.flush();
    }
    public String readMegfserver(InputStream ins)throws Exception{
        int value = ins.read();
        String str = "";
        while (value != 10){
            if(value == -1){
                throw new Exception();
            }
            str = str + ((char)value);
            value = ins.read();
        }
        str = str.trim();
        return str;
    }
}
