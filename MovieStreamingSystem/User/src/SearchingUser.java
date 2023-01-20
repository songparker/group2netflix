import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * ClassName: SearchingUser
 * Package: SearchingUser
 * Description: This method is used for searching users by username...
 *
 * @Author: Hua Zhang
 * @Create: 2023-01-13
 * @Version: v1.0
 */
public class SearchingUser {
    //A Scanner constant can be used anywhere in the class
    static Scanner enter = new Scanner(System.in);
    public static void SearchingUser() {
        while (true) {
            //prompt users to choose one searching option
            System.out.println("Please choose one searching option:");
            System.out.println("1-Search by Username\n" +
                    "2-Search by first name\n" +
                    "3-Search by last name\n" +
                    "4-Search by date of birth\n" +
                    "5-Exist Searching\n");
            int option = enter.nextInt();
            switch (option) {
                case 1:
                    SearchByUsername();
                    break;
                case 2:
                    SearchByFName();
                    break;
                case 3:
                    SearchByLName();
                    break;
                case 4:
                    SearchByDOB();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Input error, please input again.");
                    break;
            }

        }

    }

    private static void SearchByLName() {
        Scanner enter = new Scanner(System.in);
        Set<Account> alluser = UserReadin();
        List<Account> searchByLName = new ArrayList<>();
        while (true) {
            System.out.println("Please enter last name:");
            String userInput = enter.nextLine();
            for (Account user : alluser) {
                if (user.getlName().contains(userInput))
                    searchByLName.add(user);
                else {
                    System.out.println("This last name doesn't exist");
                    System.out.println("Do you want to continue searching by first name? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Account searchResult : searchByLName) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }
    }

    private static void SearchByDOB() {
        Scanner enter = new Scanner(System.in);
        Set<Account> alluser = UserReadin();
        List<Account> searchByDOB = new ArrayList<>();
        while (true) {
            System.out.println("Please enter date of birth:");
            String userInput = enter.nextLine();

            for (Account user : alluser) {
                if (user.getDateOfBirth().contains(userInput))
                    searchByDOB.add(user);
                else {
                    System.out.println("This date of birth doesn't exist");
                    System.out.println("Do you want to continue searching by first name? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Account searchResult : searchByDOB) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }
    }

    private static void SearchByFName() {
        Scanner enter = new Scanner(System.in);
        Set<Account> alluser = UserReadin();
        List<Account> searchByFName = new ArrayList<>();
        while (true) {
            System.out.println("Please enter first name:");
            String userInput = enter.nextLine();

            for (Account user : alluser) {
                if (user.getfName().contains(userInput))
                    searchByFName.add(user);
                else {
                    System.out.println("This first name doesn't exist");
                    System.out.println("Do you want to continue searching by first name? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Account searchResult : searchByFName) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }

    }

    private static void SearchByUsername() {
        Scanner enter = new Scanner(System.in);
        Set<Account> alluser = UserReadin();
        List<Account> searchByUsername = new ArrayList<>();
        while (true) {
            System.out.println("Please enter user name:");
            String userInput = enter.nextLine();

            for (Account user : alluser) {
                if (user.getUserName().contains(userInput))
                    searchByUsername.add(user);
                else {
                    System.out.println("This Username doesn't exist");
                    System.out.println("Do you want to continue searching by Username? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Account searchResult : searchByUsername) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }
    }

    //method to read in account database
    public static Set UserReadin() {
        Set<Account> userList = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Register.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split("\t");
                Account user = new Account();
                user.setUserName (arr[0]);
                user.setPassWord (arr[1]);
                user.setfName (arr[2]);
                user.setlName (arr[3]);
                user.setDateOfBirth (arr[4]);
                user.setGender (arr[5]);
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }



}
