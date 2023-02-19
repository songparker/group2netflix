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
 * ClassName: checkWatchedList
 * Package: UserFunctions
 * Description:
 *
 * @Author: Yateng
 * @Create: 2023-02-03 - 10:12 p.m.
 * @Version: v1.0
 */
public class checkWatchedList {
    static Scanner scan = new Scanner(System.in);
    public static void checkWatchedList() throws Exception {
        Set<Movie> allMovie = SearchingMovie.MovieReadin();
        String userName = LogIn.accountFound().getUserName();
        BufferedReader br = new BufferedReader(new FileReader("src/watchHistory.txt"));
        List<String> recordsList = new ArrayList<>();
        String record;
        while ((record = br.readLine()) != null){
            recordsList.add(record);
        }

        List<String> userRecordsList = new ArrayList<>();
        for(String str: recordsList){
            String[] arr = str.split("\t");
            if(userName.equals(arr[1])){
                //Build up Logged user's own record list
                userRecordsList.add(str);
                for (int j = 3; j < arr.length; j++) {
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
            for(String histories: userRecordsList) {
                String[] arr = histories.split("\t");
                if(str.equalsIgnoreCase(arr[3])){
                    System.out.println("Start playing movie: " + arr[3] + "\nYou can user \"space\" + \"Enter\" to pause and resume anytime" +
                                        "\nOr \"S\" + \"Enter\" to stop playing");
                    for(Movie m: allMovie){
                        if(str.equalsIgnoreCase(m.getM_Title())){
                            movieInList = m;
                        }
                    }
                    break;
                }
            }

            boolean flag1 = true;

            if(movieInList != null){
                while(flag1){
                    if(str.equalsIgnoreCase("n")){
                        Menus.UserMenu();
                        flag1 = false;
                    }else{
                        MoviePlaying.play(movieInList);
                        flag1 = false;
                    }
                }
            }else{
                System.out.println("Movie " + str + " is NOT found in your watched history list, check the movie title entered and try again please!");
            }

        }



    }
}
