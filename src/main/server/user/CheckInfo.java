package main.server.user;

import static main.server.Main.*;

/**
 * This class is used to check the information of a user.
 */
public class CheckInfo {
    /**
     * Constructor.
     */
    public CheckInfo() {
    }

    /**
     * Check the information of a user.
     *
     * @param user The information of a user.
     * @return The result of checking.
     */
    public static boolean check(User user) {
        /**
         * Check the user id whether it is null.
         */
        if(user.getBankAccountUserId() == null || user.getBankAccountUserId().equals("")) {
            System.out.println("User ID is empty.");
            return false;
        }
        /**
         * Check the length of user ID.
         */
        if(user.getBankAccountUserId().length() != USER_ID_LENGTH) {
            System.out.println("User ID is invalid.");
            return false;
        }
        /**
         * check userid whether digit
         */
        for(int i = 0; i < user.getBankAccountUserId().length(); i++) {
            if(!Character.isDigit(user.getBankAccountUserId().charAt(i))) {
                System.out.println("User ID is invalid.");
                return false;
            }
        }
        /**
         * Check the password whether it is null.
         */
        if(user.getBankAccountPassword() == null || user.getBankAccountPassword().equals("")) {
            return false;
        }
        /**
         * Check the length of password.
         */
        if(user.getBankAccountPassword().length() < USER_PASS_MIN_LENGTH) {
            System.out.println("Password is too short.");
            return false;
        }
        /**
         * Check the user real id whether it is null.
         */
        if(user.getBankAccountRealId() == null || user.getBankAccountRealId().equals("")) {
            System.out.println("Real ID is empty.");
            return false;
        }
        /**
         * Check the length of real ID.
         */
        if(user.getBankAccountRealId().length() != USER_REAL_ID_LENGTH) {
            System.out.println("Real ID length error.");
            return false;
        }
        /**
         * Check the real ID whether digit.
         */
        for(int i = 0; i < user.getBankAccountRealId().length(); i++) {
            if(!Character.isDigit(user.getBankAccountRealId().charAt(i))) {
                System.out.println("Real ID must be all digits.");
                return false;
            }
        }
        /**
         * Check username length
         */
        if(user.getBankAccountName().length() > USER_NAME_MAX_LENGTH) {
            System.out.println("Username is too long.");
            return false;
        }
        /**
         * Check whether the username is null.
         */
        if(user.getBankAccountName() == null || user.getBankAccountName().equals("")) {
            System.out.println("Username is empty.");
            return false;
        }
        /**
         * Check the format of gender
         */
        if(user.getBankAccountSex()!='F'||user.getBankAccountSex()!='M'){
            System.out.println("Gender format error.");
            return false;
        }
        /**
         * Check user birth date length
         */
        if(user.getBankAccountBirthDate().length()!=10){
            System.out.println("The format of birthday must be YYYY-MM-DD");
            return false;
        }
        /**
         * Check user birth date format
         */
        if(user.getBankAccountBirthDate().charAt(4)!='-'||user.getBankAccountBirthDate().charAt(7)!='-'){
            System.out.println("The format of birthday must be YYYY-MM-DD");
            return false;
        }
        return true;
}

}
