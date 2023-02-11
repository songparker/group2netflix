package AdminFunctions;

import Entities.Movie;
import UserFunctions.Menus;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import java.util.Set;

public class EditMovie {
	public static void editMovie() throws FileNotFoundException {
		Movie editMovie = new Movie();
		Scanner scan = new Scanner(System.in);
		Integer movieToEdit = 0;
		while (true) {

			boolean flag = true;
			while (flag) {
				try {
					System.out.println("Enter movie ID to edit ");
					String temp = scan.nextLine();
					movieToEdit = Integer.valueOf(temp);
				} catch (NumberFormatException nex) {
					System.out.println("input error, you can enter only digits for Movie ID!\nStarting over...");				
					editMovie();
					flag = false;
				}
				flag = false;
			}
			Set<Movie> allMovie = SearchingMovie.MovieReadin();
			for(Movie m: allMovie){
				if(m.getM_Id() == movieToEdit){
					editMovie = m;
					break;
				}
			}
			if (!movieIsExisted(movieToEdit)) {
				System.out.println("Sorry the movie ID does not exist, try again please");
			} else {

				System.out.println("Movie found is:\n" + editMovie);
				System.out.println("Please choose one edit option:");
				System.out.println("1-Edit Title\n" + "2-Edit Director\n" + "3-Edit Actor\n" + "4-Edit Year\n"
						+ "5-Edit Rating\n" + "6-Edit Genre\n" + "7-Edit Country\n" + "8-Edit Language\n"
						+ "9-Exit editing\n");
				String field = "";
				String option = scan.nextLine();
				switch (option) {
				case "1":
					System.out.println("Enter the title of the movie please");
					field = scan.nextLine();
					editTitle(movieToEdit, field);
					afterEdit();
					break;
				case "2":
					System.out.println("Enter the director of the movie please");
					field = scan.nextLine();
					editDirector(movieToEdit, field);
					afterEdit();
					break;
				case "3":
					System.out.println("Enter the actor of the movie please");
					field = scan.nextLine();
					editActor(movieToEdit, field);
					afterEdit();
					break;
				case "4":
					System.out.println("Enter the year of the movie please");
					field = scan.nextLine();
					editYear(movieToEdit, field);
					afterEdit();
					break;
				case "5":
					Double newRating = 0.0;
					boolean flag1 = true;
					while (flag1) {
						try {
							System.out.println("Enter the rating of the movie please");
							newRating = Double.parseDouble(scan.nextLine());
							flag1 = false;

						} catch (Exception e) {
							System.out.println("Input error, you can only enter digits for Rating information!");
						}
						if((newRating < 1) || (newRating > 10)){
							System.out.println("Invalid input, Enter digit form 1 - 10 only please!");
							flag1 = true;
						}
					}
						editRating(movieToEdit, newRating);
						afterEdit();
					break;
				case "6":
					System.out.println("Enter the genre of the movie please");
					field = scan.nextLine();
					editGenre(movieToEdit, field);
					afterEdit();
					break;
				case "7":
					System.out.println("Enter the country of the movie please");
					field = scan.nextLine();
					editCountry(movieToEdit, field);
					afterEdit();
					break;
				case "8":
					System.out.println("Enter the language of the movie please");
					field = scan.nextLine();
					editLanguage(movieToEdit, field);
					afterEdit();
					break;
				case "9":
					try {
						Menus.AdminMenu();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("Input Error, Starting over the Edit function...");
					break;
				}
			}
		}
	}

	// Check the movie entered exists or not
	private static boolean movieIsExisted(Integer movieToDelete) {

		boolean flag = false;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/Movie.txt"));
			String line;

			while ((line = reader.readLine()) != null && !flag) {
				String[] arr = line.split("\t");
				if (movieToDelete == (Integer.parseInt(arr[0]))) {
					flag = true;
				}
			}
			reader.close();
			return flag;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static void afterEdit() throws FileNotFoundException {
		while (true) {
			System.out.println("Would you like to continue editing?\n1-Continue editing\n2-Back to main menu\n");
			Scanner scan = new Scanner(System.in);
			String command = scan.nextLine();
			switch (command) {
			case "1":
				editMovie();
				break;
			case "2":
				Menus.AdminMenu();
				break;
			default:
				System.out.println("Error,Please enter again.");
			}
		}
	}

	public static boolean editTitle(Integer movieToEdit, String title) {
		try {
			File inputFile = new File("src/Movie.txt");
			File tempFile = new File("src/myTempFile.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writerTemp = new BufferedWriter(new FileWriter(tempFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split("\t");
				Integer movieId = Integer.parseInt(arr[0]);
				if (movieToEdit.equals(movieId)) {
					System.out.println("Movie Title is edited successfully");
					Movie editMovie = new Movie();
					editMovie.setM_Id(Integer.parseInt(arr[0]));
					editMovie.setM_Title(title);
					editMovie.setM_Director(arr[2]);
					editMovie.setM_Actors(arr[3]);
					editMovie.setM_Year(arr[4]);
					editMovie.setM_Rating(Double.parseDouble(arr[5]));
					editMovie.setM_Genre(arr[6]);
					editMovie.setM_Country(arr[7]);
					editMovie.setM_Language(arr[8]);

					writerTemp.write(editMovie.toStringInFileFormat() + System.getProperty("line.separator"));
				} else {
					writerTemp.write(line + System.getProperty("line.separator"));
				}
			}
			writerTemp.close();
			reader.close();

			moveFileToOriginalFile();
			// exitToMain();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean editDirector(Integer movieToEdit, String director) {
		try {
			File inputFile = new File("src/Movie.txt");
			File tempFile = new File("src/myTempFile.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writerTemp = new BufferedWriter(new FileWriter(tempFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split("\t");
				Integer movieId = Integer.parseInt(arr[0]);
				if (movieToEdit.equals(movieId)) {
					System.out.println("Movie Director information is edited successfully");
					Movie editMovie = new Movie();
					editMovie.setM_Id(Integer.parseInt(arr[0]));
					editMovie.setM_Title(arr[1]);
					editMovie.setM_Director(director);
					editMovie.setM_Actors(arr[3]);
					editMovie.setM_Year(arr[4]);
					editMovie.setM_Rating(Double.parseDouble(arr[5]));
					editMovie.setM_Genre(arr[6]);
					editMovie.setM_Country(arr[7]);
					editMovie.setM_Language(arr[8]);

					writerTemp.write(editMovie.toStringInFileFormat() + System.getProperty("line.separator"));
				} else {
					writerTemp.write(line + System.getProperty("line.separator"));
				}
			}
			writerTemp.close();
			reader.close();

			moveFileToOriginalFile();
			// exitToMain();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean editActor(Integer movieToEdit, String actor) {
		try {
			File inputFile = new File("src/Movie.txt");
			File tempFile = new File("src/myTempFile.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writerTemp = new BufferedWriter(new FileWriter(tempFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split("\t");
				Integer movieId = Integer.parseInt(arr[0]);
				if (movieToEdit.equals(movieId)) {
					System.out.println("Movie Actor information is edited successfully");
					Movie editMovie = new Movie();
					editMovie.setM_Id(Integer.parseInt(arr[0]));
					editMovie.setM_Title(arr[1]);
					editMovie.setM_Director(arr[2]);
					editMovie.setM_Actors(actor);
					editMovie.setM_Year(arr[4]);
					editMovie.setM_Rating(Double.parseDouble(arr[5]));
					editMovie.setM_Genre(arr[6]);
					editMovie.setM_Country(arr[7]);
					editMovie.setM_Language(arr[8]);

					writerTemp.write(editMovie.toStringInFileFormat() + System.getProperty("line.separator"));
				} else {
					writerTemp.write(line + System.getProperty("line.separator"));
				}
			}
			writerTemp.close();
			reader.close();

			moveFileToOriginalFile();
			// exitToMain();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean editYear(Integer movieToEdit, String year) {
		try {
			File inputFile = new File("src/Movie.txt");
			File tempFile = new File("src/myTempFile.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writerTemp = new BufferedWriter(new FileWriter(tempFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split("\t");
				Integer movieId = Integer.parseInt(arr[0]);
				if (movieToEdit.equals(movieId)) {
					System.out.println("Movie released year information is edited successfully");
					Movie editMovie = new Movie();
					editMovie.setM_Id(Integer.parseInt(arr[0]));
					editMovie.setM_Title(arr[1]);
					editMovie.setM_Director(arr[2]);
					editMovie.setM_Actors(arr[3]);
					editMovie.setM_Year(year);
					editMovie.setM_Rating(Double.parseDouble(arr[5]));
					editMovie.setM_Genre(arr[6]);
					editMovie.setM_Country(arr[7]);
					editMovie.setM_Language(arr[8]);

					writerTemp.write(editMovie.toStringInFileFormat() + System.getProperty("line.separator"));
				} else {
					writerTemp.write(line + System.getProperty("line.separator"));
				}
			}
			writerTemp.close();
			reader.close();

			moveFileToOriginalFile();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean editRating(Integer movieToEdit, double rating) {
		try {
			File inputFile = new File("src/Movie.txt");
			File tempFile = new File("src/myTempFile.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writerTemp = new BufferedWriter(new FileWriter(tempFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split("\t");
				Integer movieId = Integer.parseInt(arr[0]);
				if (movieToEdit.equals(movieId)) {
					System.out.println("Movie Rating is edited successfully");
					Movie editMovie = new Movie();
					editMovie.setM_Id(Integer.parseInt(arr[0]));
					editMovie.setM_Title(arr[1]);
					editMovie.setM_Director(arr[2]);
					editMovie.setM_Actors(arr[3]);
					editMovie.setM_Year(arr[4]);
					editMovie.setM_Rating(rating);
					editMovie.setM_Genre(arr[6]);
					editMovie.setM_Country(arr[7]);
					editMovie.setM_Language(arr[8]);

					writerTemp.write(editMovie.toStringInFileFormat() + System.getProperty("line.separator"));
				} else {
					writerTemp.write(line + System.getProperty("line.separator"));
				}
			}
			writerTemp.close();
			reader.close();

			moveFileToOriginalFile();
			// exitToMain();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean editGenre(Integer movieToEdit, String genre) {
		try {
			File inputFile = new File("src/Movie.txt");
			File tempFile = new File("src/myTempFile.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writerTemp = new BufferedWriter(new FileWriter(tempFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split("\t");
				Integer movieId = Integer.parseInt(arr[0]);
				if (movieToEdit.equals(movieId)) {
					System.out.println("Movie Genre is edited successfully");
					Movie editMovie = new Movie();
					editMovie.setM_Id(Integer.parseInt(arr[0]));
					editMovie.setM_Title(arr[1]);
					editMovie.setM_Director(arr[2]);
					editMovie.setM_Actors(arr[3]);
					editMovie.setM_Year(arr[4]);
					editMovie.setM_Rating(Double.parseDouble(arr[5]));
					editMovie.setM_Genre(genre);
					editMovie.setM_Country(arr[7]);
					editMovie.setM_Language(arr[8]);

					writerTemp.write(editMovie.toStringInFileFormat() + System.getProperty("line.separator"));
				} else {
					writerTemp.write(line + System.getProperty("line.separator"));
				}
			}
			writerTemp.close();
			reader.close();

			moveFileToOriginalFile();
			// exitToMain();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean editCountry(Integer movieToEdit, String country) {
		try {
			File inputFile = new File("src/Movie.txt");
			File tempFile = new File("src/myTempFile.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writerTemp = new BufferedWriter(new FileWriter(tempFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split("\t");
				Integer movieId = Integer.parseInt(arr[0]);
				if (movieToEdit.equals(movieId)) {
					System.out.println("Movie Produced Country information is edited successfully");
					Movie editMovie = new Movie();
					editMovie.setM_Id(Integer.parseInt(arr[0]));
					editMovie.setM_Title(arr[1]);
					editMovie.setM_Director(arr[2]);
					editMovie.setM_Actors(arr[3]);
					editMovie.setM_Year(arr[4]);
					editMovie.setM_Rating(Double.parseDouble(arr[5]));
					editMovie.setM_Genre(arr[6]);
					editMovie.setM_Country(country);
					editMovie.setM_Language(arr[8]);

					writerTemp.write(editMovie.toStringInFileFormat() + System.getProperty("line.separator"));
				} else {
					writerTemp.write(line + System.getProperty("line.separator"));
				}
			}
			writerTemp.close();
			reader.close();

			moveFileToOriginalFile();
			// exitToMain();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean editLanguage(Integer movieToEdit, String language) {
		try {
			File inputFile = new File("src/Movie.txt");
			File tempFile = new File("src/myTempFile.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writerTemp = new BufferedWriter(new FileWriter(tempFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split("\t");
				Integer movieId = Integer.parseInt(arr[0]);
				if (movieToEdit.equals(movieId)) {
					System.out.println("Movie Language information is edited successfully");
					Movie editMovie = new Movie();
					editMovie.setM_Id(Integer.parseInt(arr[0]));
					editMovie.setM_Title(arr[1]);
					editMovie.setM_Director(arr[2]);
					editMovie.setM_Actors(arr[3]);
					editMovie.setM_Year(arr[4]);
					editMovie.setM_Rating(Double.parseDouble(arr[5]));
					editMovie.setM_Genre(arr[6]);
					editMovie.setM_Country(arr[7]);
					editMovie.setM_Language(language);

					writerTemp.write(editMovie.toStringInFileFormat() + System.getProperty("line.separator"));
				} else {
					writerTemp.write(line + System.getProperty("line.separator"));
				}
			}
			writerTemp.close();
			reader.close();

			moveFileToOriginalFile();
			// exitToMain();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void moveFileToOriginalFile() {
		Path sourcePath = Paths.get("src/myTempFile.txt");
		Path destinationPath = Paths.get("src/Movie.txt");

		try {
			Files.delete(destinationPath);
			Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

		} catch (FileAlreadyExistsException e) {
			// destination file already exists
		} catch (IOException e) {
			// something else went wrong
			e.printStackTrace();
		}
	}

}
