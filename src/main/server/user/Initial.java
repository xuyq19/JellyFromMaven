package main.server.user;

/**
 * Initial user when registering
 */
public class Initial {
    public static String initial(String bankAccountName,
                                 String bankAccountPassword,
                                 String bankAccountRealId,
                                 String bankAccountPhoneNumber,
                                 char bankAccountSex,
                                 String bankAccountBirthDate){
        User user = new User();
        /**
         * int to 10bit string
         */
        int counter = 1+main.server.database.Counter.getCounter();
        if(counter<10){
            user.setBankAccountUserId("000000000"+counter);
        }else if(counter<100){
            user.setBankAccountUserId("00000000"+counter);
        }else if(counter<1000){
            user.setBankAccountUserId("0000000"+counter);
        }else if(counter<10000){
            user.setBankAccountUserId("000000"+counter);
        }else if(counter<100000){
            user.setBankAccountUserId("00000"+counter);
        }else if(counter<1000000){
            user.setBankAccountUserId("0000"+counter);
        }else if(counter<10000000){
            user.setBankAccountUserId("000"+counter);
        }else if(counter<100000000){
            user.setBankAccountUserId("00"+counter);
        }else{
            user.setBankAccountUserId("0"+counter);
        }
        user.setBankAccountName(bankAccountName);
        user.setBankAccountPassword(bankAccountPassword);
        user.setBankAccountRealId(bankAccountRealId);
        user.setBankAccountPhoneNumber(bankAccountPhoneNumber);
        user.setBankAccountSex(bankAccountSex);
        user.setBankAccountBirthDate(bankAccountBirthDate);
        user.setBankAccountBalance(2000);
        if(CheckInfo.check(user)== "OK"){
            main.server.database.Write.createUser(user);
            return "OK";
        }else{
            return CheckInfo.check(user);
        }
    }
}
