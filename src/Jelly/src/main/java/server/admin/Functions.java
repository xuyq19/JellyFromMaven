package server.admin;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import server.admin.database.ReadAll;
import server.admin.fileio.excel.Reader;
import server.main.User;

import java.io.IOException;

import static server.admin.Main.ADMIN_PASSWORD;
import static server.admin.Main.ADMIN_USER_NAME;


/**
 * This class contains all the functions that are used by the admin and cannot be used by the user.
 *
 * @author xuyuq
 */
public class Functions {
    /**
     * This method is used to check whwether the user is an admin or not.
     */
    public static boolean isAdmin(String bankAccountUserId, String bankAccountPassword) {
        if ("0000000010".equals(bankAccountUserId) && ADMIN_PASSWORD.equals(bankAccountPassword)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to import the data from the xls(x) file.
     */
    public static void importData(String fileName) throws IOException, InvalidFormatException {
        /**
         *import the xls(s) file
         */

        User[] user= Reader.readAll(fileName);
        server.admin.database.Write.createUser(user);
    }

    /**
     * This method is used to export the data to the xls(x) file.
     */
    public static void exportData() throws Exception {
        /**
         *export the xls(s) file
         */
        System.out.println("message:read user");
        User[] user = ReadAll.readAll();
        System.out.println("message:prepare to write");
        server.admin.fileio.excel.Writer.writeExcel(user);
    }

    /**
     * This methos is used to conclude and export the data to pdf file
     */
    public static void conclude() throws Exception {
        /**
         *conclude the pdf file
         */
        server.admin.fileio.pdf.Export.createPDF();
    }
}
