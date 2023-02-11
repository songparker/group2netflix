package UserFunctions;

import Entities.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ClassName: LogIn Package: register Description:
 *
 * @Author: Yateng
 * @Create: 2022-12-17 - 12:15 a.m.
 * @Version: v1.0
 */
public class LogIn {
	static Scanner input = new Scanner(System.in);
	static Map<String, Account> accountMap = new HashMap<>();
	static String userName = "";
	static int maxAttempts = 5;
	static int attempts = 1;

	public static void Login() throws Exception {

		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/Register.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
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
		checkAccount();
	}



	public static Account checkAccount() throws Exception {

		System.out.println("Welcome to our NetPlix system");
		System.out.println("Enter your username please:");
		userName = input.nextLine();
		System.out.println("Now enter your password please: ");
		String pwd = input.nextLine();
		Account foundAccount = accountMap.get(userName);
		if ((foundAccount != null) && (foundAccount.equals(accountMap.get("yateng"))
				|| foundAccount.equals(accountMap.get("chester")) || foundAccount.equals(accountMap.get("sneha"))
				|| foundAccount.equals(accountMap.get("hua")) || foundAccount.equals(accountMap.get("heping")))) {

			String encodedString = Base64.getEncoder().encodeToString(pwd.getBytes());
//			System.out.println(encodedString);
			if (foundAccount.getPassWord().equals(encodedString)) {
				System.out.println("Log in success! Your account information is shown below:\n");
				System.out.println(foundAccount);
				Menus.AdminMenu();
				//return userName;
			} else {
				System.out.println("Check you password and try again later please!\n");
				if (attempts != maxAttempts) {
					System.out.println(attempts + " out of " + maxAttempts);
					attempts++;
					checkAccount();
				} else {
					System.out.println(attempts + " out of " + maxAttempts);
					System.out.println("System is Closed....");
					System.exit(0);
				}
			}
		} else if (foundAccount != null) {
			String encodedString = Base64.getEncoder().encodeToString(pwd.getBytes());
//			System.out.println(encodedString);
			if (foundAccount.getPassWord().equals(encodedString)) {
				System.out.println("Log in success! Your account information is shown below:\n");
				System.out.println(foundAccount);
				Menus.UserMenu();
				//return userName;
			} else {
				System.out.println("Check you password and try again later please!\n");
				if (attempts != maxAttempts) {
					System.out.println(attempts + " out of " + maxAttempts);
					attempts++;
					checkAccount();
				} else {
					System.out.println(attempts + " out of " + maxAttempts);
					System.out.println("System is Closed....");
					System.exit(0);
				}
			}
		} else {
			System.out.println("Check you username/password and try again later please!\n");
			if (attempts != maxAttempts) {
				System.out.println(attempts + " out of " + maxAttempts);
				attempts++;
				checkAccount();
			} else {
				System.out.println(attempts + " out of " + maxAttempts);
				System.out.println("System is Closed....");
				System.exit(0);
			}
		}

		return foundAccount;
	}

	public static Account accountFound(){
		return accountMap.get(userName);
	}
}
