
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ClassName: LogIn
 * Package: register
 * Description:
 *
 * @Author: Yateng
 * @Create: 2022-12-17 - 12:15 a.m.
 * @Version: v1.0
 */
public class LogIn {

    public static void Login() {
        Scanner enter = new Scanner(System.in);
        Map<String, Account> accountMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MovieStreamingSystem/Register.txt"));
            String line;
            while((line = reader.readLine()) != null){
                String[] arr = line.split("\t");
                Account newAccount = new Account();
                newAccount.setUserName(arr[0]);
                newAccount.setPassWord(arr[1]);
                newAccount.setfName(arr[2]);
                newAccount.setlName(arr[3]);
                newAccount.setDateOfBirth(arr[4]);
                newAccount.setGender(arr[5]);
                accountMap.put(newAccount.getUserName(), newAccount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to our NetFlix system\nEnter your user name please: ");
        String userName = enter.nextLine();
        System.out.println("Now enter your pass word please: ");
        String pwd = enter.nextLine();
        Account foundAccount = accountMap.get(userName);
        if(foundAccount != null){
            if(foundAccount.getPassWord().equals(pwd)){
                System.out.println("Log in success! Your account information is shown below:");
                System.out.println(foundAccount); //Here is using account info instead of the user account page
            }else{
                System.out.println("Check you pass word and try again later please!");
                System.exit(0);
            }
        }else{
            System.out.println("Check you user name and try again later please!");
            System.exit(0);
        }



    }
}
