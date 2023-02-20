package UserFunctions;

import Entities.Account;

import java.io.*;
import java.util.Base64;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EditUserInfo {
    private static final String namesRegex = "^[a-zA-Z]{2,999}$";
    private static final String dobRegex = "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\\d|3[01])$";
    private static final String genderRegex = "^[a-zA-Z0-9]{1,15}$";
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 15;
    private static final String PASSWORD_REGEX = "^(?=.*[a-z].*[a-z])(?=.*[A-Z].*[A-Z])(?=.*\\d.*\\d)(?=.*[@#$%^&+=!].*[@#$%^&+=!])(?=\\S+$).{" + MIN_PASSWORD_LENGTH + "," + MAX_PASSWORD_LENGTH + "}$";
    private static final int MAX_ATTEMPTS = 3;
    static Scanner scan = new Scanner(System.in);

    public static void editUserInfo() throws Exception {
        editUser();
        while (true) {
            System.out.println("Do you want change anything else?\nY - Back to Edit personal Information menu" +
                    "\nN - Back to Main menu");
            String num = scan.nextLine().trim();
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
            for (Account a : accountSet) {
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
        while (flag) {

            System.out.println("Which part do you want to edit please");
            System.out.println("1- Change Password\n" +
                    "2- Change First Name\n" +
                    "3- Change Last Name\n" +
                    "4- Change Date of Birth\n" +
                    "5- Change Gender (Caution!)\n" +
                    "6- Back to main menu");
            String editPart = scan.nextLine().trim();

            switch (editPart) {
                case "1":
                    changedAccount = editPwd(found);

                    flag = false;
                    break;
                case "2":
                    changedAccount = editFname(found);

                    flag = false;
                    break;
                case "3":
                    changedAccount = editLname(found);

                    flag = false;
                    break;
                case "4":
                    changedAccount = editDOB(found);
                    flag = false;
                    break;
                case "5":
                    changedAccount = editGender(found);
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
    private static Account editPwd(Account found) {
        String line = found.toStringInFileFormat();
        String[] arr = line.split("\t");
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.println("Please enter your old password:");
            String oldPwd = scan.nextLine().trim();
            String encodedString = Base64.getEncoder().encodeToString(oldPwd.getBytes());
            if (arr[1].equals(encodedString)) {
                boolean flag = true;
                while (flag) {
                    System.out.println("Please enter your new password, or enter \"N\" to go back to the previous menu:");
                    String newPwd = scan.nextLine().trim();
                    if (newPwd.equalsIgnoreCase("N")) {
                        try {
                            editAccount(found);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        flag = false;
                    } else if (isValidPwd(newPwd)) {
                        String newEncodedPwd = Base64.getEncoder().encodeToString(newPwd.getBytes());
                        found.setPassWord(newEncodedPwd);
                        System.out.println("Password edited successfully. Please restart to validate your new password.");
                        flag = false;
                    } else {
                        System.out.println("Invalid password! The password must:\n" +
                                "- contain no spaces\n" +
                                "- be between " + MIN_PASSWORD_LENGTH + " and " + MAX_PASSWORD_LENGTH + " characters long\n" +
                                "- contain at least 2 digits\n" +
                                "- contain at least 2 lowercase letters\n" +
                                "- contain at least 2 uppercase letters\n" +
                                "- contain at least 2 special characters (@, #, %, &, !, $, etc.)\n" +
                                "Please try again.");
                    }
                }
                return found;
            } else {
                attempts++;
                System.out.println("Incorrect password. Please try again.");
            }
        }
        System.out.println("Too many attempts. Please try again later.");
        return found;
    }

    private static boolean isValidPwd(String password) {
        return password.matches(PASSWORD_REGEX);
    }
    private static Account editFname(Account found) {
        System.out.println("Enter your new First Name please, or you can enter \"N\" to go back to the previous menu.");
        String newFname = scan.nextLine().trim();
        boolean flag = true;
        while (flag) {
            if (newFname.equalsIgnoreCase("N")) {
                try {
                    editAccount(found);
                    flag = false;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (!newFname.matches(namesRegex)) {
                System.out.println("Invalid First Name! First name can only contain letters and minimum 2 letters. Please try again.");
                System.out.println("Enter your First Name please(\"N\" go back to the previous menu): ");
                newFname = scan.nextLine().trim();
            } else {
                found.setfName(newFname);
                System.out.println(found);
                System.out.println("First Name edit successful!");
                flag = false;
            }
        }
        return found;
    }


    private static Account editLname(Account found) {
        System.out.println("Enter your new Last Name please, or you can enter \"N\" to go back to the previous menu.");
        String newLname = scan.nextLine().trim();
        boolean flag = true;
        while (flag) {
            if (newLname.equalsIgnoreCase("N")) {
                try {
                    editAccount(found);
                    flag = false;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (!newLname.matches(namesRegex)) {
                System.out.println("Invalid Last name! Last name can only contain letters and minimum 2 letters. Please try again.");
                System.out.println("Enter your Last Name please(\"N\" go back to the previous menu): ");
                newLname = scan.nextLine().trim();
            } else {
                found.setlName(newLname);
                System.out.println(found);
                System.out.println("Last Name edit successful!");
                flag = false;
            }
        }
        return found;
    }

    private static Account editDOB(Account found) {
        System.out.println("Enter your new Date of Birth please (YYYY-MM-DD), or you can enter \"N\" to go back to the previous menu.");
        String newDOB = scan.nextLine().trim();
        boolean flag = true;
        while (flag) {
            if (newDOB.equalsIgnoreCase("N")) {
                try {
                    editAccount(found);
                    flag = false;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (!newDOB.matches(dobRegex)) {
                System.out.println("Invalid Date of Birth! Date of Birth can only contain digits and in format \"YYYY-MM-DD\". Please try again.");
                System.out.println("Enter your Date of Birth please (\"N\" go back to the previous menu): ");
                newDOB = scan.nextLine().trim();
            } else {
                found.setDateOfBirth(newDOB);
                System.out.println(found);
                System.out.println("Date of Birth edit successful!");
                flag = false;
            }
        }
        return found;
    }

    private static Account editGender(Account found) {

        System.out.println("Enter your new Gender please, or you can enter \"N\" to go back to the previous menu.");
        String newGender = scan.nextLine().trim();
        boolean flag = true;
        while (flag) {
            if (newGender.equalsIgnoreCase("N")) {
                try {
                    editAccount(found);
                    flag = false;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (!newGender.matches(genderRegex)) {
                System.out.println("Invalid Gender information! Gender can only contain letters and digits. Please try again.");
                System.out.println("Enter your gender please(\"N\" go back to the previous menu) : ");
                newGender = scan.nextLine().trim();
            } else {
                found.setGender(newGender);
                System.out.println(found);
                System.out.println("Gender edit successful!");
                flag = false;
            }
        }
        return found;
    }
}
