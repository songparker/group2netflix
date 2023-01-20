import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DeleteMovie {

    public static void deleteMovie() {

        File file = new File("MovieStreamingSystem/Movie.txt");
        Movie newMovie = new Movie();
        Scanner scan = new Scanner(System.in);
        Set<Movie> movieList = new HashSet<>();

        while (true) {
            System.out.println("Please Enter your choice!\n2-Delete Movie\nany Key-Exit");
            String choice = scan.nextLine();

            Integer movieToDelete;
            if (choice.equals("2")) {
                System.out.println("Enter movie ID to delete ");
                movieToDelete = Integer.valueOf(scan.nextLine());
                deleteMovieByMovieId(movieToDelete);
            } else {
                System.out.println("Account Logged out Successfully!");
                System.exit(0);
            }
        }
    }

    // find the movie to be deleted
    private static boolean deleteMovieByMovieId(Integer movieToDelete) {

        try {
            File inputFile = new File("MovieStreamingSystem/Movie.txt");
            File tempFile = new File("MovieStreamingSystem/myTempFile.txt");
            BufferedReader reader = new BufferedReader(new FileReader("MovieStreamingSystem/Movie.txt"));
            BufferedWriter writerTemp = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split("\t");
                Integer movieId = Integer.parseInt(arr[0]);
                System.out.println("Movie id " + movieId);
                if (movieToDelete.equals(movieId)) {
                    System.out.println("Deleting this movie");
                    continue;
                }
                writerTemp.write(line + System.getProperty("line.separator"));
            }
            writerTemp.close();
            reader.close();
            inputFile.delete();
            boolean successful = tempFile.renameTo(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
