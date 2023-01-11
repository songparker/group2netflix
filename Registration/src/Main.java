import register.Account;
import register.LogIn;
import register.Register;

import java.util.Scanner;

import static javax.swing.text.html.HTML.Attribute.N;

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
        System.out.println("Welcome to our NetPlix system\nDo you have an account? Y/N");

        boolean flag = true;
        while(flag){
            String str = enter.nextLine().toUpperCase();
            switch (str){
                case "Y":
                    LogIn.Login();
                    flag = false;
                    break;
                case "N":
                    Register.Register();
                    flag = false;
                    break;
                default:
                    System.out.println("Input error, please enter Y or N to choose");
                    break;
                    }
            }
        enter.close();
        }

    }
