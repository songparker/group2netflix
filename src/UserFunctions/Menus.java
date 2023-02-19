package UserFunctions;

import AdminFunctions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * ClassName: Menus Package: UserFunctions Description:
 *
 * @Author: Yateng
 * @Create: 2023-01-30 - 2:50 p.m.
 * @Version: v1.0
 */
public class Menus {
	public static void UserMenu() throws Exception {
		Scanner enter = new Scanner(System.in);
		System.out.println("Please choose the option you want to do from the shown list:");
		System.out.println("1- Edit your Account Information\n" + "2- Search Movie\n" + "3- Sort Movies By Rating\n"
				+ "4- Sort Movies By Released Year\n" + "5- Check your Favorite List\n"
				+ "6- Watch History\n" + "7- Exit the System");
		String option = enter.nextLine();
		switch (option) {
		case "1":
			EditUserInfo.editUserInfo();

			break;
		case "2":
			UsersSearchingMovie.SearchingMovie();
			break;
		case "3":
			SortByRating.sortByRating();
			break;
		case "4":
			SortByYear.sortByYear();
			break;
		case "5":
			CheckFavList.checkFavList();
			break;
		case "6":
			checkWatchedList.checkWatchedList();
			break;
		case "7":
			System.exit(0);
			break;
		default:
			System.out.println("Input error, please input again.\n");
			UserMenu();
		}

	}

	public static void AdminMenu() throws FileNotFoundException {
		Scanner enter = new Scanner(System.in);
		System.out.println("Please choose the option you want to do from the shown list:");
		System.out.println("1- Adding Movie\n" + "2- Delete Movie\n" + "3- Edit Movie\n" + "4- Search Movie\n"
				+ "5- Search User\n" + "6- Check watching history data\n" + "7- Exit the System");
		String option = enter.nextLine();
		switch (option) {
		case "1":
			try {
				AddingMovie.AddingMovie();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			break;
		case "2":
			DeleteMovie.deleteMovie();
			break;
		case "3":
			EditMovie.editMovie();
			break;
		case "4":
			SearchingMovie.SearchingMovie();
			break;
		case "5":
			SearchingUser.SearchingUser();
			break;
		case "6":
			WatchHistoryCheck.WatchHistoryCheck();
			break;
		case "7":
			System.exit(0);
			break;
		default:
			System.out.println("Input error, choose from the list shown below by entering only 1 - 7 please!\n");
			AdminMenu();
			break;

		}
	}
}
