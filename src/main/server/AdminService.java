package main.server;

/**
 * define the admin services functions.
 */
public class AdminService {
    public AdminService() {}
    public void main(String[] args) {

    }
    private boolean isAdmin(String bankAccountUserId, String bankAccountPassword) {
        if(bankAccountUserId.equals("admin") && bankAccountPassword.equals("123456")) {
            return true;
        }else {
            return false;
        }
    }
    private void setUserInfo(String bankAccountUserId){

    }
}
