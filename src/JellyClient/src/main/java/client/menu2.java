package client;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import javax.swing.*;


public class menu2 extends JFrame implements ActionListener{
    JButton bu1,bu2,bu3,bu4,bu5;  //创建按钮
    JLabel jlb1;
    Socket client = null;
    String ClientAcc = null;
    String ClientPassword = null;
    InputStream inputStream = null;
    OutputStream outputStream = null;
    public static String message=null;
    public menu2(Socket client)
    {
        this.client = client;
        bu1 = new JButton("importdata");
        bu2 = new JButton("exportdata");
        bu3 = new JButton("conclusion");
        bu4 = new JButton("exit");
        jlb1 = new JLabel("Please select service:");

        bu1.addActionListener(this);   //事件监听
        bu2.addActionListener(this);
        bu3.addActionListener(this);
        bu4.addActionListener(this);


        this.setTitle("Feima Bank");  //设置窗体标题
        this.setSize(600, 500);         //设置窗体大小
        this.setLocation(400, 200);     //设置位置
        this.setLayout(null);           //设置布局，不采用布局


        //设置按钮的位置和大小
        bu1.setBounds( 65,200,120,30);
        bu2.setBounds( 195,200,120,30);
        bu3.setBounds( 325,200,120,30);
        bu4.setBounds(455,200,120,30);

        //设置标签的位置和大小
        jlb1.setBounds(150,100,300,50);
        jlb1.setFont(new   java.awt.Font("Dialog",   1,   23));
        //加入窗体
        this.add(bu1);
        this.add(bu2);
        this.add(bu3);
        this.add(bu4);
        this.add(jlb1);

        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand()=="importdata")
        {

            new importdata().importdata("import data",client);
        }
        else if (e.getActionCommand()=="exportdata")
        {
            try {
                JOptionPane.showMessageDialog(null, "Successful！", "Message", JOptionPane.PLAIN_MESSAGE);

                exportdata.exportdata(client);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "exportdata error", "Message", JOptionPane.ERROR_MESSAGE);
                System.out.println("exportdata error");
            }
        }
        else if (e.getActionCommand()=="conclusion")
        {
            try {
                JOptionPane.showMessageDialog(null, "Successful！", "Message", JOptionPane.PLAIN_MESSAGE);
                new conclusion().conclude(client);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "conclusion error", "Message", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        else if (e.getActionCommand()=="exit")
        {
            dispose();
            new root(null);
        }

    }



}