package main.server.admin;
import java.io.*;
import java.util.*;
import main.server.user.User;

import static main.server.Main.ADMIN_PASSWORD;
import static main.server.Main.ADMIN_USER_NAME;

/**
 * This class contains all the functions that are used by the admin and cannot be used by the user.
 * @author xuyuq
 */
public class Functions {
    /**
     * This method is used to check whwether the user is an admin or not.
     */
    public static boolean isAdmin(User user) {
    if (ADMIN_USER_NAME.equals(user.getBankAccountName())&&ADMIN_PASSWORD.equals(user.getBankAccountPassword())) {
            return true;
        }else{
            return false;
        }
    }
    /**
     * This method is used to import the data from the xls(x) file.
     */
    public static void importData() {
        /**
         *import the xls(s) file
         */
    }
    /**
     * This method is used to export the data to the xls(x) file.
     */
    public static void exportData() {
        /**
         *export the xls(s) file
         */
    }

    /**
     * This methos is used to conclude and export the data to pdf file
     */
    public static void conclusions() {
        /**
         *conclude the pdf file
         */
    }

}
