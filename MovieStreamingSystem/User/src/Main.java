import registerAndLogin.LogIn;
import registerAndLogin.Register;

import java.util.Scanner;

/**
 * ClassName: ${NAME}
 * Package:
 * Description:
 *
 * @Author: Yateng
 * @Create: ${DATE} - ${TIME}
 * @Version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        Scanner enter = new Scanner(System.in);
        //invoke user enter a choice
        System.out.println("Welcome to our NetPlix system\nDo you have an account? Y/N");
        //control the while loop in case of mis-matching input
        boolean flag = true;
        while(flag){
            String str = enter.nextLine().toUpperCase();
            switch (str){
                //user enter Y to invoke the Login method
                case "Y":
                    LogIn.Login();
                    flag = false;
                    break;
                    //user enter N to invoke the Register method
                case "N":
                    Register.Register();
                    flag = false;
                    break;
                    //In case of mis-matching input, re-invoke user to enter
                default:
                    System.out.println("Input error, please enter Y or N to choose");
                    break;
                    }
            }
        enter.close();
        }

    }
