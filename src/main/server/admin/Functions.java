package main.server.admin;

import main.server.user.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

import static main.server.Main.ADMIN_PASSWORD;
import static main.server.Main.ADMIN_USER_NAME;

/**
 * This class contains all the functions that are used by the admin and cannot be used by the user.
 *
 * @author xuyuq
 */
public class Functions {
    /**
     * This method is used to check whwether the user is an admin or not.
     */
    public static boolean isAdmin(String bankAccountName, String bankAccountPassword) {
        if (ADMIN_USER_NAME.equals(bankAccountName) && ADMIN_PASSWORD.equals(bankAccountPassword)) {
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
        main.server.fileio.excel.Reader.readAll(fileName);
    }

    /**
     * This method is used to export the data to the xls(x) file.
     */
    public static void exportData() throws Exception {
        /**
         *export the xls(s) file
         */
        User[] user = main.server.database.ReadAll.readAll();
        main.server.fileio.excel.Writer.writeExcel(user);
    }

    /**
     * This methos is used to conclude and export the data to pdf file
     */
    public static void conclude() throws Exception {
        /**
         *conclude the pdf file
         */
        main.server.fileio.pdf.Export.main();
    }
}
