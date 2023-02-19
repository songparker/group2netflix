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
        String command = enter.nextLine();
        boolean flag = true;
        while(flag){
            if (command.equals("1")) {
                System.out.println("Enter the movie title you want add to your Favorite List please");
                String m_Title = enter.nextLine();
                Movie movieFound = new Movie();
                for(Movie m: allMovie){
                    if(m.getM_Title().equalsIgnoreCase(m_Title)){
                        movieFound = m;
                        break;
                    }
                }
                if(movieFound.getM_Id() != 0){
                    System.out.println("Movie you chosen is: \n" + movieFound);
                    System.out.println("Added to favList!\n");
                    try {
                        AddToFavoritList.addToFav(movieFound);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Do you want to add another one?\n" + "1 - Adding another movie\n" + "2 - Back to Main menu");
                    boolean flag1 = true;
                    while (flag1) {
                        String number = enter.nextLine();
                        if (number.equals("1")) {
                            sortByRating( );
                            return;
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
                System.out.println("Enter the movie title that you want play please");
                String m_Title = enter.nextLine();
                Movie movieFound = new Movie();
                for(Movie m: allMovie){
                    if(m.getM_Title().equalsIgnoreCase(m_Title)){
                        movieFound = m;
                        break;
                    }
                }
                if(movieFound.getM_Id() != 0){
                    System.out.println("Start playing movie: " + movieFound.getM_Title() + "\nYou can user \"space\" + \"Enter\" to pause and resume anytime" +
                            "\nOr \"S\" + \"Enter\" to stop playing");
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
