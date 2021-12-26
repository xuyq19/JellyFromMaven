package main.client.com;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class importdata {
    public void importdata(String title) {
        JFrame jframe = new JFrame(title);// 实例化一个JFrame
        JPanel jPanel = new JPanel(); // 创建一个轻量级容器
        JToolBar jToolBar = new JToolBar(); // 提供了一个用来显示常用的 Action 或控件的组件
        jframe.setVisible(true);// 可见
        jframe.setSize(500, 500);// 窗体大小
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// close的方式
        jframe.setContentPane(jPanel); // 设置 contentPane 属性。
        JLabel jl = new JLabel("Select：");// 创建一个Label标签
        jl.setHorizontalAlignment(SwingConstants.LEFT);// 样式，让文字居中
        jPanel.add("North", jl);// 将标签添加到容器中
        jPanel.add("North", jToolBar);
        JButton developer = new JButton("Upload file");
        developer.setHorizontalAlignment(SwingConstants.CENTER);
        jToolBar.add(developer);// 上传文件按钮添加到容器
        jPanel.add("North", jToolBar);
        developer.addMouseListener(new MouseAdapter() { // 添加鼠标点击事件
            public void mouseClicked(MouseEvent event) {
                eventOnImport(new JButton());
            }
        }); // 文件上传功能
    }

    /**
     * 文件上传功能
     *
     * @param developer
     *            按钮控件名称
     */
    public static void eventOnImport(JButton developer) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        /** 过滤文件类型 * */
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xls", "xlsx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(developer);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            /** 得到选择的文件* */
            File[] arrfiles = chooser.getSelectedFiles();
            if (arrfiles == null || arrfiles.length == 0) {
                return;
            }
            FileInputStream input = null;
            FileOutputStream out = null;
            DataInputStream dataInputStream = null;
            DataOutputStream dataOutputStream = null;
            String path = "./";
            try {
                for (File f : arrfiles) {
                    File dir = new File(path);
                    /** 目标文件夹 * */
                    File[] fs = dir.listFiles();
                    HashSet<String> set = new HashSet<String>();
                    for (File file : fs) {
                        set.add(file.getName());
                    }
                    /** 判断是否已有该文件* */
                    if (set.contains(f.getName())) {
                        JOptionPane.showMessageDialog(new JDialog(), f.getName() + ":File already exits！");
                        return;
                    }
                    input = new FileInputStream(f);
                    byte[] buffer = new byte[1024];
                    File des = new File(path, f.getName());
                    out = new FileOutputStream(des);
                    /** 
                     * send filename to server
                    */
                    dataOutputStream.writeUTF(f.getName());
                    dataOutputStream.flush();
                    int len = 0;
                    while ((len = input.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.flush();
                    out.close();
                    input.close();
                }
                JOptionPane.showMessageDialog(null, "Successful！", "Message", JOptionPane.INFORMATION_MESSAGE);

            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "Fail！", "Message", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Fail！", "Message", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

}
