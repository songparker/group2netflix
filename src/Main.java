import UserFunctions.LogIn;
import UserFunctions.Register;

import java.util.Scanner;

/**
 * ClassName: ${NAME} Package: Description:
 *
 * @Author: Yateng
 * @Create: ${DATE} - ${TIME}
 * @Version: v1.0
 */
public class Main {
	public static void main(String[] args) throws Exception {
		Scanner enter = new Scanner(System.in);
		boolean flag = true;
		while(flag){
		System.out.println("Welcome to our NetPlix System");
		System.out.println("Please choose the option you want to do from the shown list:");
		System.out.println("1- Login\n" + "2- Register\n" + "3- Exit");
		String choice = enter.nextLine();


			switch (choice) {
				case "1":
					LogIn.Login();
					flag = false;
					break;
				case "2":
					Register.Register();
					LogIn.Login();
					flag = false;
					break;
				case "3":
					System.out.println("Thank you for using NetPlix!!");
					System.exit(0);
				default:
					System.out.println("Invalid Keyword!Please try again!");
			}
		}
	}
}
