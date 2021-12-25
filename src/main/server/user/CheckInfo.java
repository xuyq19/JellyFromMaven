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
    public static String check(User user) {
        /**
         * Check the password whether it is null.
         */
        if (user.getBankAccountPassword() == null || user.getBankAccountPassword().equals("")) {
            return "Password cannot be null.";
        }
        /**
         * Check the length of password.
         */
        if (user.getBankAccountPassword().length() < USER_PASS_MIN_LENGTH) {
            return "Password length error.";
        }
        /**
         * Check the user real id whether it is null.
         */
        if (user.getBankAccountRealId() == null || user.getBankAccountRealId().equals("")) {
            return "User real id cannot be null.";
        }
        /**
         * Check the length of real ID.
         */
        if (user.getBankAccountRealId().length() != USER_REAL_ID_LENGTH) {
            return "User real id length error.";
        }
        /**
         * Check the real ID whether digit.
         */
        for (int i = 0; i < user.getBankAccountRealId().length(); i++) {
            if (!Character.isDigit(user.getBankAccountRealId().charAt(i))) {
                return "User real id must be digit.";
            }
        }
        /**
         * Check username length
         */
        if (user.getBankAccountName().length() > USER_NAME_MAX_LENGTH) {
            return "User name length error.";
        }
        /**
         * Check whether the username is null.
         */
        if (user.getBankAccountName() == null || user.getBankAccountName().equals("")) {
            return "User name cannot be null.";
        }
        /**
         * Check the format of gender
         */
        if (user.getBankAccountSex() != 'F' || user.getBankAccountSex() != 'M') {
            return "Gender format error";
        }
        /**
         * Check user birth date length
         */
        if (user.getBankAccountBirthDate().length() != 10) {
            return "Bank account birth date format error";
            /**
             * Check user birth date format
             */
        }
        if (user.getBankAccountBirthDate().charAt(4) != '-' || user.getBankAccountBirthDate().charAt(7) != '-') {
            return "Bank account birth date format error";

        }else{
            return "OK";
        }

        /**
         *
         */
    }
}
