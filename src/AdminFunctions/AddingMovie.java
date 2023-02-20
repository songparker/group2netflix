package AdminFunctions;

import Entities.Movie;
import UserFunctions.Menus;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AddingMovie {
    private static final String movieTitleRegex = "^[\\p{L}\\p{N}\\p{P}\\s&&[^\\p{So}]]{2,}[!~@#\\$%\\^&\\*\\(\\)_\\+\\{\\}\\|\":<>\\?\\[\\]\\\\';,./]*$";
    private static final String yearRegex = "^(19|20)\\d{2}$";
    private static final String languageRegex = "^[a-zA-Z]{2,}$";


    static Scanner scan = new Scanner(System.in);

    public static void AddingMovie() throws IOException {
        Adding();

        boolean flag = true;
        while (flag) {
            System.out.println("Do you want to add another one?\n" + "1 - Adding another movie\n" + "2 - Back to Main menu");
            String num = scan.nextLine();
            if (num.equals("1")) {
                AddingMovie();
                return;
            } else if (num.equals("2")) {
                Menus.AdminMenu();
                flag = false;
            } else {
                System.out.println("Invalid Input! Please try again!");

            }
        }
    }

    private static void Adding() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/Movie.txt", true));
        Movie newMovie = new Movie();
        Set<Movie> movieList = new HashSet<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Enter movie ID please");
            try {
                newMovie.setM_Id(Integer.valueOf(scan.nextLine().trim()));
            } catch (Exception e) {
                System.out.println("Input error, you can only enter digits for Movie ID and NOT Nullable!\nStarting over...");
                Adding();
                return;
            }

            if (movieIsExisted(newMovie)) {
                System.out.println("Movie ID already exist! Please try again!");
                AddingMovie();
            }
            flag = false;

        }
        System.out.println("Enter the movie Title please");
        String movieTitle = scan.nextLine().trim();
        while (!movieTitle.matches(movieTitleRegex)) {
            System.out.println("Invalid movie title, please try again(Minimum 2 characters)!");
            System.out.println("Enter the movie Title please: ");
            movieTitle = scan.nextLine().trim();
        }
        newMovie.setM_Title(movieTitle);
        while (movieIsExisted(newMovie)) {
            System.out.println("Movie Title already exists! Enter a different title or press N to return to the main menu: ");
            String input = scan.nextLine().trim();
            if (input.equalsIgnoreCase("N")) {
                Menus.AdminMenu(); // return to main menu
                return; // exit the method
            }
            newMovie.setM_Title(input);
        }

        System.out.println("Enter the movie director or directors please");
        String director = scan.nextLine().trim();
        while (!director.matches(movieTitleRegex)) {
            System.out.println("Invalid movie Director, please try again(Minimum 2 characters)!");
            System.out.println("Enter the movie Director please: ");
            director = scan.nextLine().trim();
        }
        newMovie.setM_Director(director);

        System.out.println("Enter the movie actors please");
        String actors = scan.nextLine().trim();
        while (!actors.matches(movieTitleRegex)) {
            System.out.println("Invalid movie Actor information, please try again(Minimum 2 characters)!");
            System.out.println("Enter the movie Actor please: ");
            actors = scan.nextLine().trim();
        }
        newMovie.setM_Actors(actors);

        System.out.println("Enter the movie Year of produced please");
        String year = scan.nextLine();
        while (!year.matches(yearRegex)) {
            System.out.println("Invalid movie Year of produced information, please try again(Digits only YYYY, Range: 1900 - 2099)!");
            System.out.println("Enter the movie Year of produced please: ");
            year = scan.nextLine();
        }
        newMovie.setM_Year(year);

        System.out.println("Enter the movie Rating please");
        boolean flag1 = true;
        while (flag1) {
            try {
                double rating = Double.parseDouble(scan.nextLine().trim());
                if (rating >= 1 && rating <= 10) {
                    newMovie.setM_Rating(rating);
                    flag1 = false;
                } else {
                    System.out.println("Invalid rating. Please enter a number between 1 and 10.");
                    System.out.println("Please enter the movie Rating again:");
                }
            } catch (Exception e) {
                System.out.println("Input error, you can only enter digits for Rating information!");
                System.out.println("Please enter the movie Rating again:");
            }
        }

        System.out.println("Enter the movie Genre please");
        String genre = scan.nextLine().trim();
        while (!genre.matches(movieTitleRegex)) {
            System.out.println("Invalid movie Genre information, please try again(Minimum 2 characters)!");
            System.out.println("Enter the movie Genre please: ");
            genre = scan.nextLine().trim();
        }
        newMovie.setM_Genre(genre);

        System.out.println("Enter the movie Country produced please");
        String country = scan.nextLine().trim();
        while (!country.matches(movieTitleRegex)) {
            System.out.println("Invalid movie Country information, please try again(Minimum 2 characters)!");
            System.out.println("Enter the movie Country please: ");
            country = scan.nextLine().trim();
        }
        newMovie.setM_Country(country);


        System.out.println("Enter the movie Language please");
        String userInput = scan.nextLine().trim();
        while (!userInput.matches(languageRegex)) {
            System.out.println("Invalid language, letters only! (No digits and special characters):");
            userInput = scan.nextLine().trim();
        }
        if (userInput.equalsIgnoreCase("english")) {
            userInput = "EN";
        } else if (userInput.equalsIgnoreCase("japanese")) {
            userInput = "JA";
        }else if (userInput.equalsIgnoreCase("french") || userInput.equalsIgnoreCase("francais")) {
            userInput = "FR";
        }else if (userInput.equalsIgnoreCase("german") || userInput.equalsIgnoreCase("germany")) {
            userInput = "DE";
        }else if (userInput.equalsIgnoreCase("Spanish")) {
            userInput = "ES";
        }else if (userInput.equalsIgnoreCase("Chinese")) {
            userInput = "ZH";
        }else if (userInput.equalsIgnoreCase("Italy") || userInput.equalsIgnoreCase("italian")) {
            userInput = "IT";
        }
        newMovie.setM_Language(userInput);
        String[] arrMovie = {String.valueOf(newMovie.getM_Id()), newMovie.getM_Title(), newMovie.getM_Director(),
                newMovie.getM_Actors(), newMovie.getM_Year(), String.valueOf(newMovie.getM_Rating()),
                newMovie.getM_Genre(), newMovie.getM_Country(), newMovie.getM_Language()};
        for (int i = 0; i < arrMovie.length; i++) {

            writer.append(arrMovie[i] + "\t");

            if (i == (arrMovie.length - 1)) {
                writer.write("\r\n");

            }
        }

        writer.close();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Movie.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split("\t");
                Movie newMovieLists = new Movie();

                newMovieLists.setM_Id(Integer.parseInt(arr[0]));
                newMovieLists.setM_Title(arr[1]);
                newMovieLists.setM_Director(arr[2]);
                newMovieLists.setM_Actors(arr[3]);
                newMovieLists.setM_Year(arr[4]);
                newMovieLists.setM_Rating(Double.parseDouble(arr[5]));
                newMovieLists.setM_Genre(arr[6]);
                newMovieLists.setM_Country(arr[7]);
                newMovieLists.setM_Language(arr[8]);

                movieList.add(newMovieLists);
            }

            System.out.println("New Movie was Added Successfully into the System...\n\nMovie Lists:\n");

            for (Movie movies : movieList)
                System.out.println(movies);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Check the movie entered is existed or not in case of avoid duplicate
    public static boolean movieIsExisted(Movie newMovie) {
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
                if (movie.getM_Id() == newMovie.getM_Id() || movie.getM_Title().equalsIgnoreCase(newMovie.getM_Title())) {
                    return true;
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}