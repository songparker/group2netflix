package UserFunctions;

import AdminFunctions.SearchingMovie;
import Entities.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * ClassName: CheckFavList
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: Yateng
 * @Create: 2023-01-30 - 10:10 a.m.
 * @Version: v1.0
 */
public class CheckFavList {
    static Scanner scan = new Scanner(System.in);
    public static void checkFavList() throws Exception {

        Set<Movie> allMovie = SearchingMovie.MovieReadin();
        String userName = LogIn.accountFound().getUserName();
        BufferedReader br = new BufferedReader(new FileReader("src/favList.txt"));
        List<String> recordsList = new ArrayList<>();
        String record;
        while ((record = br.readLine()) != null){
            recordsList.add(record);
        }

        List<String> userRecordsList = new ArrayList<>();
        for(String str: recordsList){
            String[] arr = str.split("\t");
            if(userName.equals(arr[0])){
                //Build up Logged user's own record list
                userRecordsList.add(str);
                for (int j = 1; j < arr.length; j++) {
                    System.out.print(arr[j] + "\t");
                }
                System.out.println();
            }
        }
        boolean flag = true;
        while(flag){
            System.out.println("\nYou can play anyone of them by enter the movie name or enter N back to main menu: ");
            String str = scan.nextLine();
            if(str.equalsIgnoreCase("n")){
                Menus.UserMenu();
                flag = false;
            }
            Movie movieInList = null;
            for(String favRecord: userRecordsList) {
                String[] arr = favRecord.split("\t");
//                System.out.println(arr[1].contains(str));
                if(arr[1].contains(str)){
                    for(Movie m: allMovie){
                        if(str.equalsIgnoreCase(m.getM_Title())){
                            movieInList = m;
                            break;
                        }
                    }
                    System.out.println("Movie in List is : "+ movieInList);
                }
            }
            if(movieInList != null){
                boolean flag1 = true;
                while(flag1){
                    if(str.equalsIgnoreCase("n")){
                        Menus.UserMenu();
                        flag1 = false;
                    }else{
                        System.out.println("Start playing " + movieInList.getM_Title() + "\nYou can user \"space\" + \"Enter\" to pause and resume anytime" +
                                "\nOr \"S\" + \"Enter\" to stop playing");
                        MoviePlaying.play(movieInList);
                        flag1 = false;
                    }
                }
            }else{
                System.out.println("Movie " + str + " is NOT found in your favorite list, check the movie title entered and try again please!");
            }

        }




//        String userName = LogIn.accountFound().getUserName();
//        BufferedReader br = new BufferedReader(new FileReader("src/favList.txt"));
//        Set<Movie> allMovie = SearchingMovie.MovieReadin();
//        String record;
//        int i = 1;
//        String[] arr = null;
//        while ((record = br.readLine()) != null){
//            arr = record.split("\t");
//            if(userName.equalsIgnoreCase(arr[0])){
//                System.out.println("Favorite List records #" + i + " is : \n" + arr[1] + ",\t" + arr[2] + ",\t" + arr[3] + ",\t" + arr[4]);
//                i++;
//            }
//        }
//        //////////////////
//        boolean flag = true;
//        while(flag){
//            System.out.println("You can play anyone of them by enter the movie name or enter N back to main menu: ");
//            String str = scan.nextLine();
//            Movie movieInList = null;
//            for(Movie m: allMovie){
//                if(str.equals(m.getM_Title())){
//                    movieInList = m;
//                }
//            }
//            if(movieInList != null){
//                boolean flag1 = true;
//                while(flag1){
//                    if(str.equalsIgnoreCase("n")){
//                        Menus.UserMenu();
//                        flag1 = false;
//                    }else{
//                        MoviePlaying.play(movieInList);
//                    }
//                }
//                flag = false;
//            }else{
//                System.out.println("Movie " + str + " is NOT found in your favorite list, try again please!");
//            }
//        }
    }
}
