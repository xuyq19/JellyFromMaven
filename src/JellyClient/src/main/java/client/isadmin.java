package client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class isadmin extends JFrame implements ActionListener{

    JButton bu1;  //按钮
    JLabel jlb1, jlb2,jlb3;  //标签
    JTextField jtf1,jtf2;   //文本框
    JPanel jp1,jp2,jp3,jp4;        //面板

    public isadmin() {
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
        this.setSize(600, 200);   //设置窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口
        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand()=="sure") {

        }


    }
}
