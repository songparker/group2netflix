
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * ClassName: SearchingMovie
 * Package: SearchingMovie
 * Description: This method is used for searching movies by Name,director,actor,countries...
 *
 * @Author: Hua Zhang
 * @Create: 2023-01-13
 * @Version: v1.0
 */


public class SearchingMovie {
    //A Scanner constant can be used anywhere in the class
    static Scanner enter = new Scanner(System.in);

    //searching method
    public static void SearchingMovie() {

        while (true) {
            //prompt users to choose one searching option
            System.out.println("Please choose one searching option:");
            System.out.println("1-Search by Title\n" +
                    "2-Search by Director\n" +
                    "3-Search by Actor\n" +
                    "4-Search by year\n" +
                    "5-Search by Rating\n" +
                    "6-Search by Genre\n" +
                    "7-Search by Country\n" +
                    "8-Search by Language\n" +
                    "9-Exist Searching\n");
            int option = enter.nextInt();
            switch (option) {
                case 1:
                    SearchByTitle();
                    break;
                case 2:
                    SearchByDirector();
                    break;
                case 3:
                    SearchByActor();
                    break;
                case 4:
                    SearchByYear();
                    break;
                case 5:
                    SearchByRating();
                    break;
                case 6:
                    SearchByGenre();
                    break;
                case 7:
                    SearchByCountry();
                    break;
                case 8:
                    SearchByLanguage();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Input error, please input again.");
                    break;
            }

        }
    }

    //method to read in movie database
    public static Set MovieReadin() {
        Set<Movie> movieList = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("movie.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split("\t");
                Movie movie = new Movie();
                movie.setM_Id(Integer.parseInt(arr[0]));
                movie.setM_Title(arr[1]);
                movie.setM_Director(arr[2]);
                movie.setM_Actors(arr[3]);
                movie.setM_Year(arr[4]);
                movie.setM_Rating(Double.parseDouble(arr[5]));
                movie.setM_Genre(arr[6]);
                movie.setM_Country(arr[7]);
                movie.setM_Language(arr[8]);
                movieList.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public static void SearchByTitle() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter movie title:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Title().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("The Movie doesn't exist");
                    System.out.println("Do you want to continue searching by movie title? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }
    }

    public static void SearchByDirector() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter director:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Director().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("The director doesn't exist");
                    System.out.println("Do you want to continue searching by director? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }
    }

    public static void SearchByActor() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter actor:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Actors().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("The actor doesn't exist");
                    System.out.println("Do you want to continue searching by actor? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }
    }

    public static void SearchByRating() {//this part need discuss , the rating is 0-10 or others
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter rating:");
            double userInput = enter.nextDouble();

            for (Movie movie : allMovie) {
                if (movie.getM_Rating() == userInput)
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("The rating doesn't exist");
                    System.out.println("Do you want to continue searching by rating? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }
    }

    public static void SearchByGenre() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter genre:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Genre().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("This kind or genre doesn't exist");
                    System.out.println("Do you want to continue searching by genre? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }
    }

    public static void SearchByCountry() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter country:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Country().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("This country doesn't exist");
                    System.out.println("Do you want to continue searching by country? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }
    }

    public static void SearchByLanguage() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter language:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Language().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("This language doesn't exist");
                    System.out.println("Do you want to continue searching by language? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }

    }

    public static void SearchByYear() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter year:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Year().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("We don't have movie in this year");
                    System.out.println("Do you want to continue searching by year? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                return;
            }
        }
    }

}
