
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AddingMovie {
	public static void AddingMovie() {

		Movie newMovie = new Movie();
		Scanner scan = new Scanner(System.in);
		File file = new File("MovieStreamingSystem/Movie.txt");

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

		String[] arrMovie = { String.valueOf(newMovie.getM_Id()), newMovie.getM_Title(), newMovie.getM_Director(),
				newMovie.getM_Actors(), newMovie.getM_Year(), String.valueOf(newMovie.getM_Rating()),
				newMovie.getM_Genre(), newMovie.getM_Country(), newMovie.getM_Language() };

		
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			for (int i = 0; i < arrMovie.length; i++) {
				if (arrMovie.equals(arrMovie)) {
					System.out.println("Movie already exist! Please try again!");
					System.exit(0);
				}else {
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

		Set<Movie> movieList = new HashSet<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split("\t");
				Movie newAccount = new Movie();

				newAccount.setM_Id(Integer.parseInt(arr[0]));
				newAccount.setM_Title(arr[1]);
				newAccount.setM_Director(arr[2]);
				newAccount.setM_Actors(arr[3]);
				newAccount.setM_Year(arr[4]);
				newAccount.setM_Rating(Double.parseDouble(arr[5]));
				newAccount.setM_Genre(arr[6]);
				newAccount.setM_Country(arr[7]);
				newAccount.setM_Language(arr[8]);

				movieList.add(newAccount);
			}

			System.out.println("New Movie was Successfully Added to the System...");

			System.out.println("Movie Lists:\n" + movieList);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}