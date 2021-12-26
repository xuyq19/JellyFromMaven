package main.client.com;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
public class md2 extends JFrame implements ActionListener{
    JButton bu1;  //按钮
    JLabel jlb1,jlb2,jlb3;  //标签
    JTextField jtf1,jtf2,jtf3;
    JPanel jp1,jp2,jp3,jp4;        //面板

    Socket client = null;
    OutputStream out2server = null;
    DataOutputStream out = null;
    InputStream infromserver = null;
    DataInputStream in = null;
    String ClientAcc = null;
    String ClientPassword = null;

    public md2(Socket client,String ClientAcc,String ClientPassword) {
        this.client = client;
        this.ClientAcc = ClientAcc;
        this.ClientPassword = ClientPassword;
        try {
            out2server = client.getOutputStream();
            out = new DataOutputStream(out2server);
            infromserver = client.getInputStream();
            in = new DataInputStream(infromserver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //按钮
        bu1 = new JButton("sure");
        //设置按钮监听
        bu1.addActionListener(this);
        //标签信息
        jlb1 = new JLabel("original password");
        jlb2 = new JLabel("new password");
        jlb3 = new JLabel("new password");

        //文本信息
        jtf1 = new JTextField(13);
        jtf2 = new JTextField(13);
        jtf3 = new JTextField(13);
        jp1 = new JPanel();
        jp2= new JPanel();
        jp3= new JPanel();
        jp4= new JPanel();
        //将对应信息加入面板中
        jp1.add(jlb1);
        jp1.add(jtf1);
        jp2.add(jlb2);
        jp2.add(jtf2);
        jp3.add(jlb3);
        jp3.add(jtf3);

        jp4.add(bu1);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);

        //设置布局
        this.setTitle("change password");
        this.setLayout(new GridLayout(4, 1));
        this.setSize(350, 200);   //设置窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口
        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getActionCommand().equals("sure")) {
                String oldpsw = jtf1.getText();
                System.out.println(ClientPassword);
                if(oldpsw.equals(ClientPassword)){
                    String psw1 = jtf2.getText();
                    String psw2 = jtf3.getText();
                    if(psw1.equals(psw2)){
                        sendMsg2server(out2server,"modify");
                        sendMsg2server(out2server,"2");
                        sendMsg2server(out2server,psw1);
                        if(readMegfserver(infromserver).equals("successful")){
                            JOptionPane.showMessageDialog(null, "Successful！", "message", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    else{
                        sendMsg2server(out2server,"false");
                        JOptionPane.showMessageDialog(null, "！", "Two new passwords are different!", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    sendMsg2server(out2server,"false");
                    JOptionPane.showMessageDialog(null, "Original password error！", "message", JOptionPane.ERROR_MESSAGE);
                }
             dispose();
            }
        }catch (IOException e1){
            e1.printStackTrace();
        }catch (Exception e2){
            e2.printStackTrace();
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
