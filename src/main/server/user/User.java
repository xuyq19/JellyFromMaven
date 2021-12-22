package main.server.user;


/**
 * define user class
 */
public class User {
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
    private double bankAccountBalance=2000;
    /**
     *银行用户余额
     */
    public User(String bankAccountUserId,
                String bankAccountName,
                String bankAccountPassword,
                String bankAccountRealId,
                String bankAccountPhoneNumber,
                char bankAccountSex,
                String bankAccountBirthDate, double bankAccountBalance) {
        this.bankAccountName = bankAccountName;
        this.bankAccountPassword = bankAccountPassword;
        this.bankAccountRealId = bankAccountRealId;
        this.bankAccountPhoneNumber = bankAccountPhoneNumber;
        this.bankAccountSex = bankAccountSex;
        this.bankAccountBirthDate = bankAccountBirthDate;
        this.bankAccountBalance = bankAccountBalance;
        this.bankAccountUserId = bankAccountUserId;
    }
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
    public void setBankAccountBalance(int bankAccountBalance){
        this.bankAccountBalance = bankAccountBalance;
    }
    /**
     *设置银行用户余额
     */
    public void withdrawMoney(double money){
        this.bankAccountBalance = this.bankAccountBalance-money;
    }
    /**
     *取款
     */
    public void depositMoney(double money){
        this.bankAccountBalance = this.bankAccountBalance+money;
    }
}
