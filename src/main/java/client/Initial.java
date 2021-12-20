package client;

public class Initial {
    /**
     * This is a class that is used to initialize the client.
     */
    /**
     * This is an index number for users
     */
    public static final int USER_INDEX = 3;
    private User[] users=new User[1000000000];
    public Initial(){
        users[0]=new User("00000000001","张三","123456","000000000001","123456789",'M',"2000-01-01",2000);
        users[1]=new User("00000000002","李四","123456","000000000002","123456789",'F',"2000-01-01",2000);
        users[2]=new User("00000000003","王五","123456","000000000003","123456789",'F',"2000-01-01",2000);
    }
}
