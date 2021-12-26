package main.server.fileio.excel;

import main.server.user.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Writer {

    final static String FILE_PATH = "/home/xizhilang/bank/";

    public static void writeExcel(User[] userArray) throws IOException, InvalidFormatException {
        List<String[]> data = new ArrayList<>();
        /**
         * import userArray to data
         */
        /**
         * get userArray's length
         */
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
        File excelFile = new File(FILE_PATH + "user.xlsx");
        Workbook workbook = WorkbookFactory.create(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        for (int rowNum = sheet.getLastRowNum() + 1, i = 0; i < data.size(); rowNum++, i++) {
            Row row = sheet.createRow(rowNum);
            String[] rowData = data.get(i);
            for (int columnNum = row.getLastCellNum() + 1, j = 0; j < rowData.length; columnNum++, j++) {
                row.createCell(columnNum).setCellValue(rowData[j]);
                /**
                 * 单元格存储数据
                 */
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH + "user.xlsx");
        fileOutputStream.flush();
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        /**
         * 写入excel
         */

    }
}
