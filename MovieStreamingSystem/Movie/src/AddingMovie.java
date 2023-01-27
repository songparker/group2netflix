
import java.io.*;
import java.util.*;

public class AddingMovie {

    static Scanner scan = new Scanner(System.in);

    public static void AddingMovie() throws FileNotFoundException {
        Adding();
        System.out.println("DO you want to add another one?\n" +
                "1 - Adding another movie\n" +
                "2 - Back to Main menu");
        boolean flag = true;
        while (flag) {
            Integer num = Integer.parseInt(scan.nextLine());
            if (num == 1) {
                AddingMovie();
                return;
            } else if (num == 2) {
                MainUser.AdminMainMenu();
            } else {
            	System.out.println("Invalid Input!Please try again!");
            }
        }
    }

    private static void Adding() {
        Movie newMovie = new Movie();
        Set<Movie> movieList = new HashSet<>();
        System.out.println("Enter movie ID please");
        newMovie.setM_Id(Integer.valueOf(scan.nextLine()));
        System.out.println("Enter the movie name please");
        newMovie.setM_Title(scan.nextLine());
        System.out.println("Enter the movie director or directors please");
        newMovie.setM_Director(scan.nextLine());
        System.out.println("Enter the movie actors please");
        newMovie.setM_Actors(scan.nextLine());
        System.out.println("Enter the movie year of produced please");
        newMovie.setM_Year(scan.nextLine());
        System.out.println("Enter the movie Rating please");
        newMovie.setM_Rating(Double.valueOf(scan.nextLine()));
        System.out.println("Enter the movie Genre please");
        newMovie.setM_Genre(scan.nextLine());
        System.out.println("Enter the movie Country produced please");
        newMovie.setM_Country(scan.nextLine());
        System.out.println("Enter the movie Language please");
        newMovie.setM_Language(scan.nextLine());

        String[] arrMovie = {String.valueOf(newMovie.getM_Id()), newMovie.getM_Title(),
                newMovie.getM_Director(), newMovie.getM_Actors(), newMovie.getM_Year(),
                String.valueOf(newMovie.getM_Rating()), newMovie.getM_Genre(), newMovie.getM_Country(),
                newMovie.getM_Language()};

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("MovieStreamingSystem/Movie.txt", true));

            for (int i = 0; i < arrMovie.length; i++) {
                if (movieIsExisted(newMovie)) {
                    System.out.println("Movie already exist! Please try again!");
                    AddingMovie();
                    return;
                } else {
                    writer.append(arrMovie[i] + "\t");

                }
                if (i == (arrMovie.length - 1)) {
                    writer.write("\r\n");

                }
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("MovieStreamingSystem/Movie.txt"));
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

            for(Movie movies : movieList)
                System.out.println(movies);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // Check the movie entered is existed or not in case of avoid duplicate
    private static boolean movieIsExisted(Movie newMovie) {
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
                if (movie.getM_Id() == newMovie.getM_Id() || movie.getM_Title().equals(newMovie.getM_Title())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
