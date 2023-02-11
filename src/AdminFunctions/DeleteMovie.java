package AdminFunctions;

import Entities.Movie;
import UserFunctions.Menus;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

public class DeleteMovie {
	static Scanner scan = new Scanner(System.in);

	public static void deleteMovie() throws FileNotFoundException {
		delete();
		System.out.println(
				"Do you want to delete another one?\n" + "1 - Delete another movie\n" + "2 - Back to Main menu");
		boolean flag = true;
		while (flag) {
			String num = scan.nextLine();
			if (num.equals("1")) {
				deleteMovie();
				return;
			} else if (num.equals("2")) {
				Menus.AdminMenu();
			} else {
				System.out.println("Invalid Input!Please try again!");
			}
		}
	}

	private static void delete() {
		boolean flag1 = true;
		int movieToDelete = 0;
		while(flag1){
			System.out.println("Enter movie ID to delete ");
			try{
				movieToDelete = Integer.valueOf(scan.nextLine());
			}catch (Exception e){
				System.out.println("Input error, you can only enter digits for Rating information!\nStarting over...");
				delete();
				return;
			}
			flag1 = false;
		}
		deleteMovieByMovieId(movieToDelete);
	}

	// find the movie to be deleted
	public static boolean deleteMovieByMovieId(int movieToDelete) {

		try {
			File inputFile = new File("src/Movie.txt");
			File tempFile = new File("src/myTempFile.txt");
			BufferedWriter writerTemp = new BufferedWriter(new FileWriter(tempFile));

			Set<Movie> movieSet = SearchingMovie.MovieReadin();
			Movie movieFound = new Movie();
			for(Movie m: movieSet){
				if(m.getM_Id() == movieToDelete){
					movieFound = m;
					break;
				}
			}
			if(movieFound.getM_Id() == movieToDelete){
				System.out.println("Movie found is\n" + movieFound);
				System.out.println("Do you want to delete this movie? Y/N");
				String input = scan.nextLine();
				if (input.equalsIgnoreCase("y")) {
					System.out.println("Movie Deleted Successfully!\n");
					movieSet.remove(movieFound);
				} else if (input.equalsIgnoreCase("n")) {

				} else {
					System.out.println("Invalid Input! Starting over Delete...");
					deleteMovie();
				}
			} else {
				System.out.println("There's no such a movie with ID " + movieToDelete + "\nTry again please!");
				deleteMovie();
			}
			for(Movie movie: movieSet){
				writerTemp.write(movie.toStringInFileFormat() + "\r\n");
			}

			writerTemp.close();
			inputFile.delete();
			tempFile.renameTo(new File("src/Movie.txt"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
