package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class menu extends JFrame implements ActionListener{
    JButton bu1,bu2,bu3,bu4,bu5,bu6,bu7,bu8;  //创建按钮
    JLabel jlb1;

    Socket client = null;
    OutputStream out2server = null;
    DataOutputStream out = null;
    InputStream infromserver = null;
    DataInputStream in = null;

    String ClientAcc = null;
    String ClientPassword = null;
    Scanner keyin = new Scanner(System.in);
    fmResident thisResident = null;

    public menu(Socket client, String ClientAcc, String ClientPassword)
    {
        this.client = client;
        this.ClientAcc = ClientAcc;
        this.ClientPassword = ClientPassword;
        bu1 = new JButton("adduser");//开户
        bu2 = new JButton("modify");//修改信息
        bu3 = new JButton("deluser");//销户
        bu4 = new JButton("withdraw");//取钱
        bu5 = new JButton("deposit");//存钱
        bu6 = new JButton("transform");//转账
        bu7 = new JButton("cheakmoney");//查余额
        bu8 = new JButton("exit");//退出
        jlb1 = new JLabel("Please select service:");

        bu1.addActionListener(this);   //事件监听
        bu2.addActionListener(this);
        bu3.addActionListener(this);
        bu4.addActionListener(this);
        bu5.addActionListener(this);
        bu6.addActionListener(this);
        bu7.addActionListener(this);
        bu8.addActionListener(this);


        this.setTitle("Feima Bank");  //设置窗体标题
        this.setSize(600, 500);         //设置窗体大小
        this.setLocation(400, 200);     //设置位置
        this.setLayout(null);           //设置布局，不采用布局


        //设置按钮的位置和大小
        bu1.setBounds( 65,200,110,30);
        bu2.setBounds( 185,200,110,30);
        bu3.setBounds( 305,200,110,30);
        bu4.setBounds(425,200,110,30);
        bu5.setBounds( 65,260,110,30);
        bu6.setBounds( 185,260,110,30);
        bu7.setBounds( 305,260,110,30);
        bu8.setBounds( 425,260,110,30);

        //设置标签的位置和大小
        jlb1.setBounds(150,100,300,50);
        jlb1.setFont(new   java.awt.Font("Dialog",   1,   23));
        //加入窗体
        this.add(bu1);
        this.add(bu2);
        this.add(bu3);
        this.add(bu4);
        this.add(bu5);
        this.add(bu6);
        this.add(bu7);
        this.add(bu8);
        this.add(jlb1);

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
        try {
            switch (e.getActionCommand()){
                case "adduser":{
                    sendMsg2server(out2server,"insert");
                    new client.add(client);
                    break;
                }
                case "modify":{
                    new modify(client,ClientAcc,ClientPassword);
                    break;
                }
                case "deluser":{
                    new deluser(client);
                    break;
                }
                case "withdraw":{
                    new withdraw(client,ClientAcc);
                    break;
                }
                case "deposit":{
                    new deposit(client,ClientAcc);
                    break;
                }
                case "transform":{
                    new transform(client,ClientAcc);
                    break;
                }
                case "cheakmoney":{
                    new checkmoney(client,ClientAcc);
                    break;
                }
                case "exit":{
                    dispose();
                    new root(client);
                }
            }
        }catch (IOException e1){
            e1.printStackTrace();
        }catch (Exception e1){
            e1.printStackTrace();
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
