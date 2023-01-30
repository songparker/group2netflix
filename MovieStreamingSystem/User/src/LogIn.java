
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	static Scanner enter = new Scanner(System.in);
	static Map<String, Account> accountMap = new HashMap<>();
	static int maxAttempts = 5;
	static int attempts = 1;

	public static void Login() throws FileNotFoundException {

		try {
			BufferedReader reader = new BufferedReader(new FileReader("MovieStreamingSystem/Register.txt"));
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

	public static void checkAccount() throws FileNotFoundException {
		System.out.println("Welcome to our NetFlix system\nEnter your username please: ");
		String userName = enter.nextLine();
		System.out.println("Now enter your password please: ");
		String pwd = enter.nextLine();
		Account foundAccount = accountMap.get(userName);
		if ((foundAccount != null) && (foundAccount == accountMap.get("yateng")
				|| foundAccount == accountMap.get("chester") || foundAccount == accountMap.get("sneha")
				|| foundAccount == accountMap.get("hua") || foundAccount == accountMap.get("heping"))) {
			if (foundAccount.getPassWord().equals(pwd)) {
				System.out.println("Log in success! Your account information is shown below:\n");
				System.out.println(foundAccount);
				MainAdmin.AdminMainMenu();
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
			if (foundAccount.getPassWord().equals(pwd)) {
				System.out.println("Log in success! Your account information is shown below:\n");
				System.out.println(foundAccount);
				MainUser.MainMenu();
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
	}
}
