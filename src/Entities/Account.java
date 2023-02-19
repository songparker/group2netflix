package Entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(userName, account.userName) && Objects.equals(passWord, account.passWord) && Objects.equals(fName, account.fName) && Objects.equals(lName, account.lName) && Objects.equals(dateOfBirth, account.dateOfBirth) && Objects.equals(gender, account.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, passWord, fName, lName, dateOfBirth, gender);
    }

    public String toStringInFileFormat() {
        return userName +
                "\t" + passWord +
                "\t" + fName +
                "\t" + lName +
                "\t" + dateOfBirth +
                "\t" + gender;
    }
}
