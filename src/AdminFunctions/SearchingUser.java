package AdminFunctions;

import Entities.Account;
import UserFunctions.Menus;

import java.io.*;
import java.util.*;

/**
 * ClassName: SearchingUser Package: SearchingUser Description: This method is
 * used for searching users by username...
 *
 * @Author: Hua Zhang
 * @Create: 2023-01-13
 * @Version: v1.0
 */
public class SearchingUser {
    // A Scanner constant can be used anywhere in the class
    static Scanner enter = new Scanner(System.in);
    private static final String passwordRegex = "^(?=.*[A-Z]{2})(?=.*[a-z]{2})(?=.*[0-9]{2})(?=.*[~`!@#$%^&*()_+\\-=\\[\\]{}\\\\|;:'\",.<>/?])(?!.*\\s)[\\p{ASCII}]{8,15}$";
    private static final String usernameRegex = "^[a-zA-Z0-9]{1,50}$";
    private static final String namesRegex = "^[a-zA-Z]{2,999}$";
    private static final String dobRegex = "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\\d|3[01])$";

    public static void SearchingUser() {
        while (true) {
            // prompt users to choose one searching option
            System.out.println("Please choose one searching option:");
            System.out.println("1-Search by Username\n" + "2-Search by first name\n" + "3-Search by last name\n"
                    + "4-Search by date of birth\n" + "5-Back to Main Menu\n");
            String option = enter.nextLine().trim();
            switch (option) {
                case "1":
                    SearchByUsername();
                    break;
                case "2":
                    SearchByFName();
                    break;
                case "3":
                    SearchByLName();
                    break;
                case "4":
                    SearchByDOB();
                    break;
                case "5":
                    try {
                        Menus.AdminMenu();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                default:
                    System.out.println("Input error, please choose 1 - 5 from list shown.");
                    break;
            }

        }

    }

    private static void SearchByLName() {
        Set<Account> alluser = UserReadin();
        List<Account> searchByLname = new ArrayList<>();
        boolean flag = true;

        while (flag) {
            System.out.println("Please enter Last name:");
            String userInput = enter.nextLine().toLowerCase().trim();

            while (!userInput.matches(namesRegex)) {
                System.out.println("Invalid Last Name! Last name can only contain letters and at least 2. Please try again.");
                System.out.println("Enter the Last Name you want to search please: ");
                userInput = enter.nextLine().toLowerCase().trim();
            }

            for (Account user : alluser) {
                if (user.getlName().toLowerCase().contains(userInput)) {
                    boolean b = searchByLname.stream().anyMatch(m -> m.getUserName().equals(user.getUserName()));
                    if (!b) {
                        searchByLname.add(user);
                    }
                }
            }
            if (searchByLname.size() != 0) {
                System.out.println("The search result is shown below:\n");
                searchByLname.forEach(System.out::println);
                edit(searchByLname);
//				break;
            } else {
                System.out.println("No user founded with this Last Name!");
                System.out.println("Do you want to continue searching by Last Name? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")) {
                    SearchByLName();
                } else if (command.equalsIgnoreCase("n")) {
                    System.out.println("Going back to search user main menu...");
                    SearchingUser();
                } else {
                    System.out.println("Input error, Going back to search user main menu...");
                    SearchingUser();
                }
            }
            flag = false;
        }
    }

