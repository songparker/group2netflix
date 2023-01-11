package register;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ClassName: Account
 * Package: register
 * Description:
 *
 * @Author: Yateng
 * @Create: 2022-12-16 - 11:38 p.m.
 * @Version: v1.0
 */
public class Account {
    private String userName;
    private String passWord;
    private String fName;
    private String lName;
    private String dateOfBirth;
    private String gender;
//Default constructor
    public Account() {
    }
// Parameterized constructor
    public Account(String userName, String passWord, String fName, String lName, String dateOfBirth, String gender) {
        this.userName = userName;
        this.passWord = passWord;
        this.fName = fName;
        this.lName = lName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
//    Setter and getters


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
// toString method
    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }

   /* public static void Register() {
        Account myAccount = new Account();
        Scanner enter = new Scanner(System.in);
        System.out.println(("Welcome to our registration system\nEnter your User name please: "));
        myAccount.setUserName(enter.nextLine());

        System.out.println("Enter your pass word please: ");
        myAccount.setPassWord(enter.nextLine());

        System.out.println(("Enter your first name please: "));
        myAccount.setfName(enter.nextLine());

        System.out.println("Enter your last name please: ");
        myAccount.setlName(enter.nextLine());

        System.out.println(("Enter your date of birth please: "));
        myAccount.setDateOfBirth(enter.nextLine());

        System.out.println("Enter your gender please: ");
        myAccount.setGender(enter.nextLine());

        String[] arrAccount = {myAccount.getUserName(), myAccount.getPassWord(), myAccount.getfName(),
                myAccount.getlName(), myAccount.getDateOfBirth(), myAccount.getGender()};

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Register.txt", true));
            for (int i = 0; i < arrAccount.length; i++) {
                writer.append(arrAccount[i] + "\t");
                if (i == (arrAccount.length - 1)) {
                    writer.write("\r\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Account> accountMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Register.txt"));
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

        System.out.println("Welcome to our NetPlix system\nEnter your user name please: ");
        String userName = enter.nextLine();
        System.out.println("Now enter your pass word please: ");
        String pwd = enter.nextLine();
        Account foundAccount = accountMap.get(userName);
        if (foundAccount != null) {
            if (foundAccount.getPassWord().equals(pwd)) {
                System.out.println("Log in success! Your account information is shown below:");
                System.out.println(foundAccount); //Here is using account info instead of the user account page
            } else {
                System.out.println("Check you pass word and try again later please!");
                System.exit(0);
            }
        } else {
            System.out.println("Check you user name and try again later please!");
            System.exit(0);
        }
    }

    public static void Login() {
        Scanner enter = new Scanner(System.in);
        Map<String, Account> accountMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Register.txt"));
            String line;
            while((line = reader.readLine()) != null){
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

        System.out.println("Enter your user name please: ");
        String userName = enter.nextLine();
        System.out.println("Now enter your pass word please: ");
        String pwd = enter.nextLine();
        Account foundAccount = accountMap.get(userName);
        if(foundAccount != null){
            if(foundAccount.getPassWord().equals(pwd)){
                System.out.println("Log in success! Your account information is shown below:");
                System.out.println(foundAccount); //Here is using account info instead of the user account page
            }else{
                System.out.println("Check you pass word and try again later please!");
                System.exit(0);
            }
        }else{
            System.out.println("Check you user name and try again later please!");
            System.exit(0);
        }



    } */
}
