package UserFunctions;

import AdminFunctions.SearchingMovie;
import Entities.Movie;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClassName: SortByRating
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: Hua Zhang
 * @Create: 2023-01-30 - 10:15 a.m.
 * @Version: v1.0
 */
public class SortByRating {
    static Scanner enter=new Scanner(System.in);

    public static void sortByRating( ) throws Exception {

        Set<Movie> allMovie = SearchingMovie.MovieReadin();
        System.out.println("\nThe Result of sorting by rating:");
        List<Movie> movieList = allMovie.stream().sorted(Comparator.comparing(Movie::getM_Rating).reversed()).collect(Collectors.toList());
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println("Movie number " + (i + 1) + "\n" + movieList.get(i));
        }
        System.out.println("Next: \n1- Add one of these movies to favList\n2- Play one of these movies \n3- Back to Main Menu");
        String command = enter.nextLine().trim();
        boolean flag = true;
        while(flag){
            if (command.equals("1")) {
                System.out.println("Enter the number of the movie you want to add to your Favorite List:");
                Movie movieFound = new Movie();
                boolean flag2 = true;
                while (flag2) {
                    try {
                        int movieNumber = Integer.parseInt(enter.nextLine().trim());
                        if (movieNumber < 1 || movieNumber > movieList.size()) {
                            System.out.println("Invalid movie number! Please enter a number between 1 and " + movieList.size());
                        } else {
                            movieFound = movieList.get(movieNumber - 1);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input error! Please enter only digits to choose movie.");
                    }
                }
                if(movieFound.getM_Id() != 0){
                    System.out.println("Movie you chosen is: \n" + movieFound);
                    System.out.println("Added to your Favorite List!\n");
                    try {
                        AddToFavoritList.addToFav(movieFound);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Do you want to add another one?\n" + "1 - Adding another movie\n" + "2 - Back to Main menu");
                    boolean flag1 = true;
                    while (flag1) {
                        String number = enter.nextLine().trim();
                        if (number.equals("1")) {
                            sortByRating( );
                            flag1 = false;
                        } else if (number.equals("2")) {
                            try {
                                Menus.UserMenu();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            flag1 = false;
                        } else {
                            System.out.println("Invalid Input! Please try again\n1 - Adding another movie\n2 - Back to Main menu");
                        }
                    }
                    flag = false;
                }else{
                    System.out.println("Movie not found! \nCheck the title you entered and try again please!");
                }

            } else if (command.equals("2")) {
                System.out.println("Enter the number of movie that you want to play please:");
                Movie movieFound = new Movie();
                boolean flag2 = true;
                while (flag2) {
                    try {
                        int movieNumber = Integer.parseInt(enter.nextLine());
                        if (movieNumber < 1 || movieNumber > movieList.size()) {
                            System.out.println("Invalid movie number! Please enter a number between 1 and " + movieList.size());
                        } else {
                            movieFound = movieList.get(movieNumber - 1);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input error! Please enter only digits to choose movie.");
                    }
                }

                if(movieFound.getM_Id() != 0){
                    System.out.println("Start playing movie: " + movieFound.getM_Title() + "\nYou can user \"space\" then \"Enter\" to pause and resume anytime" +
                            "\nOr \"S\" then \"Enter\" to stop playing");
                    try {
                        MoviePlaying.play(movieFound);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    flag = false;
                }else{
                    System.out.println("Movie not found! \nCheck the title you entered and try again please!");
                }
            } else if (command.equals("3")) {
                try {
                    Menus.UserMenu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else{
                System.out.println("Input error, backing to main menu!");
                try {
                    Menus.UserMenu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
