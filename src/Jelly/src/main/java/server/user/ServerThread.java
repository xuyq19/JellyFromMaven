package server.user;

import server.user.fmResident;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;

public class ServerThread extends Thread {
    public static Socket socket;
    public static InputStream ins;
    public static OutputStream outs;
    private static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://123.60.106.3:3306/bank";
    private static final String username = "xizhilang";
    private static final String password = "123456";
    private static final String tableName = "bank";
    static fmResident thisResident = null;
    static Connection conn = null;
    static String ClientAcc = null;
    static String ClientPassword = null;

    public ServerThread(Socket socket) {
        ServerThread.socket = socket;
    }

    @Override
    public void run() {
        try {
            //获取输入输出流
            ins = socket.getInputStream();
            outs = socket.getOutputStream();
            //发送消息给客户端
            String msg = "Welcome to the FMbank server!";
            sendMsg(outs, msg);
            if (login()) {
                while (!socket.isClosed()) {
                    choose();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean login() {
        String msg = null;
        boolean flag_login = true;
        try {
            sendMsg(outs, "login");
            msg = "name";
            sendMsg(outs, msg);
            ClientAcc = readMsg(ins);
            System.out.println("收到的name:" + ClientAcc);
            msg = "password";
            sendMsg(outs, msg);
            ClientPassword = readMsg(ins);
            System.out.println("收到的psw:" + ClientPassword);
            flag_login = logincheak(ClientAcc, ClientPassword);
            sendMsg(outs, "res");
            if (flag_login == true) {
                sendMsg(outs, "1");
                sendMsg(outs, thisResident.getAccountId());
                sendMsg(outs, thisResident.getUsername());
                sendMsg(outs, thisResident.getPassword());
                sendMsg(outs, thisResident.getId());
                sendMsg(outs, thisResident.getPhone());
                sendMsg(outs, thisResident.getSex());
                sendMsg(outs, thisResident.getBirthday());
                sendMsg(outs, Double.toString(thisResident.getMoney()));
            } else {
                sendMsg(outs, "0");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag_login;
    }

    public static boolean logincheak(String ClientAcc, String ClientPassword) {
        boolean flag = false;
        try {
            //加载驱动
            Class.forName(jdbc_driver);
            //连接数据库
            conn = DriverManager.getConnection(url, username, password);
            thisResident = research(ClientAcc);
            if (thisResident != null) {
                System.out.println(thisResident);
                if (thisResident.getPassword().equals(ClientPassword)) {
                    flag = true;
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return flag;
    }

    public static void sendMsg(OutputStream os, String s) throws IOException {
        //向客户端输出信息
        byte[] bytes = s.getBytes();
        os.write(bytes);
        os.write(13);
        os.write(10);
        os.flush();
    }

    public static String readMsg(InputStream ins) throws Exception {
        int value = ins.read();
        String str = "";
        //整行读取，读取到回车（13）换行（10）时停止
        while (value != 10) {
            if (value == -1) {
                throw new Exception();
            }
            str = str + ((char) value);
            value = ins.read();
        }
        str = str.trim();
        return str;
    }

    public static fmResident research(String accountid) {
        Statement state1 = null;
        Connection conn = null;
        try {
            Class.forName(jdbc_driver);
            //连接数据库
            conn = DriverManager.getConnection(url, username, password);
            //执行检查
            state1 = conn.createStatement();
            PreparedStatement ps = null;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fmResident aResident = new fmResident();
        String sql = "SELECT * FROM bank";
        int i = 1;
        try {
            ResultSet rs = state1.executeQuery(sql);
            while (rs.next()) {
                String acc = rs.getString(1);
                if (acc.equals(accountid)) {
                    aResident.setaccountId(acc);
                    aResident.setPassword(rs.getString(3));
                    aResident.setUsername(rs.getString(2));
                    aResident.setId(rs.getString(4));
                    aResident.setPhone(rs.getString(5));
                    aResident.setSex(rs.getString(6));
                    aResident.setBirthday(rs.getString(7));
                    aResident.setmoney(rs.getDouble(8));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return aResident;
        }
    }

    public static void choose() {
        try {
//            sendMsg(outs,"choose");
            String chioce = readMsg(ins);
            switch (chioce) {
                case "insert": {
                    String accountid = readMsg(ins);
                    String username = readMsg(ins);
                    String password = readMsg(ins);
                    String id = readMsg(ins);
                    String phone = readMsg(ins);
                    String sex = readMsg(ins);
                    String birthday = readMsg(ins);
                    fmResident aresident = new fmResident(accountid, username, password, id, phone, sex, birthday);
                    insert(aresident);
                    sendMsg(outs, "insertok");
                    break;
                }
                case "modify": {
                    String chioce_modify = readMsg(ins);
                    Class.forName(jdbc_driver);
                    conn = DriverManager.getConnection(url, username, password);
                    switch (chioce_modify) {
                        case "1": {
                            String username_modify = readMsg(ins);
                            String sql = "update bank set bankAccountName=? where bankAccountUserId=?";
                            PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
                            ptmt.setString(1, username_modify);
                            ptmt.setString(2, ClientAcc);
                            ptmt.execute();
                            sendMsg(outs, "successful");
                            break;
                        }
                        case "2": {
                            String password_modify = readMsg(ins);
                            String sql = "update bank set bankAccountPassword=? where bankAccountUserId=?";
                            PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
                            ptmt.setString(1, password_modify);
                            ptmt.setString(2, ClientAcc);
                            ptmt.execute();
                            sendMsg(outs, "successful");
                            break;
                        }
                        case "3": {
                            String phonenum_modify = readMsg(ins);
                            String sql = "update bank set bankAccountPhoneNumber=? where bankAccountUserId=?";
                            PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
                            ptmt.setString(1, phonenum_modify);
                            ptmt.setString(2, ClientAcc);
                            ptmt.execute();
                            sendMsg(outs, "successful");
                            break;
                        }
                    }
                    break;
                }
                case "delete": {
                    String sql = null;
                    Statement state1 = null;
                    String del_accountid = readMsg(ins);
                    Class.forName(jdbc_driver);
                    conn = DriverManager.getConnection(url, username, password);
                    state1 = conn.createStatement();
                    sql = "SELECT * FROM " + tableName;
                    ResultSet rs = state1.executeQuery(sql);
                    sql = "DELETE FROM bank WHERE bankAccountUserId=?";
                    PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
                    ptmt.setString(1, del_accountid);
                    if (ptmt.executeUpdate() != 0) {
                        sendMsg(outs, "successful");
                    }
//                    else{
//                        System.out.println("nosuch");
//                    }
                    ptmt.close();
                    break;
                }
                case "withdraw": {

                    sendMsg(outs, Double.toString(research(ClientAcc).getMoney()));
                    if (readMsg(ins).equals("yes")) {
                        String m_w = readMsg(ins);

                        double money_withdraw = Double.parseDouble(m_w);
                        String sql = null;
                        try {
                            sql = "update bank set bankAccountBalance=? where bankAccountUserId=?";
                            PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
                            ptmt.setDouble(1, research(ClientAcc).getMoney() - money_withdraw);
                            ptmt.setString(2, ClientAcc);
                            ptmt.execute();
                            sendMsg(outs, "successful");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {

                    }

                    break;
                }
                case "deposit": {
                    double money_deposit = Double.parseDouble(readMsg(ins));
                    String sql = null;
                    try {
                        sql = "update bank set bankAccountBalance=? where bankAccountUserId=?";
                        PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
                        ptmt.setDouble(1, research(ClientAcc).getMoney() + money_deposit);
                        ptmt.setString(2, ClientAcc);
                        ptmt.execute();
                        sendMsg(outs, "successful");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "transform": {
                    String sql1 = null;
                    String sql2 = null;
                    String toacc = readMsg(ins);
                    double tran_money = Double.parseDouble(readMsg(ins));
                    sql1 = "update bank set bankAccountBalance=? where bankAccountUserId=?";
                    PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql1);
                    ptmt.setDouble(1, thisResident.getMoney() - tran_money);
                    ptmt.setString(2, ClientAcc);
                    ptmt.execute();
                    sql2 = "update bank set bankAccountBalance=? where bankAccountUserId=?";
                    fmResident toresident = research(toacc);
                    PreparedStatement ptmt1 = (PreparedStatement) conn.prepareStatement(sql2);
                    ptmt1.setDouble(1, toresident.getMoney() + tran_money);
                    ptmt1.setString(2, toacc);
                    ptmt1.execute();
                    break;
                }
                case "checkmoney": {
                    sendMsg(outs, Double.toString(research(ClientAcc).getMoney()));
//                    if(readMsg(ins).equals("successful")
                    break;
                }
                default: {
                    break;
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insert(fmResident aresident) {
        // Connection conn = null;
        Statement state1 = null;
        boolean flag = true;
        try {
            Class.forName(jdbc_driver);
            //连接数据库
            conn = DriverManager.getConnection(url, username, password);
            //执行检查
            state1 = conn.createStatement();
            String sql = "insert into bankAccountUserId, bankAccountName, bankAccountPassword, bankAccountRealId, bankAccountPhoneNumber, bankAccountSex, bankAccountBirthDate, bankAccountBalance)" + " value( \"" + aresident.getAccountId() + "\",\"" + aresident.getUsername() + "\",\"" + aresident.getPassword() + "\",\"" + aresident.getId() + "\",\"" + aresident.getPhone() + "\",\"" + aresident.getSex() + "\",\"" + aresident.getBirthday() + "\"," + aresident.getMoney() + ")";
            state1.executeUpdate(sql);
            state1.close();
            conn.close();
//            ps = conn.prepareStatement(sql);
//            if(ps!=null) {
//                ps.setString(1, aresident.getAccountId());
//                ps.setString(2, aresident.getUsername());
//                ps.setString(3, aresident.getPassword());
//                ps.setString(4, aresident.getId());
//                ps.setString(5, aresident.getPhone());
//                ps.setString(6, aresident.getSex());
//                ps.setString(7, aresident.getBirthday());
//                ps.setDouble(8, aresident.getMoney());
//                ps.executeUpdate(sql);
//                System.out.println("添加数据成功");
//            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
