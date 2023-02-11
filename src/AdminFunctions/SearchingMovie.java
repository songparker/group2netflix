package AdminFunctions;

import Entities.Movie; 
import UserFunctions.Menus;

import java.io.*;
import java.util.*;


/**
 * ClassName: SearchingMovie Package: SearchingMovie Description: This method is
 * used for searching movies by Name,director,actor,countries...
 *
 * @Author: Hua Zhang
 * @Create: 2023-01-13
 * @Version: v1.0
 */

public class SearchingMovie {
	// A Scanner constant can be used anywhere in the class
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
					Menus.AdminMenu();
				} catch (FileNotFoundException e) {					
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Input error, please choose from 1 - 9 from the list shown below!");
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
				flag = handleSearchResult(searchResult,  "Title");
			} else {
				System.out.println("The Movie doesn't exist");
				System.out.println("Do you want to continue searching by movie title? Y/N");
				String command = enter.nextLine();
				if (command.equalsIgnoreCase("y")){
					flag = true;
				}else if (command.equalsIgnoreCase("n")){
					System.out.println("Going back to searching menu...");
					break;
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
				flag = handleSearchResult(searchResult, "Director");
			} else {
				System.out.println("The director doesn't exist");
				System.out.println("Do you want to continue searching by director? Y/N");
				String command = enter.nextLine();
				if (command.equalsIgnoreCase("y")){
					flag = true;
				}else if (command.equalsIgnoreCase("n")){
					System.out.println("Going back to searching menu...");
					break;
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
				flag = handleSearchResult(searchResult,  "Actor or Actors");
			} else {
				System.out.println("The Actor doesn't exist");
				System.out.println("Do you want to continue searching by movie actor? Y/N");
				String command = enter.nextLine();
				if (command.equalsIgnoreCase("y")){
					flag = true;
				}else if (command.equalsIgnoreCase("n")){
					System.out.println("Going back to searching menu...");
					break;
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
			//input mismatch exception need handled
//			double userInput = enter.nextDouble();
			String rating = enter.nextLine();
			Double userInput = 0.0;
			try{
				userInput = Double.parseDouble(rating);
			}catch (Exception e){
				System.out.println("Input error, you can enter only digits for Movie rating information, try again please!");
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
				flag = handleSearchResult(searchResult,"Rating");
			} else {
				System.out.println("The Rating doesn't exist");
				System.out.println("Do you want to continue searching by movie Rating? Y/N");
				String command = enter.nextLine();
				if (command.equalsIgnoreCase("y")){
					flag = true;
				}else if (command.equalsIgnoreCase("n")){
					System.out.println("Going back to searching menu...");
					break;
				}else{
					System.out.println("Input error, going back to searching menu...");
					break;
				}
			}
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
				flag = handleSearchResult(searchResult, "Genre");
			} else {
				System.out.println("This kind of genre doesn't exist");
				System.out.println("Do you want to continue searching by genre? Y/N");
				String command = enter.nextLine();
				if (command.equalsIgnoreCase("y")){
					flag = true;
				}else if (command.equalsIgnoreCase("n")){
					System.out.println("Going back to searching menu...");
					break;
				}else{
					System.out.println("Input error, going back to searching menu...");
					break;
				}
			}
		}
	}

	public static void SearchByCountry() {
		Scanner enter = new Scanner(System.in);
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
				flag = handleSearchResult(searchResult, "Country");
			} else {
				System.out.println("This country doesn't exist");
				System.out.println("Do you want to continue searching by country? Y/N");
				String command = enter.nextLine();
				if (command.equalsIgnoreCase("y")){
					flag = true;
				}else if (command.equalsIgnoreCase("n")){
					System.out.println("Going back to searching menu...");
					break;
				}else{
					System.out.println("Input error, going back to searching menu...");
					break;
				}
			}
		}

	}

	public static void SearchByLanguage() {
		Scanner enter = new Scanner(System.in);
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
				flag = handleSearchResult(searchResult,"Language");
			} else {
				System.out.println("This language doesn't exist");
				System.out.println("Do you want to continue searching by language? Y/N");
				String command = enter.nextLine();
				if (command.equalsIgnoreCase("y")){
					flag = true;
				}else if (command.equalsIgnoreCase("n")){
					System.out.println("Going back to searching menu...");
					break;
				}else{
					System.out.println("Input error, going back to searching menu...");
					break;
				}
			}
		}
	}

	public static void SearchByYear() {
		Scanner enter = new Scanner(System.in);
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
				flag = handleSearchResult(searchResult,"Year");
			} else {
				System.out.println("We don't have movie in this year");
				System.out.println("Do you want to continue searching by year? Y/N");
				String command = enter.nextLine();
				if (command.equalsIgnoreCase("y")){
					flag = true;
				}else if (command.equalsIgnoreCase("n")){
					System.out.println("Going back to searching menu...");
					break;
				}else{
					System.out.println("Input error, going back to searching menu...");
					break;
				}
			}

		}
	}

	// Handle search result for each time(Admin can edit directly after each search)
	public static boolean handleSearchResult(List<Movie> searchByTitleResult, String str) {
		System.out.println("The searching result for this time is:");
		for (int i = 0; i < searchByTitleResult.size(); i++) {
			System.out.println("The result number " + (i + 1) + " is: \n" + searchByTitleResult.get(i));
		}
		boolean flag = true;
		String command = "";
		int num = 0;
		while(flag) {
			System.out.println("What will be the next?\n" + "1 - Edit one of the result movies\n" + "2 - Delete one of the result movies\n"
					+ "3 - Continue searching by movie " + str + "\n" + "4 - Back to searching menu\n");
			command = enter.nextLine();
			if (command.equals("1")) {
				boolean flag1 = true;
				while (flag1) {
					try {
						System.out.println("Enter the number of movie that you want to edit:");
						num = Integer.valueOf(enter.nextLine());
						flag1 = false;
					} catch (Exception e) {
						System.out.println("Input error, Enter Digit only to choose!");
					}
					if((num < 1) || (num > searchByTitleResult.size())){
						System.out.println("Invalid input, Enter digit form 1 - " + searchByTitleResult.size() + " only please!");
						flag1 = true;
					}
				}
				Movie m = searchByTitleResult.get(num - 1);
				editResult(m);
				return true;
			}else{
				flag = false;
			}
		}


		 if (command.equals("2")) {
			 boolean flag1 = true;
			 while (flag1) {
				 try {
					 System.out.println("Enter the number of movie that you want to delete:");
					 num = Integer.parseInt(enter.nextLine());
					 flag1 = false;

				 } catch (Exception e) {
					 System.out.println("Input error, Enter Digit only to choose!");
				 }
				 if((num < 1) || (num > searchByTitleResult.size())){
					 System.out.println("Invalid input, Enter digit form 1 - " + searchByTitleResult.size() + " only please!");
					 flag1 = true;
				 }
			 }
			Movie m = searchByTitleResult.get(num - 1);
			Integer movieId=m.getM_Id();
			DeleteMovie.deleteMovieByMovieId(movieId);
			return false;
		}

		else if (command.equals("3")) {
			return true;
		} else if (command.equals("4")) {
			System.out.println("Going back to searching menu...");
			return false;
		}
		else{
			 System.out.println("Input error, going back to search by " + str + "...");
		}
		return true;

	}


	// Admin can edit movie after each searching
	public static void editResult(Movie m) {
		boolean flag = true;
		while (flag) {
			System.out.println("Please choose which part you want edit of movie " + m.getM_Title());
			System.out.println("1 - Movie Title\n" + "2 - Movie Director\n" + "3 - Movie Actor\n"
					+ "4 - Movie year\n" + "5 - Movie Rating\n" + "6 - Movie Genre\n" + "7 - Movie Country\n"
					+ "8 - Movie Language\n" + "9 - Exit Editing\n");
			Integer movieId=m.getM_Id();

			//int part = enter.nextInt();
			String part = enter.nextLine();

			switch (part) {
			case "1":
				System.out.println("Please enter the new Title for movie " + m.getM_Title());
				String newTitle = enter.nextLine();
				EditMovie.editTitle(movieId,newTitle);
				break;
			case "2":
				System.out.println("Please enter the new Director information for movie " + m.getM_Title());
				String newDirector = enter.nextLine();
				EditMovie.editDirector(movieId,newDirector);
				break;
			case "3":
				System.out.println("Please enter the new Actor information for movie " + m.getM_Title());
				String newActor = enter.nextLine();
				EditMovie.editActor(movieId,newActor);
				break;
			case "4":
				System.out.println("Please enter the new produced year information for movie " + m.getM_Title());
				String year = enter.nextLine();
				EditMovie.editYear(movieId,year);
				break;
			case "5":
				Double newRating = 0.0;
				boolean flag1 = true;
				while (flag1) {
					try {
						System.out.println("Please enter the new Rating for movie " + m.getM_Title());
						newRating = Double.parseDouble(enter.nextLine());
						flag1 = false;

					} catch (Exception e) {
						System.out.println("Input error, Enter Digit only to choose!");
					}
					if((newRating < 1) || (newRating > 10)){
						System.out.println("Invalid input, Enter digit form 1 - 10 only please!");
						flag1 = true;
					}
				}
				EditMovie.editRating(movieId,newRating);
				break;
			case "6":
				System.out.println("Please enter the new Genre information for movie " + m.getM_Title());
				String genre = enter.nextLine();
				EditMovie.editGenre(movieId,genre);
				break;
			case "7":
				System.out.println("Please enter the new produced Country information for movie " + m.getM_Title());
				String country = enter.nextLine();
				EditMovie.editCountry(movieId,country);
				break;
			case "8":
				System.out.println("Please enter the new Language information for movie " + m.getM_Title());
				String language = enter.nextLine();
				EditMovie.editLanguage(movieId,language);
				break;
			case "9":
				SearchingMovie();
				return;
			default:
				System.out.println("Input error, select from the list shown below by entering 1 - 9 only please!");
				editResult(m);
				return;
			}

			System.out.println("Do you want to edit anything else? Y/N");
			String str = enter.nextLine().toUpperCase();
			if (str.equalsIgnoreCase("y")) {
				flag=true;
			}
			else if (str.equalsIgnoreCase("n")){
				SearchingMovie();
				flag=false;
			}else{
				System.out.println("Input error, going back to searching menu...");
				SearchingMovie();
				flag=false;
			}
		}
	}
}