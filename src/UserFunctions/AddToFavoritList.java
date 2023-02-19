package UserFunctions;

import Entities.Account;
import Entities.Movie;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AddToFavoritList {

    public static void addToFav(Movie movie) throws Exception {
        BufferedWriter bw;

        try {
            bw = new BufferedWriter(new FileWriter("src/favList.txt", true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Account accountFound = LogIn.accountFound();
        String userName = accountFound.getUserName();

        StringBuilder sb = new StringBuilder();
        sb.append(userName + "\t" + "Movie: " + movie.getM_Title() + "\t" +
                "Release Year: " + movie.getM_Year() + "\t" + "Rating: " + movie.getM_Rating() +
                "\t" + "Genre: " + movie.getM_Genre() + "\r\n");
        String s = sb.toString();
        if(!checkItemExisted(s)){
            try {
                bw.write(s);
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static boolean checkItemExisted(String str) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/favList.txt"));
        List<String> recordList = new ArrayList<>();
        String record;
        while ((record = br.readLine()) != null){
            recordList.add(record);
        }
        boolean anyMatch = false;
        for(String favRecord: recordList){
            String[] arr = favRecord.split("\t");
            String[] arrExist = str.split("\t");
            if((arr[0].equals(arrExist[0]) && (arr[1].equals(arrExist[1])))){
                anyMatch = true;
                break;
            }
        }
        return anyMatch;
    }
}
