package main.server.user;

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
     * This function is used to create a new user.
     */
    public static String register(String bankAccountName,
                                  String bankAccountPassword,
                                  String bankAccountRealId,
                                  String bankAccountPhoneNumber,
                                  char bankAccountSex,
                                  String bankAccountBirthDate) {
        if (Initial.initial(bankAccountName, bankAccountPassword, bankAccountRealId, bankAccountPhoneNumber, bankAccountSex, bankAccountBirthDate) == "OK") {
            return "OK";
        } else {
            return Initial.initial(bankAccountName, bankAccountPassword, bankAccountRealId, bankAccountPhoneNumber, bankAccountSex, bankAccountBirthDate);
        }
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
        if (CheckInfo.check(user) == "OK") {
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

    public static String withdrawMoney(User user, double amount) {
        if (amount <= user.getBankAccountBalance()) {
            user.setBankAccountBalance((int) (user.getBankAccountBalance() - amount));
            main.server.database.Write.setUserInfo(user, 6);
            return "The money has been withdrawn";
        } else {
            return "The amount you entered is not correct or you don't have enough money";
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