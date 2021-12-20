
package client;
import java.util.*;
import java.io.*;
import java.net.*;
import server.*;

import static java.lang.System.exit;

/**
 * @author xuyuqi
 */

/**
 * This class is used to store the functions of the admins.
 */
public class Admins {
    public Admins() {}
    public void main(String bankAccountUserId,String bankAccountPassword) throws FileNotFoundException {
        if(isAdmin(bankAccountUserId,bankAccountPassword)){
            System.out.println("Welcome to the admin page!");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the function you want to use:");
            int function = scanner.nextInt();
            switch(function){
                case 1:
                    setUserInfomation();
                    break;
                case 2:
                    exportDatabaseIntoExcels();
                    break;
                case 3:
                    importDatabaseFromExcels();
                    break;
                case 4:
                    exit(0);
                    break;
                default:
                    System.out.println("Please enter the correct function!");
                    break;

            }
        }
        else{
            System.out.println("You are not an admin!");
            System.exit(1);
        }
    }
    public boolean isAdmin(String bankAccountUserId,String bankAccountPassword){
        if(bankAccountUserId.equals("admin") && bankAccountPassword.equals("12345678890")){
            return true;
        }
        else{
            return false;
        }
    }
    private void setUserInfomation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the user Id:");
        String bankAccountUserId = scanner.nextLine();


    }
    private void exportDatabaseIntoExcels() throws FileNotFoundException {
        server.ExcelProcessor excelProcessor = new server.ExcelProcessor();
        excelProcessor.exportIntoFile();
    }
    private void importDatabaseFromExcels() throws FileNotFoundException {
        server.ExcelProcessor excelProcessor = new server.ExcelProcessor();
        excelProcessor.importFromFile();
    }
    private void createBankAccount() {
        /**
         * This function is used to create a new bank account.
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the user Id:");
        String bankAccountUserId = scanner.nextLine();
        System.out.println("Please enter the password:");
        String bankAccountPassword = scanner.nextLine();
        System.out.println("Please enter the name:");
        String bankAccountName = scanner.nextLine();
        System.out.println("Please enter the phone number:");
        String bankAccountPhoneNumber = scanner.nextLine();
        System.out.println("Please enter the address:");
        String bankAccountAddress = scanner.nextLine();
        System.out.println("Please enter the balance:");
        double bankAccountBalance = scanner.nextDouble();

        server.Database.addBankAccount(bankAccount);
    }

}
