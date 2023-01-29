import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddToFavoriteList {

    public static void main(String[] args) throws IOException {


        //LogIn.Login();

        //SearchingMovie.SearchingMovie();
        Scanner enter = new Scanner(System.in);
        Map<String, Account> accountMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MovieStreamingSystem/Register.txt"));
            String line;
            while((line = reader.readLine()) != null){
                String[] arr = line.split("\t");
                Account newAccount = new Account();
                newAccount.setUserName(arr[0]);
                newAccount.setPassWord(arr[1]);
                newAccount.setfName(arr[2]);
                newAccount.setlName(arr[3]);
                newAccount.setDateOfBirth(arr[4]);
                newAccount.setGender(arr[5]);
                accountMap.put(newAccount.getUserName(), newAccount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to our NetFlix system\nEnter your user name please: ");
        String userName = enter.nextLine();
        System.out.println("Now enter your pass word please: ");
        String pwd = enter.nextLine();
        Account foundAccount = accountMap.get(userName);
        if(foundAccount != null){
            if(foundAccount.getPassWord().equals(pwd)){
                System.out.println("Log in success! Your account information is shown below:");
                System.out.println(foundAccount); //Here is using account info instead of the user account page
                while (true) {
                    System.out.println("Do you want to search movies, Y/N?");
                    String searchOrNot = enter.nextLine();
                    if (searchOrNot.equalsIgnoreCase("Y"))
                    {
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

                    } else if (searchOrNot.equalsIgnoreCase("N")) {
                        System.exit(0);
                    } else {
                        System.out.println("Please only input Y or N, Y stands for Yes and N stands for No.");
                        return;
                    }
                }
            }else{
                System.out.println("Check you pass word and try again later please!");
                System.exit(0);
            }
        }else{
            System.out.println("Check you user name and try again later please!");
            System.exit(0);
        }


    }

    public static Set MovieReadin() {
        Set<Movie> movieList = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("movie.txt"));
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
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter movie title:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Title().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("The Movie doesn't exist");
                    System.out.println("Do you want to continue searching by movie title? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                System.out.println("Do you want to add it to your favorite list? Y/N");
                String addOrNot = enter.nextLine();
                if (addOrNot.equalsIgnoreCase("y"))
                {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                    FavoriteList myFavorite = new FavoriteList();
                    myFavorite.setUser_name(Account.getUserName());
                    myFavorite.setMovie_id(String.valueOf(searchResult.getM_Id()));
                    myFavorite.setMovie_name(searchResult.getM_Title());
                    myFavorite.setAdd_date(dateFormat.format(date));
                    //System.out.println("This movie added to your favorite list successfully.");
                    //System.out.println(myFavorite);
                    //String[] newFavorite = {myFavorite.getUser_name(), myFavorite.getMovie_id(), myFavorite.getMovie_name(),
                            //myFavorite.getAdd_date()};

                    if (favoExisted(myFavorite.getUser_name(), myFavorite.getMovie_name())==false) {
                        System.out.println("This movie added to your favorite list successfully.");
                        System.out.println(myFavorite);
                        String[] newFavorite = {myFavorite.getUser_name(), myFavorite.getMovie_id(), myFavorite.getMovie_name(),
                                myFavorite.getAdd_date()};
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("FavoriteList.txt", true));
                            for (int i = 0; i < newFavorite.length; i++) {
                                writer.append(newFavorite[i] + "\t");
                                if (i == (newFavorite.length - 1)) {
                                    writer.write("\r\n");
                                }
                            }
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                            Map<String, FavoriteList> favoriteMap = new HashMap<>();
                            try {
                                BufferedReader reader = new BufferedReader(new FileReader("FavoriteList.txt"));
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    String[] arr = line.split("\t");
                                    FavoriteList newFavoList = new FavoriteList();
                                    newFavoList.setUser_name(arr[0]);
                                    newFavoList.setMovie_id(arr[1]);
                                    newFavoList.setMovie_name(arr[2]);
                                    newFavoList.setAdd_date(arr[3]);
                                    favoriteMap.put(newFavoList.getUser_name(), newFavoList);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                else {
                    System.out.println("This movie is already in your favorite list.");
                }
                    break;
                }
                return;
            }
        }


    public static void SearchByDirector() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter director:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Director().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("The director doesn't exist");
                    System.out.println("Do you want to continue searching by director? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                System.out.println("Do you want to add it to your favorite list? Y/N");
                String addOrNot = enter.nextLine();
                if (addOrNot.equalsIgnoreCase("y"))
                {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                    Account thisUser = new Account();
                    Class UserInfo = thisUser.getClass();
                    FavoriteList myFavorite = new FavoriteList();
                    myFavorite.setUser_name(Account.getUserName());
                    myFavorite.setMovie_id(String.valueOf(searchResult.getM_Id()));
                    myFavorite.setMovie_name(searchResult.getM_Title());
                    myFavorite.setAdd_date(dateFormat.format(date));
                    System.out.println("This movie added to your favorite list successfully.");
                    System.out.println(myFavorite);
                    String[] newFavorite = {myFavorite.getUser_name(), myFavorite.getMovie_id(), myFavorite.getMovie_name(),
                            myFavorite.getAdd_date()};

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("FavoriteList.txt", true));
                        for (int i = 0; i < newFavorite.length; i++) {
                            writer.append(newFavorite[i] + "\t");
                            if (i == (newFavorite.length - 1)) {
                                writer.write("\r\n");
                            }
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, FavoriteList> favoriteMap = new HashMap<>();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("FavoriteList.txt"));
                        String line;
                        while((line = reader.readLine()) != null){
                            String[] arr = line.split("\t");
                            FavoriteList newFavoList = new FavoriteList();
                            newFavoList.setUser_name(arr[0]);
                            newFavoList.setMovie_id(arr[1]);
                            newFavoList.setMovie_name(arr[2]);
                            newFavoList.setAdd_date(arr[3]);
                            favoriteMap.put(newFavoList.getUser_name(), newFavoList);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                return;
            }
        }
    }

    public static void SearchByActor() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter actor:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Actors().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("The actor doesn't exist");
                    System.out.println("Do you want to continue searching by actor? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                System.out.println("Do you want to add it to your favorite list? Y/N");
                String addOrNot = enter.nextLine();
                if (addOrNot.equalsIgnoreCase("y"))
                {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                    Account thisUser = new Account();
                    Class UserInfo = thisUser.getClass();
                    FavoriteList myFavorite = new FavoriteList();
                    myFavorite.setUser_name(Account.getUserName());
                    myFavorite.setMovie_id(String.valueOf(searchResult.getM_Id()));
                    myFavorite.setMovie_name(searchResult.getM_Title());
                    myFavorite.setAdd_date(dateFormat.format(date));
                    System.out.println("This movie added to your favorite list successfully.");
                    System.out.println(myFavorite);
                    String[] newFavorite = {myFavorite.getUser_name(), myFavorite.getMovie_id(), myFavorite.getMovie_name(),
                            myFavorite.getAdd_date()};

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("FavoriteList.txt", true));
                        for (int i = 0; i < newFavorite.length; i++) {
                            writer.append(newFavorite[i] + "\t");
                            if (i == (newFavorite.length - 1)) {
                                writer.write("\r\n");
                            }
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, FavoriteList> favoriteMap = new HashMap<>();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("FavoriteList.txt"));
                        String line;
                        while((line = reader.readLine()) != null){
                            String[] arr = line.split("\t");
                            FavoriteList newFavoList = new FavoriteList();
                            newFavoList.setUser_name(arr[0]);
                            newFavoList.setMovie_id(arr[1]);
                            newFavoList.setMovie_name(arr[2]);
                            newFavoList.setAdd_date(arr[3]);
                            favoriteMap.put(newFavoList.getUser_name(), newFavoList);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                return;
            }
        }
    }

    public static void SearchByRating() {//this part need discuss , the rating is 0-10 or others
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter rating:");
            double userInput = enter.nextDouble();

            for (Movie movie : allMovie) {
                if (movie.getM_Rating() == userInput)
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("The rating doesn't exist");
                    System.out.println("Do you want to continue searching by rating? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                System.out.println("Do you want to add it to your favorite list? Y/N");
                String addOrNot = enter.nextLine();
                if (addOrNot.equalsIgnoreCase("y"))
                {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                    Account thisUser = new Account();
                    Class UserInfo = thisUser.getClass();
                    FavoriteList myFavorite = new FavoriteList();
                    myFavorite.setUser_name(Account.getUserName());
                    myFavorite.setMovie_id(String.valueOf(searchResult.getM_Id()));
                    myFavorite.setMovie_name(searchResult.getM_Title());
                    myFavorite.setAdd_date(dateFormat.format(date));
                    System.out.println("This movie added to your favorite list successfully.");
                    System.out.println(myFavorite);
                    String[] newFavorite = {myFavorite.getUser_name(), myFavorite.getMovie_id(), myFavorite.getMovie_name(),
                            myFavorite.getAdd_date()};

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("FavoriteList.txt", true));
                        for (int i = 0; i < newFavorite.length; i++) {
                            writer.append(newFavorite[i] + "\t");
                            if (i == (newFavorite.length - 1)) {
                                writer.write("\r\n");
                            }
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, FavoriteList> favoriteMap = new HashMap<>();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("FavoriteList.txt"));
                        String line;
                        while((line = reader.readLine()) != null){
                            String[] arr = line.split("\t");
                            FavoriteList newFavoList = new FavoriteList();
                            newFavoList.setUser_name(arr[0]);
                            newFavoList.setMovie_id(arr[1]);
                            newFavoList.setMovie_name(arr[2]);
                            newFavoList.setAdd_date(arr[3]);
                            favoriteMap.put(newFavoList.getUser_name(), newFavoList);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                return;
            }
        }
    }

    public static void SearchByGenre() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter genre:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Genre().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("This kind or genre doesn't exist");
                    System.out.println("Do you want to continue searching by genre? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                System.out.println("Do you want to add it to your favorite list? Y/N");
                String addOrNot = enter.nextLine();
                if (addOrNot.equalsIgnoreCase("y"))
                {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                    Account thisUser = new Account();
                    Class UserInfo = thisUser.getClass();
                    FavoriteList myFavorite = new FavoriteList();
                    myFavorite.setUser_name(Account.getUserName());
                    myFavorite.setMovie_id(String.valueOf(searchResult.getM_Id()));
                    myFavorite.setMovie_name(searchResult.getM_Title());
                    myFavorite.setAdd_date(dateFormat.format(date));
                    System.out.println("This movie added to your favorite list successfully.");
                    System.out.println(myFavorite);
                    String[] newFavorite = {myFavorite.getUser_name(), myFavorite.getMovie_id(), myFavorite.getMovie_name(),
                            myFavorite.getAdd_date()};

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("FavoriteList.txt", true));
                        for (int i = 0; i < newFavorite.length; i++) {
                            writer.append(newFavorite[i] + "\t");
                            if (i == (newFavorite.length - 1)) {
                                writer.write("\r\n");
                            }
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, FavoriteList> favoriteMap = new HashMap<>();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("FavoriteList.txt"));
                        String line;
                        while((line = reader.readLine()) != null){
                            String[] arr = line.split("\t");
                            FavoriteList newFavoList = new FavoriteList();
                            newFavoList.setUser_name(arr[0]);
                            newFavoList.setMovie_id(arr[1]);
                            newFavoList.setMovie_name(arr[2]);
                            newFavoList.setAdd_date(arr[3]);
                            favoriteMap.put(newFavoList.getUser_name(), newFavoList);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                return;
            }
        }
    }

    public static void SearchByCountry() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter country:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Country().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("This country doesn't exist");
                    System.out.println("Do you want to continue searching by country? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                System.out.println("Do you want to add it to your favorite list? Y/N");
                String addOrNot = enter.nextLine();
                if (addOrNot.equalsIgnoreCase("y"))
                {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                    Account thisUser = new Account();
                    Class UserInfo = thisUser.getClass();
                    FavoriteList myFavorite = new FavoriteList();
                    myFavorite.setUser_name(Account.getUserName());
                    myFavorite.setMovie_id(String.valueOf(searchResult.getM_Id()));
                    myFavorite.setMovie_name(searchResult.getM_Title());
                    myFavorite.setAdd_date(dateFormat.format(date));
                    System.out.println("This movie added to your favorite list successfully.");
                    System.out.println(myFavorite);
                    String[] newFavorite = {myFavorite.getUser_name(), myFavorite.getMovie_id(), myFavorite.getMovie_name(),
                            myFavorite.getAdd_date()};

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("FavoriteList.txt", true));
                        for (int i = 0; i < newFavorite.length; i++) {
                            writer.append(newFavorite[i] + "\t");
                            if (i == (newFavorite.length - 1)) {
                                writer.write("\r\n");
                            }
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, FavoriteList> favoriteMap = new HashMap<>();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("FavoriteList.txt"));
                        String line;
                        while((line = reader.readLine()) != null){
                            String[] arr = line.split("\t");
                            FavoriteList newFavoList = new FavoriteList();
                            newFavoList.setUser_name(arr[0]);
                            newFavoList.setMovie_id(arr[1]);
                            newFavoList.setMovie_name(arr[2]);
                            newFavoList.setAdd_date(arr[3]);
                            favoriteMap.put(newFavoList.getUser_name(), newFavoList);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                return;
            }
        }
    }

    public static void SearchByLanguage() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter language:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Language().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("This language doesn't exist");
                    System.out.println("Do you want to continue searching by language? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                System.out.println("Do you want to add it to your favorite list? Y/N");
                String addOrNot = enter.nextLine();
                if (addOrNot.equalsIgnoreCase("y"))
                {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                    Account thisUser = new Account();
                    Class UserInfo = thisUser.getClass();
                    FavoriteList myFavorite = new FavoriteList();
                    myFavorite.setUser_name(Account.getUserName());
                    myFavorite.setMovie_id(String.valueOf(searchResult.getM_Id()));
                    myFavorite.setMovie_name(searchResult.getM_Title());
                    myFavorite.setAdd_date(dateFormat.format(date));
                    System.out.println("This movie added to your favorite list successfully.");
                    System.out.println(myFavorite);
                    String[] newFavorite = {myFavorite.getUser_name(), myFavorite.getMovie_id(), myFavorite.getMovie_name(),
                            myFavorite.getAdd_date()};

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("FavoriteList.txt", true));
                        for (int i = 0; i < newFavorite.length; i++) {
                            writer.append(newFavorite[i] + "\t");
                            if (i == (newFavorite.length - 1)) {
                                writer.write("\r\n");
                            }
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, FavoriteList> favoriteMap = new HashMap<>();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("FavoriteList.txt"));
                        String line;
                        while((line = reader.readLine()) != null){
                            String[] arr = line.split("\t");
                            FavoriteList newFavoList = new FavoriteList();
                            newFavoList.setUser_name(arr[0]);
                            newFavoList.setMovie_id(arr[1]);
                            newFavoList.setMovie_name(arr[2]);
                            newFavoList.setAdd_date(arr[3]);
                            favoriteMap.put(newFavoList.getUser_name(), newFavoList);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                return;
            }
        }
    }

    public static void SearchByYear() {
        Scanner enter = new Scanner(System.in);
        Set<Movie> allMovie = MovieReadin();
        List<Movie> searchByTitleResult = new ArrayList<>();
        while (true) {
            System.out.println("Please enter year:");
            String userInput = enter.nextLine();

            for (Movie movie : allMovie) {
                if (movie.getM_Year().contains(userInput))
                    searchByTitleResult.add(movie);
                else {
                    System.out.println("We don't have movie in this year");
                    System.out.println("Do you want to continue searching by year? Y/N");
                    String command = enter.nextLine();
                    if (command.equalsIgnoreCase("y"))
                        break;
                    else
                        return;
                }
            }

            for (Movie searchResult : searchByTitleResult) {
                System.out.println("The searching result is:");
                System.out.println(searchResult);
                System.out.println("Do you want to add it to your favorite list? Y/N");
                String addOrNot = enter.nextLine();
                if (addOrNot.equalsIgnoreCase("y"))
                {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                    Account thisUser = new Account();
                    Class UserInfo = thisUser.getClass();
                    FavoriteList myFavorite = new FavoriteList();
                    myFavorite.setUser_name(Account.getUserName());
                    myFavorite.setMovie_id(String.valueOf(searchResult.getM_Id()));
                    myFavorite.setMovie_name(searchResult.getM_Title());
                    myFavorite.setAdd_date(dateFormat.format(date));
                    System.out.println("This movie added to your favorite list successfully.");
                    System.out.println(myFavorite);
                    String[] newFavorite = {myFavorite.getUser_name(), myFavorite.getMovie_id(), myFavorite.getMovie_name(),
                            myFavorite.getAdd_date()};

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("FavoriteList.txt", true));
                        for (int i = 0; i < newFavorite.length; i++) {
                            writer.append(newFavorite[i] + "\t");
                            if (i == (newFavorite.length - 1)) {
                                writer.write("\r\n");
                            }
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, FavoriteList> favoriteMap = new HashMap<>();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("FavoriteList.txt"));
                        String line;
                        while((line = reader.readLine()) != null){
                            String[] arr = line.split("\t");
                            FavoriteList newFavoList = new FavoriteList();
                            newFavoList.setUser_name(arr[0]);
                            newFavoList.setMovie_id(arr[1]);
                            newFavoList.setMovie_name(arr[2]);
                            newFavoList.setAdd_date(arr[3]);
                            favoriteMap.put(newFavoList.getUser_name(), newFavoList);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                return;
            }
        }
    }

    //Check favorite existed or not before write down into the file
    public static boolean favoExisted(String user_name,String movie_name){

        Map<String, FavoriteList> FavoMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("FavoriteList.txt"));
            String line;
            while((line = reader.readLine()) != null){
                String[] arr = line.split("\t");
                if((arr[1].equals(user_name))&&(arr[3].equalsIgnoreCase(movie_name)))
                {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}

