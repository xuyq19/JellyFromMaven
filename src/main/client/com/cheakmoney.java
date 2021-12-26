package main.client.com;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
public class cheakmoney extends JFrame implements ActionListener{
    JLabel jlb1,jlb2;

    Socket client = null;
    OutputStream out2server = null;
    DataOutputStream out = null;
    InputStream infromserver = null;
    DataInputStream in = null;
    String ClientAcc = null;

    public  cheakmoney(Socket client,String ClientAcc) {
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
        //标签信息
        Double moneynow = cheakmoney(ClientAcc);

        jlb1 = new JLabel(moneynow.toString());
        jlb2 = new JLabel("");

        jlb1.setBounds( 0,50,90,60);
        jlb2.setBounds( 150,50,90,60);
        //将JPane加入JFrame中
        this.add(jlb1);
        this.add(jlb2);


        //设置布局
        this.setTitle("Cheak");
        this.setLayout(new GridLayout(1, 1));
        this.setSize(250, 100);   //设置窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口
        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小
    }
    @Override
    public void actionPerformed(ActionEvent e) {
//          Double moneynow = cheakmoney(ClientAcc);
//        JOptionPane.showMessageDialog(null, moneynow);
        dispose();
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

