package main.server.user;

import main.server.database.*;

import java.util.Scanner;

/**
 * This class contains all the functions that are used by the user.
 *
 * @author xuyuq
 */
public class Functions {

    /**
     * Constructor for the class.
     */
    public Functions() {
    }

    public static void main(String[] args) {

    }

    /**
     * This fuctions is used to check if username and password are correct.
     */
    public static boolean checkUser(User user) {
        return LoginCheck.login(user.getBankAccountUserId(), user.getBankAccountPassword());
    }

    /**
     * This function is used to create a new user.
     */
    public void createBankAccount(String bankAccountName,
                                  String bankAccountPassword,
                                  String bankAccountRealId,
                                  String bankAccountPhoneNumber,
                                  char bankAccountSex,
                                  String bankAccountBirthDate, double bankAccountBalance) {
        User user = new User();
        /**
         * Set bank account user Id
         */
        String bankAccountUserId = bankAccountName.substring(0, 3) + bankAccountRealId.substring(0, 6);
        user.setBankAccountUserId(bankAccountUserId);
        /**
         * Set bank account name
         */
        user.setBankAccountName(bankAccountName);
        /**
         * Set bank account password
         */
        user.setBankAccountPassword(bankAccountPassword);
        /**
         * Set bank account real id
         */
        user.setBankAccountRealId(bankAccountRealId);
        /**
         * Set bank account phone number
         */
        user.setBankAccountPhoneNumber(bankAccountPhoneNumber);
        /**
         * Set bank account gender
         */
        user.setBankAccountSex(bankAccountSex);
        /**
         * Set bank account birth date
         */
        user.setBankAccountBirthDate(bankAccountBirthDate);
        /**
         * Set bank account balance
         */
        user.setBankAccountBalance(2000);
        /**
         * check if the user already exists
         */
        if (!CheckInfo.check(user)) {
            /**
             * write the information of the user to the database
             */
            Write.createUser(user);
        } else {
            System.out.println("The information you entered is not correct");
        }
        System.out.println("The user has been created");
    }

    public static String setUserInfo(User user, int selection, String information) {
        User userTemp = user;
        System.out.println("Select the information you want to change");
        /**
         * Selections:
         * 0: set the user's bank account name
         * 1: set the user's bank account password
         * 3: set the user's bank account phone number
         * 4: set the user's bank account sex
         * 5: set the user's bank account birth date
         */
        switch (selection) {
            case 0 -> user.setBankAccountName(information);
            case 1 -> user.setBankAccountPassword(information);
            case 3 -> user.setBankAccountPhoneNumber(information);
            case 4 -> user.setBankAccountSex(information.charAt(0));
            case 5 -> user.setBankAccountBirthDate(information);
            default -> {
                return "The selection you entered is not correct";
            }
        }
        if (CheckInfo.check(user)) {
            main.server.database.Write.setUserInfo(user, selection);
            return "The information has been changed";
        } else {
            user = userTemp;
            return "The information you entered is not correct";
        }

}

    public static void depositMoney(User user, double amount) {
        user.setBankAccountBalance(user.getBankAccountBalance() + amount);
        main.server.database.Write.setUserInfo(user, 6);

    }

    public void withdrawMoney(User user, double amount) {
        if (main.server.database.LoginCheck.login(user.getBankAccountUserId(), user.getBankAccountPassword())) {
            if (amount <= user.getBankAccountBalance()) {
                user.setBankAccountBalance((int) (user.getBankAccountBalance() - amount));
                main.server.database.Write.setUserInfo(user, 6);
            } else {
                System.out.println("You don't have enough money");
            }
        }
    }

    public static void transferToBankAccount(User user0, String bankAccountUserId, double amount) {
        if (main.server.database.LoginCheck.login(user0.getBankAccountUserId(), user0.getBankAccountPassword())) {
            System.out.println("Enter the amount you want to transfer");
            Scanner scanner = new Scanner(System.in);
            /**
             * search the user from the database
             */
            User user1 = main.server.database.ReadSingle.returnUser(bankAccountUserId);
            if (amount <= user0.getBankAccountBalance()) {
                user0.setBankAccountBalance((int) (user0.getBankAccountBalance() - amount));
                user1.setBankAccountBalance((int) (user1.getBankAccountBalance() + amount));
                main.server.database.Write.setUserInfo(user0, 6);
                main.server.database.Write.setUserInfo(user1, 6);
            } else {
                System.out.println("You don't have enough money");
            }
        }
    }


}