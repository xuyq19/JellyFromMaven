package server;
public class Sheets {
    private String bankAccountUserId;
    private String bankAccountName;
    private String bankAccountPassword;
    private String bankAccountRealId;
    private String bankAccountPhoneNumber;
    private char bankAccountSex;
    private String bankAccountBirthDate;
    private double bankAccountBalance;
    /**
     * getters and setters
     */
    public String getBankAccountUserId() {
        return bankAccountUserId;
    }
    public void setBankAccountUserId(String bankAccountUserId) {
        this.bankAccountUserId = bankAccountUserId;
    }
    public String getBankAccountName() {
        return bankAccountName;
    }
    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }
    public String getBankAccountPassword() {
        return bankAccountPassword;
    }
    public void setBankAccountPassword(String bankAccountPassword) {
        this.bankAccountPassword = bankAccountPassword;
    }
    public String getBankAccountRealId() {
        return bankAccountRealId;
    }
    public void setBankAccountRealId(String bankAccountRealId) {
        this.bankAccountRealId = bankAccountRealId;
    }
    public String getBankAccountPhoneNumber() {
        return bankAccountPhoneNumber;
    }
    public void setBankAccountPhoneNumber(String bankAccountPhoneNumber) {
        this.bankAccountPhoneNumber = bankAccountPhoneNumber;
    }
    public char getBankAccountSex() {
        return bankAccountSex;
    }
    public void setBankAccountSex(char bankAccountSex) {
        this.bankAccountSex = bankAccountSex;
    }
    public String getBankAccountBirthDate() {
        return bankAccountBirthDate;
    }
    public void setBankAccountBirthDate(String bankAccountBirthDate) {
        this.bankAccountBirthDate = bankAccountBirthDate;
    }
    public double getBankAccountBalance() {
        return bankAccountBalance;
    }
    public void setBankAccountBalance(double bankAccountBalance) {
        this.bankAccountBalance = bankAccountBalance;
    }
    /**
     * constructor
     */
    public Sheets() {
        super();
    }
    /**
     * constructor
     */
    public Sheets(String bankAccountUserId, String bankAccountName, String bankAccountPassword, String bankAccountRealId, String bankAccountPhoneNumber, char bankAccountSex, String bankAccountBirthDate, double bankAccountBalance) {
        super();
        this.bankAccountUserId = bankAccountUserId;
        this.bankAccountName = bankAccountName;
        this.bankAccountPassword = bankAccountPassword;
        this.bankAccountRealId = bankAccountRealId;
        this.bankAccountPhoneNumber = bankAccountPhoneNumber;
        this.bankAccountSex = bankAccountSex;
        this.bankAccountBirthDate = bankAccountBirthDate;
        this.bankAccountBalance = bankAccountBalance;
    }

}

