package server;
import com.alibaba.excel.*;
import server.Sheets;

import java.util.*;
import java.io.*;


public class ExcelProcessor(String bankAccountUserId,String bankAccountName,String bankAccountRealId,String bankAccountPassword,String bankAccountPhoneNumber,char bankAccountSex,String bankAccountBirthDate,double bankAccountBalance){

    String PATH = System.getProperty("user.dir") + "/src/main/resources/excel/";
    String fileName = "bankAccount.xlsx";
    private List<Sheets>data(){
        List<Sheets> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Sheets sheet = new Sheets();
            sheet.setBankAccountName("bankAccountName");
            sheet.setBankAccountRealId("bankAccountRealId");
            sheet.setBankAccountPassword("bankAccountPassword");
            sheet.setBankAccountPhoneNumber("bankAccountPhoneNumber");
            sheet.setBankAccountSex(bankAccountSex);
            sheet.setBankAccountBirthDate("bankAccountBirthDate");
            sheet.setBankAccountBalance(bankAccountBalance);
        }
        return list;
    }
    public void CreateFile() throws FileNotFoundException {
        /**
         * 创建一个excel对象
         */
        EasyExcel.write(PATH + fileName).sheet("fmBank").doWrite(data());


    }
    public void exportIntoFile() throws FileNotFoundException{
        /**
         * 写出数据
         */
        EasyExcel.write(PATH + fileName).sheet("fmBank").doWrite(data());

    }
    public void importFromFile() throws FileNotFoundException{
        /**
         * 读取数据
         */
        EasyExcel.read(PATH + fileName).sheet("fmBank").doRead();

    }
}
