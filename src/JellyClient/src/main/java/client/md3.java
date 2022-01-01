package client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
public class md3 extends JFrame implements ActionListener{
    JButton bu1;  //按钮
    JLabel jlb1;  //标签
    JTextField jtf1;
    JPanel jp1,jp2;        //面板
    Socket client = null;
    OutputStream out2server = null;
    DataOutputStream out = null;
    InputStream infromserver = null;
    DataInputStream in = null;
    String ClientAcc = null;
    String ClientPassword = null;

    public md3(Socket client) {
        this.client = client;
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
        jlb1 = new JLabel("phone");

        //文本信息
        jtf1 = new JTextField(13);
        jp1 = new JPanel();
        jp2= new JPanel();
        //将对应信息加入面板中
        jp1.add(jlb1);
        jp1.add(jtf1);

        jp2.add(bu1);

        this.add(jp1);
        this.add(jp2);

        //设置布局
        this.setTitle("change phone");
        this.setLayout(new GridLayout(2, 1));
        this.setSize(350, 200);   //设置窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口
        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag_modify = true;
        try{
            if (e.getActionCommand()=="sure")
            {
                String phonenum = jtf1.getText();
                if(phonenum.length()==11){
                    for (int i = 0; i < 11; i++) {
                        if (phonenum.charAt(i) < '0' || phonenum.charAt(i) > '9') {
                            sendMsg2server(out2server, "false");
                            flag_modify = false;
                            JOptionPane.showMessageDialog(null, "Input are not all numbers！", "message", JOptionPane.ERROR_MESSAGE);
                            dispose();
                            break;
                        }
                    }
                    if(flag_modify == true){
                        sendMsg2server(out2server,"modify");
                        sendMsg2server(out2server,"3");
                        sendMsg2server(out2server,phonenum);
                        if(readMegfserver(infromserver).equals("successful")){
                            JOptionPane.showMessageDialog(null, "Successful！", "message", JOptionPane.PLAIN_MESSAGE);
                            dispose();
                        }
                    }
                }else {
                    flag_modify = false;
                    sendMsg2server(out2server,"false");
                    JOptionPane.showMessageDialog(null, "The phone number need 11 numbers!", "message", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            }
        }catch (IOException e1){
            e1.printStackTrace();
        }catch (Exception e2){
            e2.printStackTrace();
        }


    }
    public static void sendMsg2server(OutputStream os,String s)throws IOException {
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

}
