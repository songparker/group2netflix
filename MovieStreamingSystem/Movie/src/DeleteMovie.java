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
    static Scanner scan = new Scanner(System.in);

    public static void deleteMovie() throws FileNotFoundException {
        delete();
        System.out.println("DO you want to delete another one?\n" +
                "1 - Delete another movie\n" +
                "2 - Back to Main menu");
        boolean flag = true;
        while (flag) {
            Integer num = Integer.parseInt(scan.nextLine());
            if(num == 1){
                deleteMovie();
                return;
            } else if (num == 2) {
                MainUser.AdminMainMenu();
            } else {
            	System.out.println("Invalid Input!Please try again!");
            }
        }
    }
    private static  void  delete(){
        File file = new File("MovieStreamingSystem/Movie.txt");
        Movie newMovie = new Movie();
        Set<Movie> movieList = new HashSet<>();
        System.out.println("Enter movie ID to delete ");
        Integer movieToDelete = Integer.valueOf(scan.nextLine());
        deleteMovieByMovieId(movieToDelete);

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
//                System.out.println("Movie id " + movieId);
                if (movieToDelete.equals(movieId)) {

                    System.out.println("Deleting this movie\n" + line);
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
