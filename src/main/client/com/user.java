package main.client.com;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;


public class user extends JFrame implements ActionListener{

    JButton bu1;  //按钮
    JLabel jlb1, jlb2,jlb3;  //标签
    JTextField jtf1,jtf2;   //文本框
    JPanel jp1,jp2,jp3,jp4;        //面板
    Socket client = null;
    OutputStream out2server = null;
    DataOutputStream out = null;
    InputStream infromserver = null;
    DataInputStream in = null;


    String ClientAcc = null;
    String ClientPassword = null;
    Scanner keyin = new Scanner(System.in);
    fmResident thisResident = null;


    public user(Socket client) {
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
        jtf2 = new JTextField(13);

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
        if (e.getActionCommand()=="sure")
        {
            try {
                while(true){
                    String req = readMegfserver(in);
                    if(req.equals("login")){
                        if(login()) {
                            if(readMegfserver(infromserver).equals("choose")) {
                                new menu(client,ClientAcc,ClientPassword);
                            }
                        }
                        break;
                    }
                }

            }catch (IOException e1){
                e1.printStackTrace();
            }catch (Exception e2){
                e2.printStackTrace();
            }
            dispose();
        }
    }

    public static void sendMsg2server(OutputStream os,String s)throws IOException{
        byte[] bytes = s.getBytes();
        os.write(bytes);
        os.write(13);
        os.write(10);
        os.flush();
    }
    public static String readMegfserver(InputStream ins)throws Exception{
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
    public boolean login(){
        boolean flag = false;
        String massage = null;
        try{
            while (true) {
                massage = readMegfserver(infromserver);
                switch (massage) {
                    case "name": {

                        System.out.println(massage);
                        ClientAcc = jtf1.getText();
                        sendMsg2server(out2server, ClientAcc);
                        break;
                    }
                    case "password": {
                        System.out.println(massage);
                        ClientPassword = jtf2.getText();
                        sendMsg2server(out2server, ClientPassword);
                        break;
                    }
                    case "res":{
                        String res = readMegfserver(infromserver);
                        if(res.equals("1")){
                            JOptionPane.showMessageDialog(null, "验证成功");
                            thisResident = new fmResident(readMegfserver(infromserver),readMegfserver(infromserver),readMegfserver(infromserver),readMegfserver(infromserver),readMegfserver(infromserver),readMegfserver(infromserver),readMegfserver(infromserver));
                            thisResident.setmoney(Double.parseDouble(readMegfserver(infromserver)));
                            return true;
                        }else{
                            JOptionPane.showMessageDialog(null, "验证失败");
                            return false;
                        }
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}
