# 飞马银行微系统设计文档
## 0.项目概述：
    0.1.项目语言:Java;
    0.2.项目类型:银行系统微服务
    0.3.项目结构：Clients-Server.
## 1.项目简介：
### 1.1.项目概述：
飞马银行微系统是一个银行系统微服务，主要是为了提供给飞马银行的客户提供一个简单的银行系统，
客户可以通过这个系统来操作银行账户，查询账户余额，转账等等。
### 1.2.项目功能：
#### 1.2.1.个人账户功能：开户，销户，查询余额等信息，存取款，转账，修改账户信息
#### 1.2.2.管理员账户功能：在个人账户功能的基础上加入导入导出Excel文件和生成年终报表（PDF格式）
### 1.3.项目设计：
使用Clients-Server架构，客户端提供输入输出下载上传操作以及GUI，服务端提供数据库操作以及接口调用，
服务端使用多线程技术，C/S分离，通过各个接口实现客户端与服务端通信，客户端不涉及到任何数据库操作，使用Maven进行项目管理
## 2.项目分析和说明：
### 2.1.服务端
#### 2.1.1.服务端是用包封装将不同类型的功能（例如数据库包，文件读写包，通讯包）进行分类，其中每一个包就是一类功能。接下来是对各个类的说明
##### 2.1.1.1 main.server.user.User 字段说明
```Java
public User(){
}
private String bankAccountUserId;
/**
 *十位数的银行用户ID
 */
private String bankAccountName;
/**
 *银行用户名
 */
private String bankAccountPassword;
/**
 *银行用户密码
 */
private String bankAccountRealId;
/**
 *银行用户真实ID
 */
private String bankAccountPhoneNumber;
/**
 *银行用户手机号
 */
private char bankAccountSex;
/**
 *银行用户性别
 */
private String bankAccountBirthDate;
/**
 *银行用户生日
 */
private double bankAccountBalance
/**
 *银行用户余额
 */


```
##### 2.1.1.2 根据封装原理定义类的getter and setter：
```Java
public void setBankAccountUserId() {
        this.bankAccountUserId = bankAccountUserId;
    }
    /**
     * 设置银行用户id
     */
    public String getBankAccountUserId(){
        return bankAccountUserId;
    }
    /**
     *获取银行用户ID
     */
    public String getBankAccountName(){
        return bankAccountName;
    }
    /**
     *获取银行用户名
     */
    public String getBankAccountPassword(){
        return bankAccountPassword;
    }
    /**
     *获取银行用户密码
     */
    public String getBankAccountRealId(){
        return bankAccountRealId;
    }
    /**
     *获取银行用户真实ID
     */
    public String getBankAccountPhoneNumber(){
        return bankAccountPhoneNumber;
    }
    public char getBankAccountSex(){
        return bankAccountSex;
    }
    /**
     *获取银行用户性别
     */
    public String getBankAccountBirthDate(){
        return bankAccountBirthDate;
    }
    /**
     *获取银行用户生日
     */
    public double getBankAccountBalance(){
        return bankAccountBalance;
    }
    /**
     *获取银行用户余额
     */
    public void setBankAccountUserId(String bankAccountUserId){
        this.bankAccountUserId = bankAccountUserId;
    }
    /**
     *设置银行用户ID
     */
    public void setBankAccountName(String bankAccountBalance){
        this.bankAccountName = bankAccountBalance;
    }
    /**
     *设置银行用户名
     */
    public void setBankAccountPassword(String bankAccountPassword){
        this.bankAccountPassword = bankAccountPassword;
    }
    /**
     *设置银行用户密码
     */
    public void setBankAccountRealId(String bankAccountRealId){
        this.bankAccountRealId = bankAccountRealId;
    }
    /**
     *设置银行用户真实ID
     */
    public void setBankAccountPhoneNumber(String bankAccountPhoneNumber){
        this.bankAccountPhoneNumber = bankAccountPhoneNumber;
    }
    /**
     *设置银行用户手机号
     */
    public void setBankAccountSex(char bankAccountSex){
        this.bankAccountSex = bankAccountSex;
    }
    /**
     *设置银行用户性别
     */
    public void setBankAccountBirthDate(String bankAccountBirthDate){
        this.bankAccountBirthDate = bankAccountBirthDate;
    }
    /**
     *设置银行用户生日
     */
    public void setBankAccountBalance(double bankAccountBalance){
        this.bankAccountBalance = bankAccountBalance;
    }
    /**
     *设置银行用户余额
     */

```

