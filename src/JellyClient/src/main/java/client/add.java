package client;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;


public class add extends JFrame implements ActionListener{

    JButton bu1;  //按钮
    JLabel jlb1, jlb2, jlb3,jlb4,jlb5,jlb6,jlb7;  //标签
    JTextField jtf1,jtf2,jtf3,jtf4, jtf5,jtf6,jtf7;   //文本框
    JPanel jp1,jp2,jp3, jp4,jp5,jp6,jp7,jp8;        //面板
    Socket client = null;
    OutputStream out2server = null;
    DataOutputStream out = null;
    InputStream infromserver = null;
    DataInputStream in = null;


    public add(Socket client) {
        this.client = client;
        //新建按钮
        bu1 = new JButton("sure");
        //设置按钮监听
        bu1.addActionListener(this);
        //添加标签信息
        jlb1 = new JLabel("accountid");
        jlb2 = new JLabel("username ");
        jlb3 = new JLabel("password ");
        jlb4 = new JLabel("id              ");
        jlb5 = new JLabel("phone      ");
        jlb6 = new JLabel("sex          ");
        jlb7 = new JLabel("birthday   ");
        //添加文本信息
        jtf1 = new JTextField(13);
        jtf2 = new JTextField(13);
        jtf3 = new JTextField(13);
        jtf4 = new JTextField(13);
        jtf5 = new JTextField(13);
        jtf6 = new JTextField(13);
        jtf7 = new JTextField(13);
        //新建面板
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jp8 = new JPanel();
        //将对应信息加入面板中
        jp1.add(jlb1);
        jp1.add(jtf1);

        jp2.add(jlb2);
        jp2.add(jtf2);

        jp3.add(jlb3);
        jp3.add(jtf3);

        jp4.add(jlb4);
        jp4.add(jtf4);

        jp5.add(jlb5);
        jp5.add(jtf5);

        jp6.add(jlb6);
        jp6.add(jtf6);

        jp7.add(jlb7);
        jp7.add(jtf7);

        jp8.add(bu1);


        //将JPane加入JFrame中（即将不同面板以此加入窗口中）
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.add(jp6);
        this.add(jp7);
        this.add(jp8);
        //设置布局
        this.setTitle("Sign In");//设置窗口名称
        this.setLayout(new GridLayout(8, 1));//设置窗口中共有八行一列
        this.setSize(400, 450);   //设置窗体大小
        this.setLocationRelativeTo(null);//窗口位置：在屏幕中间显示(居中显示)
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
        if (e.getActionCommand()=="sure")
        {
            adduser();
            //成功时消息提示
            JOptionPane.showMessageDialog(null,"Successful！","Message",JOptionPane.PLAIN_MESSAGE);
            dispose();
        }

    }
    public boolean adduser(){
        boolean flag_add = true;
        String accountid = jtf1.getText();
        String username = jtf2.getText();
        String password = jtf3.getText();
        String id = jtf4.getText();
        String phone = jtf5.getText();
        String sex = jtf6.getText();
        String birthday = jtf7.getText();
        String sql = null;
        try{
            if(testinfo(accountid, username, password, id, phone, sex, birthday)){
                fmResident aresident = new fmResident(accountid, username, password, id, phone, sex, birthday);
                System.out.println(aresident);
                sendMsg2server(out2server,accountid);
                sendMsg2server(out2server,username);
                sendMsg2server(out2server,password);
                sendMsg2server(out2server,id);
                sendMsg2server(out2server,phone);
                sendMsg2server(out2server,sex);
                sendMsg2server(out2server,birthday);
                String info =  readMegfserver(infromserver);
                if(info.equals("insertok")) {
                    System.out.println("开户成功");
                }
                flag_add = true;
            }else{
                sendMsg2server(out2server,"false");
                flag_add = false;
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag_add;
    }
    public void sendMsg2server(OutputStream os, String s)throws IOException {
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
    public static boolean testinfo(String accountid,String username,String password,String id,String phone,String sex,String birthday){
        if(accountid.length()!=10){
            System.out.println("The length of accountid must be 10!");
            JOptionPane.showMessageDialog(null, "Two new passwords are differentThe length of accountid must be 10!", "message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        for(int i = 0;i < accountid.length();i++){
            if(accountid.charAt(i) > '9' || accountid.charAt(i) < '0'){
                System.out.println("The accountid must be numbers!");
                JOptionPane.showMessageDialog(null, "The accountid must be numbers!", "message", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if(username.length()>10){
            System.out.println("The length of username must be shorter than 10!");
            JOptionPane.showMessageDialog(null, "The length of username must be shorter than 10!", "message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(id.length()!=12) {
            System.out.println("The length of id must be 12!");
            JOptionPane.showMessageDialog(null, "The length of id must be 12!", "message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        for(int i = 0;i < id.length();i++){
            if(id.charAt(i) > '9' ||id.charAt(i) < '0'){
                System.out.println("The id must be numbers!");
                JOptionPane.showMessageDialog(null, "The id must be numbers!", "message", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if(sex.equals("F")&&sex.equals("M")){
            System.out.println("The sex must be F or M");
            JOptionPane.showMessageDialog(null, "The sex must be F or M", "message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(birthday.length()!=10){
            System.out.println("The format of birthday must be YYYY-MM-DD");
            JOptionPane.showMessageDialog(null, "The format of birthday must be YYYY-MM-DD", "message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(birthday.charAt(4)!='-'||birthday.charAt(7)!='-'){
            System.out.println("The format of birthday must be YYYY-MM-DD");
            JOptionPane.showMessageDialog(null, "The format of birthday must be YYYY-MM-DD", "message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
