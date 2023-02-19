package UserFunctions;

import Entities.Account;

import java.io.*;
import java.util.Base64;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditUserInfo {

    private static final String passwordRegex = "^(?=.*[A-Z]{2})(?=.*[a-z]{2})(?=.*[0-9]{2})(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{2})(?!.*\\s)[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{8,15}$";
    static Scanner scan = new Scanner(System.in);
    public static void editUserInfo() throws Exception {
        editUser();
        while (true) {
            System.out.println("Do you want change anything else?\nY - Back to Edit personal Information menu" +
                    "\nN - Back ro Main menu");
            String num = scan.nextLine();
            if (num.equalsIgnoreCase("Y")) {
                editUser();
            } else if (num.equalsIgnoreCase("N")) {
                Menus.UserMenu();
            } else {
                System.out.println("Invalid Input!Please try again!");
            }
        }
    }


    public static void editUser() throws Exception {
        Set<Account> accountSet = new HashSet<>();
        try {
            File inputFile = new File("src/Register.txt");
            File tempFile = new File("src/tempo.txt");
            BufferedReader reader = new BufferedReader(new FileReader("src/Register.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/tempo.txt"));

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
                accountSet.add(newAccount);
            }
            Account account = LogIn.accountFound();

            accountSet.remove(account);
            Account editedAccount = editAccount(account);
            accountSet.add(editedAccount);
            for(Account a: accountSet){
                String lineInFile = a.toStringInFileFormat();
                bw.write(lineInFile + "\r\n");
            }
            bw.close();
            inputFile.delete();
            if (tempFile.renameTo(new File("src/Register.txt"))) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    private static Account editAccount(Account found) throws Exception {

        boolean flag = true;
        Account changedAccount = found;
        while(flag){

            System.out.println("Which part do you want to edit please");
            System.out.println("1- Change Password\n" +
                    "2- Change First Name\n" +
                    "3- Change Last Name\n" +
                    "4- Change Date of Birth\n"+
                    "5- Change Gender (Caution!)\n" +
                    "6- Back to main menu");
            String editPart = scan.nextLine();

            switch (editPart){
                case "1":
                    changedAccount = editPwd(found);
                    System.out.println("Password edit successful!");
                    flag = false;
                    break;
                case "2":
                    changedAccount = editFname(found);
                    System.out.println("First Name edit successful!");
                    flag = false;
                    break;
                case "3":
                    changedAccount = editLname(found);
                    System.out.println("last Name edit successful!");
                    flag = false;
                    break;
                case "4":
                    changedAccount = editDOB(found);
                    System.out.println("Date of Birth edit successful!");
                    flag = false;
                    break;
                case "5":
                    changedAccount = editGender(found);
                    System.out.println("Gender edit successful!");
                    flag = false;
                    break;
                case "6":
                    Menus.UserMenu();
                    flag = false;
                    break;
                default:
                    System.out.println("Input error, please enter 1-6 to choose from the list shown");
            }
        }
        return changedAccount;
    }

    private static Account editPwd(Account found){
        String line = found.toStringInFileFormat();
        String[] arr = line.split("\t");


        System.out.println("Please enter you old pass word");
        String oldPwd = scan.nextLine();
        String encodedString = Base64.getEncoder().encodeToString(oldPwd.getBytes());
        if(arr[1].equals(encodedString)){

            String newEncodedPwd = "";

            boolean flag = true;
            while(flag){
                System.out.println("Please enter your new pass word now");
                String newPwd = scan.nextLine();
                if (isValid(newPwd)) {
                    newEncodedPwd = Base64.getEncoder().encodeToString(newPwd.getBytes());
                    flag = false;
                } else {
                    System.out.println("Invalide password! \n-Password should contains no space\n-At least 2 digit number(0-9)" +
                            "\n-Length should be between 8 to 15 characters" +
                            "\n-Should contain at least 2 lowercase letter(a-z)" +
                            "\n-Should contain at least 2 uppercase letter(A-Z)" +
                            "\n-Should contain at least 2 special character ( @, #, %, &, !, $, etc…)" +
                            "Please try again!");
                }
            }
            found.setPassWord(newEncodedPwd);
            return found;
        }else{
            System.out.println("Pass word incorrect, please try again!");
            editPwd(found);
        }
        return found;
    }

    private static Account editFname(Account found){
        System.out.println("Enter your new Fisrt Name please");
        String newFname = scan.nextLine();
        found.setfName(newFname);
        System.out.println(found);
        return found;
    }

    private static Account editLname(Account found){
        System.out.println("Enter your new Last Name please");
        String newLname = scan.nextLine();
        found.setlName(newLname);
        return found;
    }

    private static Account editDOB(Account found){
        System.out.println("Enter your new Date of Birth please");
        String newDOB = scan.nextLine();
        found.setDateOfBirth(newDOB);
        return found;
    }

    private static Account editGender(Account found){
        System.out.println("Enter your new Gender (with caution) please");
        String newGender = scan.nextLine();
        found.setGender(newGender);
        return found;
    }
//check if user entered new pass word is valid or not
    public static boolean isValid(String password) {
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


}