    private static void SearchByDOB() {

        Set<Account> alluser = UserReadin();
        List<Account> searchByDOB = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter the Date of Birth you want to search (YYYY-MM-DD) :");
            String userInput = enter.nextLine();

            while (!userInput.matches(dobRegex)) {
                System.out.println("Invalid Date of Birth! Date of Birth can only contain digits and in format \"YYYY-MM-DD\". Please try again.");
                System.out.println("Enter the Date of Birth you want to search please: ");
                userInput = enter.nextLine();
            }

            for (Account user : alluser) {
                if (user.getDateOfBirth().equalsIgnoreCase(userInput)) {
                    boolean b = searchByDOB.stream().anyMatch(m -> m.getUserName().equals(user.getUserName()));
                    if (!b) {
                        searchByDOB.add(user);
                    }
                }
            }
            if (searchByDOB.size() != 0) {
                System.out.println("The search result is shown below:\n");
                searchByDOB.forEach(System.out::println);
                edit(searchByDOB);
//				break;
            } else {
                System.out.println("No user founded with the Date of Birth!");
                System.out.println("Do you want to continue searching by DOB? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")) {
                    SearchByDOB();
                } else if (command.equalsIgnoreCase("n")) {
                    System.out.println("Going back to search user main menu...");
                    SearchingUser();
                } else {
                    System.out.println("Input error, Going back to search user main menu...");
                    SearchingUser();
                }
            }
            flag = false;
        }
    }

    private static void SearchByFName() {

        Set<Account> alluser = UserReadin();
        List<Account> searchByFname = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter First name you want to search:");
            String userInput = enter.nextLine().toLowerCase().trim();
            while (!userInput.matches(namesRegex)) {
                System.out.println("Invalid First Name! First name can only contain letters and at least 2. Please try again.");
                System.out.println("Enter your First Name please: ");
                userInput = enter.nextLine().toLowerCase().trim();
            }

            for (Account user : alluser) {
                if (user.getfName().toLowerCase().contains(userInput)) {
                    boolean b = searchByFname.stream().anyMatch(m -> m.getUserName().equals(user.getUserName()));
                    if (!b) {
                        searchByFname.add(user);
                    }
                }
            }
            if (searchByFname.size() != 0) {
                System.out.println("The search result is shown below:\n");
                searchByFname.forEach(System.out::println);
                edit(searchByFname);
//				break;

            } else {
                System.out.println("No user founded with this First Name!");
                System.out.println("Do you want to continue searching by First Name? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")) {
                    SearchByFName();
                } else if (command.equalsIgnoreCase("n")) {
                    System.out.println("Going back to search user main menu...");
                    SearchingUser();
                } else {
                    System.out.println("Input error, Going back to search user main menu...");
                    SearchingUser();
                }
            }
            flag = false;
        }
    }

    private static void SearchByUsername() {

        Set<Account> alluser = UserReadin();
        List<Account> searchByUsername = new ArrayList<>();
        boolean flag = true;

        while (flag) {
            System.out.println("Please enter user name:");
            String userInput = enter.nextLine().toLowerCase().trim();

            while (!userInput.matches(usernameRegex)) {
                System.out.println("Invalid username! Username can only contain letters and digits and have No spaces. Please try again.");
                System.out.println("Enter your User Name please: ");
                userInput = enter.nextLine().toLowerCase().trim();
            }

            for (Account user : alluser) {
                if (user.getUserName().toLowerCase().contains(userInput)) {
                    boolean b = searchByUsername.stream().anyMatch(m -> m.getUserName().equals(user.getUserName()));
                    if (!b) {
                        searchByUsername.add(user);
                    }
                }
            }
            if (searchByUsername.size() != 0) {
                System.out.println("The search result is shown below:\n");
                searchByUsername.forEach(System.out::println);
                edit(searchByUsername);
            } else {
                System.out.println("No user founded with this User Name");
                System.out.println("Do you want to continue searching by username? Y/N");
                String command = enter.nextLine().trim();
                if (command.equalsIgnoreCase("y")) {
                    SearchByUsername();
                } else if (command.equalsIgnoreCase("n")) {
                    System.out.println("Going back to search user main menu...");
                    SearchingUser();
                } else {
                    System.out.println("Input error, Going back to search user main menu...");
                    SearchingUser();
                }
            }
            flag = false;
        }
    }

    // method to read in account database
    public static Set UserReadin() {
        Set<Account> userList = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Register.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split("\t");
                Account user = new Account();
                user.setUserName(arr[0]);
                user.setPassWord(arr[1]);
                user.setfName(arr[2]);
                user.setlName(arr[3]);
                user.setDateOfBirth(arr[4]);
                user.setGender(arr[5]);
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private static void edit(List<Account> searchResult) {
        Set<Account> allUsers = UserReadin();
        System.out.println("1- Delete all\n" +
                "2- Enter the userName to delete specific user account\n" +
                "3- Enter the username to edit specific user account\n" +
                "4- Back to Search User menu");

        String num = enter.nextLine().trim();
        switch (num) {
            case "1":
                deleteAll(searchResult, allUsers);
                break;
            case "2":
                deleteOne(allUsers);
                break;
            case "3":
                editOneUser(allUsers);
                break;
            case "4":
                break;
            default:
                System.out.println("Input error, Backing to search user main menu");
                SearchingUser();
                break;
        }
    }

    private static void deleteAll(List<Account> seachResult, Set<Account> allUser) {
        for (Account a : seachResult) {
            String userName = a.getUserName();
            allUser.removeIf(e -> userName.equals(e.getUserName()));
        }

        try {
            fileOperation(allUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Deleted successfully!");


    }

    private static void deleteOne(Set<Account> allUser) {
        boolean flag = true;
        while (flag) {

            System.out.println("Enter the user name that you want to delete please. Or you can enter \"N\" back to previous menu");
            String userName = enter.nextLine().trim();
            while (!userName.matches(usernameRegex)) {
                System.out.println("Invalid username! Username can only contain letters and digits and have No spaces. Please try again.");
                System.out.println("Enter your User Name please: ");
                userName = enter.nextLine().trim();
            }


            Account foundAccount = new Account();
            for (Account a : allUser) {
                if (userName.equals(a.getUserName())) {
                    foundAccount = a;
                    break;
                }
            }
            if (!(foundAccount.getUserName() == null)) {
                allUser.remove(foundAccount);
                try {
                    fileOperation(allUser);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println("User \"" + userName + "\" is deleted successfully!");
                flag = false;
            } else if (userName.equalsIgnoreCase("N")) {
                flag = false;
            } else {
                System.out.println("Input error, user \"" + userName + "\" is not found, Check your input and try again please!");
            }
        }


    }

    private static void editOneUser(Set<Account> allUser) {
        System.out.println("Enter the userName that you want to edit please, Or you can enter \"N\" back to previous menu");
        String userName = enter.nextLine().trim();
        while (!userName.matches(usernameRegex)) {
            System.out.println("Invalid username! Username can only contain letters and digits and have No spaces. Please try again.");
            System.out.println("Enter the userName that you want to edit please: ");
            userName = enter.nextLine().trim();
        }
        Account account = null;
        for (Account a : allUser) {
            if (userName.equals(a.getUserName())) {
                account = a;
            }
        }
        if (account != null) {
            System.out.println("Found account is: \n" + account);
            allUser.remove(account);
            boolean flag = true;
            while (flag) {
                System.out.println("Which part do you want to edit please");
                System.out.println("1- Reset Password\n" +
                        "2- Change First Name\n" +
                        "3- Change Last Name\n" +
                        "4- Change Date of Birth\n" +
                        "5- Change Gender (Caution!)\n" +
                        "6- Back to Search User Main Menu");
                String editPart = enter.nextLine().trim();
                String changeOrNot = "";
                switch (editPart) {
                    case "1":
                        String defaultPassword = "AAbb11@@33";
                        String newPw = Base64.getEncoder().encodeToString(defaultPassword.getBytes());
                        account.setPassWord(newPw);
                        System.out.println("The Password of User: " + userName + " has been rested successfully!");
                        System.out.println("Do you want change anything else? Y/N");
                        changeOrNot = enter.nextLine().trim();

                        if (changeOrNot.equalsIgnoreCase("Y")) {

                        } else if (changeOrNot.equalsIgnoreCase("N")) {
                            System.out.println("All changes SAVED, going back to Search User Main Menu...");
                            flag = false;
                        } else {
                            System.out.println("Input error, all changes SAVED, going back to Search User Main Menu...");
                            flag = false;
                        }
                        break;
                    case "2":
                        System.out.println("Enter the new First Name please:");
                        String newFName = enter.nextLine().trim();
                        while (!newFName.matches(namesRegex)) {
                            System.out.println("Invalid First Name! First name can only contain letters and at least 2. Please try again.");
                            System.out.println("Enter the new First Name please: ");
                            newFName = enter.nextLine().trim();
                        }
                        account.setfName(newFName);

                        System.out.println("The First name of User: " + userName + " has been changed to " + newFName);

                        System.out.println("Do you want change anything else? Y/N");
                        changeOrNot = enter.nextLine().trim();

                        if (changeOrNot.equalsIgnoreCase("Y")) {

                        } else if (changeOrNot.equalsIgnoreCase("N")) {
                            System.out.println("All changes SAVED, going back to Search User Main Menu...");
                            flag = false;
                        } else {
                            System.out.println("Input error, all changes SAVED, going back to Search User Main Menu...");
                            flag = false;
                        }
                        break;
                    case "3":
                        System.out.println("Enter the new Last Name please:");
                        String newLName = enter.nextLine().trim();
                        while (!newLName.matches(namesRegex)) {
                            System.out.println("Invalid Last Name! Last name can only contain letters and at least 2. Please try again.");
                            System.out.println("Enter the new Last Name please: ");
                            newLName = enter.nextLine().trim();
                        }

                        account.setlName(newLName);
                        System.out.println("The Last name of User " + userName + " has been changed to " + newLName);

                        System.out.println("Do you want change anything else? Y/N");
                        changeOrNot = enter.nextLine().trim();

                        if (changeOrNot.equalsIgnoreCase("Y")) {

                        } else if (changeOrNot.equalsIgnoreCase("N")) {
                            System.out.println("All changes SAVED, going back to Search User Main Menu...");
                            flag = false;
                        } else {
                            System.out.println("Input error, all changes SAVED, going back to Search User Main Menu...");
                            flag = false;
                        }
                        break;
                    case "4":
                        System.out.println("Enter the new Date of Birth please (YYYY-MM-DD):");
                        String newDOB = enter.nextLine();
                        while (!newDOB.matches(dobRegex)) {
                            System.out.println("Invalid Date of Birth! Date of Birth can only contain digits and in format \"YYYY-MM-DD\". Please try again.");
                            System.out.println("Enter the new Date of Birth please: ");
                            newDOB = enter.nextLine();
                        }
                        account.setDateOfBirth(newDOB);
                        System.out.println("The Date of Birth of User: " + userName + " has been changed to " + newDOB);
                        System.out.println("Do you want change anything else? Y/N");
                        changeOrNot = enter.nextLine().trim();

                        if (changeOrNot.equalsIgnoreCase("Y")) {

                        } else if (changeOrNot.equalsIgnoreCase("N")) {
                            System.out.println("All changes SAVED, going back to Search User Main Menu...");
                            flag = false;
                        } else {
                            System.out.println("Input error, all changes SAVED, going back to Search User Main Menu...");
                            flag = false;
                        }
                        break;
                    case "5":
                        System.out.println("Enter the new Gender please(CAUTION):");
                        String newGender = enter.nextLine().trim();
                        while (!newGender.matches(usernameRegex)) {
                            System.out.println("Invalid Gender information! Gender can only contain letters and digits. Please try again.");
                            System.out.println("Enter the new gender please: ");
                            newGender = enter.nextLine().trim();
                        }
                        account.setGender(newGender);

                        System.out.println("The Gender of User: " + userName + " has been changed to " + newGender);
                        System.out.println("Do you want change anything else? Y/N");
                        changeOrNot = enter.nextLine().trim();

                        if (changeOrNot.equalsIgnoreCase("Y")) {

                        } else if (changeOrNot.equalsIgnoreCase("N")) {
                            System.out.println("All changes SAVED, going back to Search User Main Menu...");
                            flag = false;
                        } else {
                            System.out.println("Input error, all changes SAVED, going back to Search User Main Menu...");
                            flag = false;
                        }
                        break;
                    case "6":
                        flag = false;
                        break;
                    default:
                        System.out.println("Input error, please enter 1-6 to choose from the list shown");
                        break;
                }

            }
            allUser.add(account);
        } else if (userName.equalsIgnoreCase("N")) {
            return;
        } else {
            System.out.println("No account found with " + userName + ", Check you input and try again please!");
            editOneUser(allUser);
            return;
        }

        try {
            fileOperation(allUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void fileOperation(Set<Account> allUser) throws Exception {
        File inputFile = new File("src/Register.txt");
        File tempFile = new File("src/tempo.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/tempo.txt"));

        for (Account a : allUser) {
            String lineInFile = a.toStringInFileFormat();
            //write down the new data into tempo file
            bw.write(lineInFile + "\r\n");
        }
        bw.close();
        //delete original file and rename the tempo file
        inputFile.delete();
        if (tempFile.renameTo(new File("src/Register.txt"))) {
        }
    }
}
