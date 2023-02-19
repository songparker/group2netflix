package UserFunctions;

import Entities.Account;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: Register Package: register Description:
 *
 * @Author: Yateng
 * @Create: 2022-12-16 - 11:45 p.m.
 * @Version: v1.0
 */
public class Register {
    private static final String passwordRegex = "^(?=.*[A-Z]{2})(?=.*[a-z]{2})(?=.*[0-9]{2})(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{2})(?!.*\\s)[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{8,15}$";

    public static void Register() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/Register.txt", true));
        Account myAccount = new Account();
        Scanner enter = new Scanner(System.in);
        System.out.println(("Welcome to our Registration system\nEnter your username please: "));
//        myAccount.setUserName(enter.nextLine());
        String userName = enter.nextLine();
        if (userIsExisted(userName)) {
            System.out.println("This username is taken, please try again\n");
            Register();
            return;
        }else{
            myAccount.setUserName(userName);
        }
        System.out.println("Enter your password please: ");
        String pwd = enter.nextLine();
        String encodedString = "";
        if (isValid(pwd)) {
            encodedString = Base64.getEncoder().encodeToString(pwd.getBytes());
        } else {
            System.out.println("Invalide password! \n-Password should contains no space\n-At least 2 digit number(0-9)" +
                    "\n-Length should be between 8 to 15 characters" +
                    "\n-Should contain at least 2 lowercase letter(a-z)" +
                    "\n-Should contain at least 2 uppercase letter(A-Z)" +
                    "\n-Should contain at least 2 special character ( @, #, %, &, !, $, etc…)" +
                    "Please try again!");
            Register();
            return;
        }
        myAccount.setPassWord(encodedString);
        System.out.println(("Enter your first name please: "));
        myAccount.setfName(enter.nextLine());

        System.out.println("Enter your last name please: ");
        myAccount.setlName(enter.nextLine());

        System.out.println(("Enter your date of birth please: "));
        myAccount.setDateOfBirth(enter.nextLine());

        System.out.println("Enter your gender please: ");
        myAccount.setGender(enter.nextLine());

        String str = myAccount.toStringInFileFormat();
        writer.write(str + "\n");
        writer.close();
    }

    // Check user existed or not before write down into the file
    public static boolean userIsExisted(String userName) {

        Map<String, Account> accountMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Register.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split("\t");
                if (arr[0].equalsIgnoreCase(userName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isValid(String password) {
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}