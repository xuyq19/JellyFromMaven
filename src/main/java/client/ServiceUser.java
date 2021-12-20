package client;

import java.util.Scanner;

/**
 * @author xuyuqi
 */
public class ServiceUser {
    static final int USER_ID_LENGTH =10;
    /**
     * @param userId
     */
    static final int USER_NAME_LENGTH =10;
    static final int USER_REAL_ID_LENGTH =12;
    static final int USER_BIRTH_DATE_LENGTH =10;

    public ServiceUser() {
    }

    private boolean testInformation(String bankAccountUserId,String bankAccountName,String bankAccountPassword,String bankAccountRealId,String bankAccountPhoneNumber,char bankAccountSex,String bankAccountBirthDate){
        if(bankAccountUserId.length()!=USER_ID_LENGTH){
            System.out.println("The length of User ID must be 10!");
            return false;
        }
        /**
         * Test the length of User Name
         */
        for(int i = 0;i < bankAccountUserId.length();i++){
            if(bankAccountUserId.charAt(i) > '9' || bankAccountUserId.charAt(i) < '0'){
                return false;
            }
            /**
             * Test User Real ID
             */
        }
        if(bankAccountName.length()>USER_NAME_LENGTH){
            System.out.println("The length of user name must be shorter than 10!");
            return false;
        }
        /**
         * Test the length of User Name
         */
        if(bankAccountRealId.length()!=USER_REAL_ID_LENGTH) {
            System.out.println("The length of real ID must be 12!");
            return false;
        }
        /**
         * Test User Phone Number
         */
        for(int i = 0;i < bankAccountRealId.length();i++){
            if(bankAccountRealId.charAt(i) > '9' ||bankAccountRealId.charAt(i) < '0'){
                System.out.println("The id must be numbers!");
                return false;
            }
        }
        if(bankAccountSex != 'F' && bankAccountSex != 'M'){
            System.out.println("The sex must be F or M");
        }
        if(bankAccountBirthDate.length()!=USER_BIRTH_DATE_LENGTH){
            System.out.println("The format of birthday must be YYYY-MM-DD");
            return false;
        }
        if(bankAccountBirthDate.charAt(4)!='-'||bankAccountBirthDate.charAt(7)!='-'){
            System.out.println("The format of birthday must be YYYY-MM-DD");
            return false;
        }
        return true;
    }
    public boolean register(String bankAccountUserId,String bankAccountName,String bankAccountPassword,String bankAccountRealId,String bankAccountPhoneNumber,char bankAccountSex,String bankAccountBirthDate){
        if(testInformation(bankAccountUserId,bankAccountName,bankAccountPassword,bankAccountRealId,bankAccountPhoneNumber,bankAccountSex,bankAccountBirthDate)){
            return true;
        }
        return false;
    }
    public void setInformation(User auser){
        System.out.println("Please input the id of the information you want to reset");
        System.out.print("1.username\n2.phone number\n3.password\n");
        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
        switch (choose){
            case 1:{
                System.out.print("Please input the name:");
                String ausername = sc.next();
                if(testInformation(auser.getBankAccountUserId(),ausername,auser.getBankAccountPassword(),auser.getBankAccountRealId(),auser.getBankAccountPhoneNumber(),auser.getBankAccountSex(),auser.getBankAccountBirthDate())==true){
                    auser.setBankAccountName(ausername);
                }
                else {
                    System.out.println("Reset unsuccessfully!");
                }
                break;
            }
            case 2:{
                System.out.print("Please input the phone number:");
                String aphone = sc.next();
                if(testInformation(auser.getBankAccountUserId(),auser.getBankAccountName(),auser.getBankAccountPassword(),auser.getBankAccountRealId(),aphone,auser.getBankAccountSex(),auser.getBankAccountBirthDate())==true){
                    auser.setBankAccountPhoneNumber(aphone);
                    System.out.println("Reset the phone number successfully!");
                }
                else {
                    System.out.println("Reset unsuccessfully!");
                }
                break;
            }
            case 3:{
                int time = 5;
                while(time >= 0){
                    System.out.print("Please input the old password:");
                    String apassword = sc.next();
                    if(apassword.equals(auser.getBankAccountPassword())){
                        System.out.print("Correct!Please the new password:");
                        apassword = sc.next();
                        System.out.println("Reset the password successfully!");
                    }
                    else {
                        time--;
                        System.out.println("Reset unsuccessfully!You have only "+time+" times!");
                    }
                }
                if(time == 0){
                    System.out.println("Time is up!");
                }
                break;
            }
            default:{
                System.out.println("Please input the right number!");
            }
        }
        sc.close();
    }
    public boolean withdrawMoney(User auser,double money){
        if(auser.getBankAccountBalance()>money){
            auser.withdrawMoney(money);
            return true;
        }
        else{
            return false;
        } 
    }
    public void depositMoney(User auser,double money){
        depositMoney(auser, money);
    }
    public void transferMoney(User from,User to,double money){
        if(from.getBankAccountBalance()>=money){
            from.withdrawMoney(money);
            to.depositMoney(money);
        }
    }
}
