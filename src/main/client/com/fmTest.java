package main.client.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

import java.sql.Statement;
//作为服务器端程序
public class fmTest {
    static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/fmbank?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String username = "root";
    static final String password = "210718";
    static String ClientAcc = null;
    static String ClientPassword = null;
    static Connection conn = null;
    static Statement state1 = null;
    static Scanner keyin = new Scanner(System.in);
    static fmResident thisResident = null;
    public static void main(String[] args) {
        RootGUI root = new RootGUI();
        int chioce = 0;
        System.out.println("请输入客户姓名、密码");
        ClientAcc = keyin.next();
        ClientPassword = keyin.next();
//        ClientAcc = "0000000001";
//        ClientPassword = "210718";
        thisResident = research(ClientAcc);
        if(thisResident != null){
            if(thisResident.getPassword().equals(ClientPassword)){
                System.out.println("密码正确");
            }else{
                System.out.println("密码错误");
            }
        }else{
            System.out.println("用户不存在");
        }

        //连接数据库
            initsqlconnect();
            //执行检查
        try {
            state1 = conn.createStatement();
            String sql = "SELECT username,password FROM sheet1";
            ResultSet rs = state1.executeQuery(sql);
            while (rs.next()) {

                String name = rs.getString(1);
                String psw = rs.getString(2);
                System.out.println("psw = " + psw);
                System.out.println("name = " + name);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("请输入要进行的操作：1.开户2.修改信息3.销户4.取钱5.存钱6.转账7.查余额");
        chioce = keyin.nextInt();
        switch(chioce) {
            //开户
            case 1:{
                //在GUI界面中弹出
                System.out.println("请输入信息XXX：");
                String accountid = keyin.next();
                String username = keyin.next();
                String password = keyin.next();
                String id = keyin.next();
                String phone = keyin.next();
                String sex = keyin.next();
                String birthday = keyin.next();
                String sql = null;
                if(testinfo(accountid, username, password, id, phone, sex, birthday)){
                    fmResident aresident = new fmResident(accountid, username, password, id, phone, sex, birthday);
                    System.out.println(aresident);
                    insert(aresident);
                    System.out.println("开户成功");
                }
                break;
            }
            //修改信息
            case 2:{
                modify();
                break;
            }
            //销户
            case 3:{
                System.out.println("请输入要销户的账户id");
                String del_accountid = keyin.next();
                delete(del_accountid);
                break;
            }
            //取钱
            case 4:{
                System.out.println("请输入取钱数量：");
                double money = keyin.nextDouble();
                if(withdrawsmoney(money,ClientAcc)) {
                    System.out.println("取钱成功");
                    System.out.println("余额为" + cheak(ClientAcc));
                }else{
                    System.out.println("余额不足，请留意账户余额！");
                }
                break;
            }
            //存钱
            case 5:{
                System.out.print("请输入存钱数量");
                double money = keyin.nextDouble();
                depositmoney(money, ClientAcc);
                System.out.println("存钱成功");
                System.out.println("余额为" + cheak(ClientAcc));
                break;
            }
            //转账
            case 6:{
                System.out.println("要转账的账户id和钱数：");
                String tran_id = keyin.next();
                double tran_money = keyin.nextDouble();
                transform(ClientAcc,tran_id,tran_money);
                System.out.println("余额为" + cheak(ClientAcc));
                break;
            }
            //查找余额
            case 7:{
                double money = cheak(ClientAcc);
                if(money >= 0){
                    System.out.println("余额为" + money);
                }else{System.out.println("用户不存在");}
            }
            default:
        }
        keyin.close();
    }

    static void initsqlconnect(){
        try{
            //加载驱动
            Class.forName(jdbc_driver);
            //连接数据库
            conn = DriverManager.getConnection(url, username, password);
        }catch(SQLException e1){
            e1.printStackTrace();
        }catch(Exception e2){
            e2.printStackTrace();
        }

    }

    /**
     * @param aresident 插入此对象
     */
    static void insert(fmResident aresident){
        // Connection conn = null;
        Statement state1 = null;
        boolean flag = true;
        try{
            Class.forName(jdbc_driver);
            //连接数据库
            conn = DriverManager.getConnection(url, username, password);
            //执行检查
            state1 = conn.createStatement();
            String sql = "insert into sheet1(accountId, username, password, Id, phone, sex, birthday,money)" + " value( \""+aresident.getAccountId()+"\",\""+aresident.getUsername()+"\",\""+aresident.getPassword()+"\",\""+aresident.getId()+"\",\""+aresident.getPhone()+"\",\""+aresident.getSex()+"\",\""+aresident.getBirthday()+"\","+aresident.getMoney()+")";
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


        }catch(SQLException e1){
            e1.printStackTrace();
        }catch(Exception e2){
            e2.printStackTrace();
        }
    }
    /**
     * 根据账户ID寻找用户，返回resident
     */
    static fmResident research(String accountid){
        Statement state1 = null;
        Connection conn = null;
        try{
            Class.forName(jdbc_driver);
            //连接数据库
            conn = DriverManager.getConnection(url, username, password);
            //执行检查
            state1 = conn.createStatement();
            PreparedStatement ps = null;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        fmResident aResident = new fmResident();
        String sql = "SELECT * FROM sheet1";
        int i = 1;
        try{
            ResultSet rs = state1.executeQuery(sql);
            while (rs.next()){
                String acc = rs.getString(1);
                if(acc.equals(accountid)){
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
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return aResident;
        }
    }
    /**
    * 修改用户信息
    */
    static boolean modify(){
        boolean flag = true;
        System.out.println("请问要修改哪项信息？1.用户名2.密码3.手机号");
        //看GUI界面中用户点了哪个按钮就执行哪段操作
        int choose = keyin.nextInt();
        try{
            switch(choose){
                //修改用户名
                case 1:{
                    System.out.println("请输入新的用户名");
                    String username = keyin.next();
                    String sql = "update sheet1 set Username=? where accountId=?";
                    PreparedStatement ptmt = (PreparedStatement)conn.prepareStatement(sql);
                    ptmt.setString(1, username);
                    ptmt.setString(2, ClientAcc);
                    ptmt.execute();
                    System.out.println("修改成功");
                    flag = true;
                    break;
                }
                //修改密码
                case 2:{
                    System.out.println("请输入原密码：");
                    String oldpsw = keyin.next();
                    fmResident aresident = research(ClientAcc);
                    if(aresident.getPassword().equals(oldpsw)){
                        System.out.println("原密码验证正确，请输入新密码");
                        String psw1 = keyin.next();
                        System.out.println("请再此输入新密码：");
                        String psw2 = keyin.next();
                        if(psw2.equals(psw1)){
                            String sql = "update sheet1 set Password=? where accountId=?";
                            PreparedStatement ptmt = (PreparedStatement)conn.prepareStatement(sql);
                            ptmt.setString(1, psw1);
                            ptmt.setString(2, ClientAcc);
                            ptmt.execute();
                            System.out.println("修改成功");
                        }else{
                            System.out.println("两次输入的密码不同");
                            flag = false;
                        }
                    }else{
                        System.out.println("原密码错误");
                        flag = false;
                    }
                    break;
                }
                //修改银行预留手机号码
                case 3:{
                    System.out.println("请输入要修改的新手机号");
                    String phonenum = keyin.next();
                    if(phonenum.length()==11){
                        for(int i = 0;i < 11;i++){
                            if(phonenum.charAt(i) < '0' || phonenum.charAt(i) > '9') {
                                System.out.println("手机号需为数字！");
                                flag = false;
                            }
                            String sql = "update sheet1 set Phone=? where accountId=?";
                            PreparedStatement ptmt = (PreparedStatement)conn.prepareStatement(sql);
                            ptmt.setString(1, phonenum);
                            ptmt.setString(2, ClientAcc);
                            ptmt.execute();
                            System.out.println("修改成功");
                            flag = true;
                        }
                    }else {
                        System.out.println("手机号需为11位");
                        flag = false;
                    }
                    break;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @param del_accountid 根据账户id删除用户
     */
    static void delete(String del_accountid){
        String sql = null;
        Statement state1 = null;
        try{
            //加载驱动
            Class.forName(jdbc_driver);
            //连接数据库
            conn = DriverManager.getConnection(url, username, password);
            state1 = conn.createStatement();
            sql = "SELECT * FROM sheet1";
            ResultSet rs = state1.executeQuery(sql);
            //删除
            // while(rs.next()){
            //     if(rs.getString("accountId").equals(del_accountid)&&rs.getString("birthday"))
            // }
            sql = "DELETE FROM sheet1 WHERE accountId=?";
            PreparedStatement ptmt = (PreparedStatement)conn.prepareStatement(sql);
            ptmt.setString(1, del_accountid);
            if(ptmt.executeUpdate()!=0){
                System.out.println("删除成功");
            }else{
                System.out.println("无此条记录");
            }
            ptmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    static synchronized Boolean withdrawsmoney(double money,String accountid){
        String sql = null;
        boolean flag = true;
        try{
            fmResident aresident = research(accountid);
            if(aresident.getMoney()>=money){
                sql = "update sheet1 set money=? where accountId=?";
                PreparedStatement ptmt = (PreparedStatement)conn.prepareStatement(sql);
                ptmt.setDouble(1, thisResident.getMoney()-money);
                ptmt.setString(2, accountid);
                ptmt.execute();
                flag = true;
            }else{
                flag = false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return flag;
        }
    }
    static synchronized void depositmoney(double money,String accountid){
        String sql = null;
        try{
            sql = "update sheet1 set money=? where accountId=?";
            PreparedStatement ptmt = (PreparedStatement)conn.prepareStatement(sql);
            ptmt.setDouble(1, thisResident.getMoney() + money);
            ptmt.setString(2, accountid);
            ptmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    static synchronized void transform(String from,String to,double money){
        String sql1 = null;
        String sql2 = null;
        fmResident aresidentfrom = research(from);
        fmResident aresidentto = research(to);
        try{
            if(aresidentfrom.getMoney()>=money) {
                sql1 = "update sheet1 set money=? where accountId=?";
                PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql1);
                ptmt.setDouble(1, aresidentfrom.getMoney() - money);
                ptmt.setString(2, from);
                ptmt.execute();
                sql2 = "update sheet1 set money=? where accountId=?";
                PreparedStatement ptmt1 = (PreparedStatement)conn.prepareStatement(sql2);
                ptmt1.setDouble(1, aresidentto.getMoney() + money);
                ptmt1.setString(2, to);
                ptmt1.execute();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static double cheak(String accountid){
        fmResident aresident = research(accountid);
        if(aresident != null)
        {
            return aresident.getMoney();
        }else{return -1;}
    }
    static boolean testinfo(String accountid,String username,String password,String id,String phone,String sex,String birthday){
        if(accountid.length()!=10){
            System.out.println("The length of accountid must be 10!");
            return false;
        }
        for(int i = 0;i < accountid.length();i++){
            if(accountid.charAt(i) > '9' || accountid.charAt(i) < '0'){
                System.out.println("The accountid must be numbers!");
                return false;
            }
        }
        if(username.length()>10){
            System.out.println("The length of username must be shorter than 10!");
            return false;
        }
        if(id.length()!=12) {
            System.out.println("The length of id must be 12!");
            return false;
        }
        for(int i = 0;i < id.length();i++){
            if(id.charAt(i) > '9' ||id.charAt(i) < '0'){
                System.out.println("The id must be numbers!");
                return false;
            }
        }
        if(sex.equals("F")&&sex.equals("M")){
            System.out.println("The sex must be F or M");
        }
        if(birthday.length()!=10){
            System.out.println("The format of birthday must be YYYY-MM-DD");
            return false;
        }
        if(birthday.charAt(4)!='-'||birthday.charAt(7)!='-'){
            System.out.println("The format of birthday must be YYYY-MM-DD");
            return false;
        }
        return true;
    }

}

