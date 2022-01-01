package client;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static java.lang.System.exit;


public class importdata {
    Socket socket;

    public void importdata (String title, Socket socket) {
        JFrame jframe = new JFrame(title);
        // 实例化一个JFrame
        this.socket = socket;
        JPanel jPanel = new JPanel();
        // 创建一个轻量级容器
        JToolBar jToolBar = new JToolBar();
        // 提供了一个用来显示常用的 Action 或控件的组件
        jframe.setVisible(true);
        // 可见
        jframe.setSize(500, 500);
        // 窗体大小
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // close的方式
        jframe.setContentPane(jPanel);
        // 设置 contentPane 属性。
        JLabel jl = new JLabel("Select：");
        // 创建一个Label标签
        jl.setHorizontalAlignment(SwingConstants.LEFT);
        // 样式，让文字居中
        jPanel.add("North", jl);
        // 将标签添加到容器中
        jPanel.add("North", jToolBar);
        JButton developer = new JButton("Upload file");
        developer.setHorizontalAlignment(SwingConstants.CENTER);
        jToolBar.add(developer);
        // 上传文件按钮添加到容器
        jPanel.add("North", jToolBar);
        developer.addMouseListener(new MouseAdapter() {
            // 添加鼠标点击事件
            @Override
            public void mouseClicked (MouseEvent event) {
                eventOnImport(new JButton(), socket);
            }
        }); // 文件上传功能
    }

    /**
     * 文件上传功能
     *
     * @param developer 按钮控件名称
     */
    public void eventOnImport (JButton developer, Socket socket) {

        JFileChooser chooser = new JFileChooser();

        chooser.setMultiSelectionEnabled(false);
        /** 过滤文件类型 * */
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx", "xlsx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(developer);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            /** 得到选择的文件* */
            InputStream inputStream = null;
            File arrfile = chooser.getSelectedFile();
            System.out.println(arrfile.getName());
            System.out.println(arrfile.getTotalSpace());
            String path = arrfile.getAbsolutePath();
            System.out.println(arrfile.getUsableSpace());
            System.out.println(path);
            try {
                inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                String message = "importData";
                DataOutputStream dos = new DataOutputStream(outputStream);
                dos.writeUTF(message);
                dos.flush();
                FileInputStream fileInputStream = new FileInputStream(path);
                System.out.println("Stream Available State:" + fileInputStream.available());
                int len;
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.flush();
                DataInputStream dis = new DataInputStream(inputStream);
                message = dis.readUTF();
                System.out.println("Message:Receive " + message);
                if ("exportDataSuccess".equals(message)) {
                    inputStream.close();
                    outputStream.flush();
                    outputStream.close();
                    exit(0);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}