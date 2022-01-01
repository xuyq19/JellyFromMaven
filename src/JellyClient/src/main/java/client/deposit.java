package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class deposit extends JFrame implements ActionListener{

    JButton bu1;  //按钮
    JLabel jlb1;  //标签
    JTextField jtf1;   //文本框
    JPanel jp1,jp2;        //面板

    Socket client = null;
    OutputStream out2server = null;
    DataOutputStream out = null;
    InputStream infromserver = null;
    DataInputStream in = null;
    String ClientAcc = null;

    public deposit(Socket client, String ClientAcc) {
        this.client = client;
        this.ClientAcc = ClientAcc;
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
        //按钮
        bu1 = new JButton("sure");
        //设置按钮监听
        bu1.addActionListener(this);
        //标签信息

        jlb1 = new JLabel("deposit money");

        //文本信息
        jtf1 = new JTextField(13);

        jp1 = new JPanel();
        jp2= new JPanel();

        //将对应信息加入面板中
        jp1.add(jlb1);
        jp1.add(jtf1);
        jp2.add(bu1);


        //将JPane加入JFrame中
        this.add(jp1);
        this.add(jp2);

        //设置布局
        this.setTitle("Deposit");
        this.setLayout(new GridLayout(2, 1));
        this.setSize(250, 100);   //设置窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口
        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand()=="sure")
        {
            deposit();
            JOptionPane.showMessageDialog(null,"Successful！","Message",JOptionPane.PLAIN_MESSAGE);
            dispose();
        }

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
    public boolean deposit(){
        boolean flag_deposit = true;
        Double money = Double.parseDouble(jtf1.getText());
        try {
            sendMsg2server(out2server,"deposit");
            sendMsg2server(out2server,money.toString());
            if(readMegfserver(infromserver).equals("successful")){
                flag_deposit = true;
            }
            else {
                flag_deposit = false;
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag_deposit;
    }
}