package server.admin.fileio.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import server.admin.database.Counter;
import server.main.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Writer {

    final static String FILE_PATH = "/home/xizhilang/bank/";

    public static void writeExcel(User[] userArray) throws IOException, InvalidFormatException, SQLException {
        System.out.println(Arrays.toString(userArray));
        System.out.println("MESSAGE:START WRITE");
        List<String[]> data = new ArrayList<>();
        /**
         * import userArray to data
         */
        /**
         * get userArray's length
         */
        System.out.println("message:transfer into arraylist");
        int length = userArray.length;
        for (int index = 0; index < length; index++) {
            User user = userArray[index];
            String[] userData = new String[8];
            userData[0] = user.getBankAccountUserId();
            userData[1] = user.getBankAccountName();
            userData[2] = user.getBankAccountPassword();
            userData[3] = user.getBankAccountRealId();
            userData[4] = user.getBankAccountPhoneNumber();
            userData[5] = String.valueOf(user.getBankAccountSex());
            userData[6] = user.getBankAccountBirthDate();
            userData[7] = String.valueOf(user.getBankAccountBalance());
        }
        System.out.println("message:complete trans");
        File excelFile = new File(FILE_PATH + "user.xlsx");
        excelFile.createNewFile();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("userId");
        row.createCell(1).setCellValue("name");
        row.createCell(2).setCellValue("password");
        row.createCell(3).setCellValue("realId");
        row.createCell(4).setCellValue("phoneNumber");
        row.createCell(5).setCellValue("sex");
        row.createCell(6).setCellValue("birth date");
        row.createCell(7).setCellValue("balance");
        /**
         * user data into excel
         */
        for(int i=0;i< Counter.count();i++){
            Row row1 = sheet.createRow(i+1);
            row1.createCell(0).setCellValue(userArray[i].getBankAccountUserId());
            System.out.println(userArray[i].getBankAccountUserId());
            row1.createCell(1).setCellValue(userArray[i].getBankAccountName());
            row1.createCell(2).setCellValue(userArray[i].getBankAccountPassword());
            row1.createCell(3).setCellValue(userArray[i].getBankAccountRealId());
            row1.createCell(4).setCellValue(userArray[i].getBankAccountPhoneNumber());
            row1.createCell(5).setCellValue(String.valueOf(userArray[i].getBankAccountSex()));
            row1.createCell(6).setCellValue(userArray[i].getBankAccountBirthDate());
            row1.createCell(7).setCellValue(String.valueOf(userArray[i].getBankAccountBalance()));
        }


        OutputStream outputStream= new FileOutputStream(excelFile);
        workbook.write(outputStream);
        System.out.println("sheet"+sheet.getLastRowNum());
        outputStream.flush();
        outputStream.close();
        /**
         * 写入excel
         */

    }
}
