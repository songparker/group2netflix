package UserFunctions;

import Entities.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UsersSearchingMovie {
    static Scanner enter = new Scanner(System.in);

    // searching method
    public static void SearchingMovie() {

        while (true) {
            // prompt users to choose one searching option
            System.out.println("Please choose one searching option:");
            System.out.println("1-Search by Title\n" + "2-Search by Director\n" + "3-Search by Actor\n"
                    + "4-Search by year\n" + "5-Search by Rating\n" + "6-Search by Genre\n" + "7-Search by Country\n"
                    + "8-Search by Language\n" + "9-Exit Search\n");
            String option = enter.nextLine();
            switch (option) {
                case "1":
                    SearchByTitle();
                    break;
                case "2":
                    SearchByDirector();
                    break;
                case "3":
                    SearchByActor();
                    break;
                case "4":
                    SearchByYear();
                    break;
                case "5":
                    SearchByRating();
                    break;
                case "6":
                    SearchByGenre();
                    break;
                case "7":
                    SearchByCountry();
                    break;
                case "8":
                    SearchByLanguage();
                    break;
                case "9":
                    try {
                        Menus.UserMenu();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Input error, please input again.");
                    break;
            }

        }
    }

    // method to read in movie database
    public static Set MovieReadin() {
        Set<Movie> movieList = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Movie.txt"));
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
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public static void SearchByTitle() {
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter movie title:");
            String userInput = enter.nextLine();
            for (Movie movie : allMovie) {
                if (movie.getM_Title().equalsIgnoreCase(userInput)) {
                    boolean b = searchResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchResult.add(movie);
                    }
                }
            }
            if (searchResult.size() != 0) {
                flag = handleSearchResult(searchResult);
            } else {
                System.out.println("The Movie doesn't exist");
                System.out.println("Do you want to continue searching by movie title? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")){
                    flag = true;
                }else{
                    System.out.println("Input error, going back to searching menu...");
                    break;
                }
            }
        }
    }

    public static void SearchByDirector() {
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter director:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Director().equalsIgnoreCase(userInput)) {
                    boolean b = searchResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchResult.add(movie);
                    }
                }
            }
            if (searchResult.size() != 0) {
                flag = handleSearchResult(searchResult);
            } else {
                System.out.println("The director doesn't exist");
                System.out.println("Do you want to continue searching by director? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")){
                    flag = true;
                }else{
                    System.out.println("Input error, going back to searching menu...");
                    break;
                }
            }
        }

    }

    public static void SearchByActor() {
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter the actor:");
            String userInput = enter.nextLine();
            for (Movie movie : allMovie) {
                if (movie.getM_Actors().equalsIgnoreCase(userInput)) {
                    boolean b = searchResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchResult.add(movie);
                    }
                }
            }
            if (searchResult.size() != 0) {
                flag = handleSearchResult(searchResult);
            } else {
                System.out.println("The Actor doesn't exist");
                System.out.println("Do you want to continue searching by movie actor? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")){
                    flag = true;
                }else{
                    System.out.println("Input error, going back to searching menu...");
                    break;
                }
            }
        }
    }

    public static void SearchByRating() {// this part need discuss , the rating is 0-10 or others
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter the rating:");

            String rating = enter.nextLine();
            Double userInput = 0.0;
            try{
                userInput = Double.parseDouble(rating);
            }catch (Exception e){
                System.out.println("Enter error, please enter numbers only!");
//                System.out.println("Entered is: " + rating);
                SearchByRating();
                return;
            }
            for (Movie movie : allMovie) {
                if (movie.getM_Rating() == userInput) {
                    boolean b = searchResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchResult.add(movie);
                    }
                }
            }
            if (searchResult.size() != 0) {
                flag = handleSearchResult(searchResult);
            } else {
                System.out.println("We don't have any movie matching this Rating!");
                System.out.println("Do you want to continue searching by movie Rating? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")){
                    flag = true;
                }else{
                    System.out.println("Input error, going back to searching menu...");
                    break;
                }
            }
//            flag = false;
        }
    }

    public static void SearchByGenre() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter genre:");
            String userInput = enter.nextLine();
            for (Movie movie : allMovie) {
                if (movie.getM_Genre().equalsIgnoreCase(userInput)) {
                    boolean b = searchResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchResult.add(movie);
                    }
                }
            }
            if (searchResult.size() != 0) {
                flag = handleSearchResult(searchResult);
            } else {
                System.out.println("This kind of genre doesn't exist");
                System.out.println("Do you want to continue searching by genre? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")){
                    flag = true;
                }else{
                    System.out.println("Input error, going back to searching menu...");
                    break;
                }
            }
        }
    }

    public static void SearchByCountry() {
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter country:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Country().equalsIgnoreCase(userInput)) {
                    boolean b = searchResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchResult.add(movie);
                    }
                }
            }
            if (searchResult.size() != 0) {
                flag = handleSearchResult(searchResult);
            } else {
                System.out.println("This country doesn't exist");
                System.out.println("Do you want to continue searching by country? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")){
                    flag = true;
                }else{
                    System.out.println("Input error, going back to searching menu...");
                    break;
                }
            }
        }

    }

    public static void SearchByLanguage() {
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter language:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Language().equalsIgnoreCase(userInput)) {
                    boolean b = searchResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchResult.add(movie);
                    }
                }
            }
            if (searchResult.size() != 0) {
                flag = handleSearchResult(searchResult);
            } else {
                System.out.println("This language doesn't exist");
                System.out.println("Do you want to continue searching by language? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")){
                    flag = true;
                }else{
                    System.out.println("Input error, going back to searching menu...");
                    break;
                }
            }
        }
    }

    public static void SearchByYear() {
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter year:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Year().equalsIgnoreCase(userInput)) {
                    boolean b = searchResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchResult.add(movie);
                    }
                }
            }
            if (searchResult.size() != 0) {
                flag = handleSearchResult(searchResult);
            } else {
                System.out.println("We don't have movie in this year");
                System.out.println("Do you want to continue searching by year? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y")){
                    flag = true;
                }else{
                    System.out.println("Input error, going back to searching menu...");
                    break;
                }
            }

        }
    }

    // Handle search result for each time(users can play or add to favlist after each search)
    public static boolean handleSearchResult(List<Movie> searchResult) {
        System.out.println("The searching result for this time is:");
        for (int i = 0; i < searchResult.size(); i++) {
            System.out.println("The result number " + (i + 1) + " is: \n" + searchResult.get(i));
        }

        System.out.println("Next: \n1- Add one of these movies to favList\n2- Play one of these movies \n3- Back to Main Menu");

        boolean flag = true;
        while(flag){
            String command = enter.nextLine();
            if (command.equals("1")) {
                System.out.println("Enter the movie title you want add to your Favorite List please");
                String m_Title = enter.nextLine();
                Movie movieFound = new Movie();
                for(Movie m: searchResult){
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
                    flag = false;
                }else{
                    System.out.println("Movie not found! \nCheck the title you entered and try again please!");
                }

            } else if (command.equals("2")) {
                System.out.println("Enter the movie title that you want play please");
                String m_Title = enter.nextLine();
                Movie movieFound = new Movie();
                for(Movie m: searchResult){
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
                    break;
                    //need to upgrade
                }
            } else if (command.equals("3")) {
                try {
                    Menus.UserMenu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                flag = false;
            }else{
                System.out.println("Input error, please try again choose from 1 -3: ");
                //need to improve
            }
        }
        return false;
    }

}
