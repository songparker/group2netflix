
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
            enter.nextLine();
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
            BufferedReader reader = new BufferedReader(new FileReader("MovieStreamingSystem/Movie.txt"));
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
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter movie title:");
            String userInput = enter.nextLine();
            for (Movie movie : allMovie) {
                if (movie.getM_Title().equalsIgnoreCase(userInput)) {
                    boolean b = searchByTitleResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchByTitleResult.add(movie);
                    }
                }
            }
            if (searchByTitleResult.size() != 0) {
                flag = handleSearchResult(searchByTitleResult, allMovie, "Title");
            } else {
                System.out.println("The Movie doesn't exist");
                System.out.println("Do you want to continue searching by movie title? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y"))
                    break;
                else
                    return;
            }
        }
    }



    public static void SearchByDirector() {
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter director:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Director().equalsIgnoreCase(userInput)) {
                    boolean b = searchByTitleResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchByTitleResult.add(movie);
                    }
                }
            }
            if (searchByTitleResult.size() != 0) {
                flag = handleSearchResult(searchByTitleResult, allMovie, "Director");
            } else {
                System.out.println("The director doesn't exist");
                System.out.println("Do you want to continue searching by director? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y"))
                    break;
                else
                    return;
            }
        }


    }


    public static void SearchByActor() {
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter the actor:");
            String userInput = enter.nextLine();
            for (Movie movie : allMovie) {
                if (movie.getM_Actors().equalsIgnoreCase(userInput)) {
                    boolean b = searchByTitleResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchByTitleResult.add(movie);
                    }
                }
            }
            if (searchByTitleResult.size() != 0) {
                flag = handleSearchResult(searchByTitleResult, allMovie, "Actor or Actors");
            } else {
                System.out.println("The Actor doesn't exist");
                System.out.println("Do you want to continue searching by movie actor? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y"))
                    break;
                else
                    return;
            }
        }
    }

    public static void SearchByRating() {//this part need discuss , the rating is 0-10 or others
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter the rating:");
            Integer userInput = Integer.parseInt(enter.nextLine());
            for (Movie movie : allMovie) {
                if (movie.getM_Rating() == userInput) {
                    boolean b = searchByTitleResult.stream().anyMatch(m -> m.getM_Title().contains(movie.getM_Title()));
                    if (!b) {
                        searchByTitleResult.add(movie);
                    }
                }
            }
            if (searchByTitleResult.size() != 0) {
                flag = handleSearchResult(searchByTitleResult, allMovie, "Rating");
            } else {
                System.out.println("The Actor doesn't exist");
                System.out.println("Do you want to continue searching by movie actor? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y"))
                    break;
                else
                    return;
            }
        }
    }

    public static void SearchByGenre() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter genre:");
            String userInput = enter.nextLine();
            for (Movie movie : allMovie) {
                if (movie.getM_Actors().equalsIgnoreCase(userInput)) {
                    boolean b = searchByTitleResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchByTitleResult.add(movie);
                    }
                }
            }
            if (searchByTitleResult.size() != 0) {
                flag = handleSearchResult(searchByTitleResult, allMovie, "Genre");
            } else {
                System.out.println("This kind or genre doesn't exist");
                System.out.println("Do you want to continue searching by genre? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y"))
                    break;
                else
                    return;
            }
        }
    }

    public static void SearchByCountry() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter country:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Actors().equalsIgnoreCase(userInput)) {
                    boolean b = searchByTitleResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchByTitleResult.add(movie);
                    }
                }
            }
            if (searchByTitleResult.size() != 0) {
                flag = handleSearchResult(searchByTitleResult, allMovie, "Country");
            } else {
                System.out.println("This country doesn't exist");
                System.out.println("Do you want to continue searching by country? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y"))
                    break;
                else
                    return;
            }
        }

    }

    public static void SearchByLanguage() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter language:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Actors().equalsIgnoreCase(userInput)) {
                    boolean b = searchByTitleResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchByTitleResult.add(movie);
                    }
                }
            }
            if (searchByTitleResult.size() != 0) {
                flag = handleSearchResult(searchByTitleResult, allMovie, "Language");
            } else {
                System.out.println("This language doesn't exist");
                System.out.println("Do you want to continue searching by language? Y/N");
                String command = enter.nextLine();
                if (command.equalsIgnoreCase("y"))
                    break;
                else
                    return;
            }
        }
    }

    public static void SearchByYear() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("Please enter year:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Actors().equalsIgnoreCase(userInput)) {
                    boolean b = searchByTitleResult.stream().anyMatch(m -> m.getM_Id() == movie.getM_Id());
                    if (!b) {
                        searchByTitleResult.add(movie);
                    }
                }
            }
            if (searchByTitleResult.size() != 0) {
                flag = handleSearchResult(searchByTitleResult, allMovie, "Year");
            } else {
                    System.out.println("We don't have movie in this year");
                    System.out.println("Do you want to continue searching by year? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }

        }
    }
   // Handle search result for each time(Admin can edit directly after each search)
    public static boolean handleSearchResult(List<Movie> searchByTitleResult, Set<Movie> allMovie, String str) {
        System.out.println("The searching result for this time is:");
        for (int i = 0; i < searchByTitleResult.size(); i++) {
            System.out.println("The result number " + (i + 1) + " is: \n" + searchByTitleResult.get(i));
        }

        System.out.println("What will be the next?\n" +
                "1 - Edit one of the result movies\n" +
                "2 - Continue searching by movie " + str + "\n" +
                "3 - Back to searching menu\n");

        String command = enter.nextLine();
        if (command.equals("1")) {
            System.out.println("Enter the number of movie that you want to edit please");
            Integer num = Integer.parseInt(enter.nextLine());
            Movie m = searchByTitleResult.get(num - 1);
            allMovie.remove(m);
            editResult(m);
            allMovie.add(m);
            try {
                File inputFile = new File("MovieStreamingSystem/Movie.txt");
                File tempFile = new File("MovieStreamingSystem/myTempFile.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
                for (Movie e : allMovie) {
                    String line = e.toStringInFileFormat();
                    bw.write(line);
                    bw.write("\r\n");
                }
                bw.close();
                inputFile.delete();
                if (tempFile.renameTo(inputFile)) {
                    System.out.println("Movie edit successful!");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;

        } else if (command.equals("2")) {
            return true;
        } else if (command.equals("3")) {
            return false;
        }
        return true;
    }


    //Admin can edit movie after each searching
    public static void editResult(Movie m) {
        boolean flag = true;
        while (flag) {
            System.out.println("Please choose which part you want edit of movie " + m.getM_Title());
            System.out.println("1- Movie Id\n" +
                    "2 - Movie Title\n" +
                    "3 - Movie Director\n" +
                    "4 - Movie Actor\n" +
                    "5 - Movie year\n" +
                    "6 - Movie Rating\n" +
                    "7 - Movie Genre\n" +
                    "8 - Movie Country\n" +
                    "9 - Movie Language\n" +
                    "10 - Exit Editing\n");

            int part = enter.nextInt();
            enter.nextLine();

            switch (part) {
                case 1:
                    System.out.println("Please enter the new Id for movie " + m.getM_Title());
                    Integer newId = Integer.parseInt(enter.nextLine());
                    m.setM_Id(newId);
                    break;
                case 2:
                    System.out.println("Please enter the new Title for movie " + m.getM_Title());
                    String newTitle = enter.nextLine();
                    m.setM_Title(newTitle);
                    break;
                case 3:
                    System.out.println("Please enter the new Director information for movie " + m.getM_Title());
                    String newDirector = enter.nextLine();
                    m.setM_Director(newDirector);
                    break;
                case 4:
                    System.out.println("Please enter the new Actor information for movie " + m.getM_Title());
                    String newActor = enter.nextLine();
                    m.setM_Actors(newActor);
                    break;
                case 5:
                    System.out.println("Please enter the new produced year information for movie " + m.getM_Title());
                    String year = enter.nextLine();
                    m.setM_Year(year);
                    break;
                case 6:
                    System.out.println("Please enter the new Rating for movie " + m.getM_Title());
                    Integer newRating = Integer.parseInt(enter.nextLine());
                    m.setM_Rating(newRating);
                    break;
                case 7:
                    System.out.println("Please enter the new Genre information for movie " + m.getM_Title());
                    String genre = enter.nextLine();
                    m.setM_Genre(genre);
                    break;
                case 8:
                    System.out.println("Please enter the new produced Country information for movie " + m.getM_Title());
                    String country = enter.nextLine();
                    m.setM_Country(country);
                    break;
                case 9:
                    System.out.println("Please enter the new Language information for movie " + m.getM_Title());
                    String language = enter.nextLine();
                    m.setM_Language(language);
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Enter error, please try again!");
                    return;
            }

            System.out.println("Do you want to edit anything else? Y/N");
            String str = enter.nextLine().toUpperCase();
            if ("N".equalsIgnoreCase(str)) {
                flag = false;
            }
        }
    }


}