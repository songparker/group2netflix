import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EditMovie {
	static Scanner scan = new Scanner(System.in);
	public static void editMovie() throws FileNotFoundException {

		edit();
		System.out.println("DO you want to delete another one?\n" +
				"1 - Delete another movie\n" +
				"2 - Back to Main menu");
		boolean flag = true;
		while (flag) {
			Integer num = Integer.parseInt(scan.nextLine());
			if(num == 1){
				editMovie();
				return;
			}else{
				MainAdmin.AdminMainMenu();
				flag = false;
			}
		}


	}

	public  static  void edit(){
		File file = new File("MovieStreamingSystem/Movie.txt");
		Movie editMovie = new Movie();
		Set<Movie> movieList = new HashSet<>();

		while (true) {
			System.out.println("Please Enter your choice!\n3-Edit Movie\nany Key-Exit");
			String choice = scan.nextLine();

			Integer movieToDelete;
			if (choice.equals("3")) {
				System.out.println("Enter movie ID to edit ");
				movieToDelete = Integer.valueOf(scan.nextLine());

				editMovie.setM_Id(movieToDelete);
				System.out.println("Enter the movie name please");
				editMovie.setM_Title(scan.nextLine());
				System.out.println("Enter the movie director or directors please");
				editMovie.setM_Director(scan.nextLine());
				System.out.println("Enter the movie actors please");
				editMovie.setM_Actors(scan.nextLine());
				System.out.println("Enter the movie year of produced please");
				editMovie.setM_Year(scan.nextLine());
				System.out.println("Enter the movie Rating please");
				editMovie.setM_Rating(Double.valueOf(scan.nextLine()));
				System.out.println("Enter the movie Genre please");
				editMovie.setM_Genre(scan.nextLine());
				System.out.println("Enter the movie Country produced please");
				editMovie.setM_Country(scan.nextLine());
				System.out.println("Enter the movie Language please");
				editMovie.setM_Language(scan.nextLine());

				// call edit method
				editMovieByMovieId(movieToDelete, editMovie);
			} else {
				System.out.println("Account Logged out Successfully!");
				System.exit(0);
			}
		}

	}

	// find the movie to be deleted
	private static boolean editMovieByMovieId(Integer movieToEdit, Movie editMovie) {

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
				if (movieToEdit.equals(movieId)) {
					System.out.println("Editing this movie");
					writerTemp.write(editMovie.toStringInFileFormat()+ System.getProperty("line.separator"));
				} else {
					writerTemp.write(line + System.getProperty("line.separator"));
				}
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
