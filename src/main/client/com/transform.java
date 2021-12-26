package main.client.com;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

public class transform extends JFrame implements ActionListener{

    JButton bu1,bu2;  //按钮
    JLabel jlb1,jlb2;  //标签
    JTextField jtf1,jtf2;   //文本框
    JPanel jp1,jp2,jp3;        //面板

    Socket client = null;
    OutputStream out2server = null;
    DataOutputStream out = null;
    InputStream infromserver = null;
    DataInputStream in = null;
    String ClientAcc = null;

    public transform(Socket client,String ClientAcc) {
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

        jlb1 = new JLabel("To id       ");
        jlb2 = new JLabel("transform");

        //文本信息
        jtf1 = new JTextField(13);
        jtf2= new JTextField(13);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();


        //将对应信息加入面板中
        jp1.add(jlb1);
        jp1.add(jtf1);
        jp2.add(jlb2);
        jp2.add(jtf2);
        jp3.add(bu1);


        //将JPane加入JFrame中
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        //设置布局
        this.setTitle("Transform");
        this.setLayout(new GridLayout(3, 1));
        this.setSize(250, 200);   //设置窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口
        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand()=="sure")
        {
            boolean flag_tran = transform();
            if(flag_tran){
                JOptionPane.showMessageDialog(null,"转账成功！","信息",JOptionPane.PLAIN_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,"转账失败，余额不足！","信息",JOptionPane.PLAIN_MESSAGE);
            }
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
    public boolean transform(){
        boolean flag_transfrom = true;
        System.out.println("要转账的账户id和钱数：");
        String tran_id = jtf1.getText();
        double tran_money = Double.parseDouble(jtf2.getText());
        try{
            if(cheakmoney(ClientAcc)>=tran_money){
                sendMsg2server(out2server,"transform");
                sendMsg2server(out2server,tran_id);
                sendMsg2server(out2server,Double.toString(tran_money));
            }else{
                sendMsg2server(out2server,"false");
                System.out.println("The money is not enough");
                flag_transfrom = false;
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag_transfrom;
    }
    public double cheakmoney(String ClientAcc){
        double moneynow = 0;
        try {
            sendMsg2server(out2server,"checkmoney");
            String aa = readMegfserver(infromserver);
//            System.out.println(aa);
            moneynow = Double.parseDouble(aa);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return moneynow;
    }
}
