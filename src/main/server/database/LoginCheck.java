package main.server.database;
/**
 * @author xuyuq
 */
public class LoginCheck {
    public static boolean login(String bankAccountUserId,String bankAccountPassword){
        if(ReadSingle.checkInformation(bankAccountUserId,bankAccountPassword)){
            return true;
        }else{
            return false;
        }

    }


}
