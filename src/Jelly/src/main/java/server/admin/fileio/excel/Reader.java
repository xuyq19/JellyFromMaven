package server.admin.fileio.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import server.main.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuyuq
 */
public class Reader {
    final static String FILE_PATH = "/home/xizhilang/bank/";

    public static User[] readAll(String fileName) throws IOException, InvalidFormatException {
        String filePath = FILE_PATH + fileName;
        File file = new File(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(String.valueOf(0));
        List<String[]> users = new ArrayList<>();
        /**
         * 开始逐行遍历
         */
        for (int rowNum = 0, i = 0; i <= sheet.getLastRowNum(); rowNum++, i++) {
            Row row = sheet.getRow(rowNum);
            String[] rowString = new String[row.getLastCellNum()];
            for (int columnNum = 0, j = 0; j < row.getLastCellNum(); columnNum++, j++) {
                Cell cell = row.getCell(columnNum);
                cell.setCellType(CellType.STRING);
                rowString[j] = new DataFormatter().formatCellValue(cell);
            }
            users.add(rowString);
        }
        server.main.User[] userArray = new server.main.User[users.size()];
        for (int i = 0; i < users.size(); i++) {
            userArray[i].setBankAccountUserId(users.get(i)[0]);
            userArray[i].setBankAccountName(users.get(i)[1]);
            userArray[i].setBankAccountPassword(users.get(i)[2]);
            userArray[i].setBankAccountRealId(users.get(i)[3]);
            userArray[i].setBankAccountPhoneNumber(users.get(i)[4]);
            userArray[i].setBankAccountSex(users.get(i)[5]);
            userArray[i].setBankAccountBirthDate(users.get(i)[6]);
            String bankAccountBalanceString = users.get(i)[7];
            double bankAccountBalanceDouble = Double.parseDouble(bankAccountBalanceString);
            userArray[i].setBankAccountBalance(bankAccountBalanceDouble);
        }
        return userArray;
    }
}






