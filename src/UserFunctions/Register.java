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
          private static final String passwordRegex = "^(?=.*[A-Z]{2})(?=.*[a-z]{2})(?=.*[0-9]{2})(?=.*[~`!@#$%^&*()_+\\-=\\[\\]{}\\\\|;:'\",.<>/?])(?!.*\\s)[\\p{ASCII}]{8,15}$";
          private static final String usernameRegex = "^[a-zA-Z0-9]{1,50}$";
          private static final String namesRegex = "^[a-zA-Z]{2,999}$";
          private static final String dobRegex = "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\\d|3[01])$";


    public static void Register() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/Register.txt", true));
        Account myAccount = new Account();
        Scanner enter = new Scanner(System.in);
        System.out.println(("Welcome to our Registration system\nEnter your User Name please: "));
        String userName = enter.nextLine().trim();
        while (!userName.matches(usernameRegex)) {
            System.out.println("Invalid username! Username can only contain letters and digits and have No spaces. Please try again.");
            System.out.println("Enter your User Name please: ");
            userName = enter.nextLine().trim();
        }
        if (userIsExisted(userName)) {
            System.out.println("This username is taken, please try again\n");
            Register();
            return;
        } else {
            myAccount.setUserName(userName);
        }
        System.out.println("Enter your password please: ");
        System.out.println("-Password should contain no space\n-At least 2 digit number(0-9)" +
                "\n-Length should be between 8 to 15 characters" +
                "\n-Should contain at least 2 lowercase letter(a-z)" +
                "\n-Should contain at least 2 uppercase letter(A-Z)" +
                "\n-Should contain at least 2 special character ( @, #, %, &, !, $, etc…)");
        String pwd = enter.nextLine().trim();
        while(!isValid(pwd)){
            System.out.println("Invalide password! \n-Password should contains no space\n-At least 2 digit number(0-9)" +
                    "\n-Length should be between 8 to 15 characters" +
                    "\n-Should contain at least 2 lowercase letter(a-z)" +
                    "\n-Should contain at least 2 uppercase letter(A-Z)" +
                    "\n-Should contain at least 2 special character ( @, #, %, &, !, $, etc…)" +
                    "Please try again! \nEnter you pass word please: ");
            pwd = enter.nextLine().trim();
        }
        String encodedString = Base64.getEncoder().encodeToString(pwd.getBytes());
        myAccount.setPassWord(encodedString);

        System.out.println(("Enter your first name please: "));
        String fName = enter.nextLine().trim();
        while (!fName.matches(namesRegex)) {
            System.out.println("Invalid First Name! First name can only contain letters and at least 2. Please try again.");
            System.out.println("Enter your First Name please: ");
            fName = enter.nextLine().trim();
        }
        myAccount.setfName(fName);

        System.out.println("Enter your last name please: ");
        String lName = enter.nextLine().trim();
        while (!lName.matches(namesRegex)){
            System.out.println("Invalid Last Name! Last name can only contain letters and at least 2. Please try again.");
            System.out.println("Enter your Last Name please: ");
            lName = enter.nextLine().trim();
        }
        myAccount.setlName(lName);

        System.out.println(("Enter your date of birth please (YYYY-MM-DD): "));
        String dobStr = enter.nextLine().trim();
        while (!dobStr.matches(dobRegex)) {
            System.out.println("Invalid Date of Birth! Date of Birth can only contain digits and in format \"YYYY-MM-DD\". Please try again.");
            System.out.println("Enter your Date of Birth please: ");
            dobStr = enter.nextLine().trim();
        }
        myAccount.setDateOfBirth(dobStr);

        System.out.println("Enter your gender please: ");
        String gender = enter.nextLine().trim();
        while (!gender.matches(usernameRegex)) {
            System.out.println("Invalid Gender information! Gender can only contain letters and digits. Please try again.");
            System.out.println("Enter your gender please: ");
            gender = enter.nextLine().trim();
        }
        myAccount.setGender(gender);

        String str = myAccount.toStringInFileFormat();
        writer.write(str + "\n");
        writer.close();
        System.out.println("New Account is Added Successfully!");
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