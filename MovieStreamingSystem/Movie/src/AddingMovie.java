package MoviesData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * ClassName: moviesInput
 * Package: MoviesData
 * Description:
 *
 * @Author: Yateng
 * @Create: 2023-01-11 - 10:20 a.m.
 * @Version: v1.0
 */
public class AddingMovie {
    public static void main(String[] args){
        Movie newMovie = new Movie();
        Scanner scan = new Scanner(System.in);
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

        String[] arrMovie = {String.valueOf(newMovie.getM_Id()),newMovie.getM_Title(), newMovie.getM_Director(),
                            newMovie.getM_Actors(), newMovie.getM_Year(), String.valueOf(newMovie.getM_Rating()),
                            newMovie.getM_Genre(), newMovie.getM_Country(), newMovie.getM_Language()};

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Register.txt", true));
            for (int i = 0; i < arrMovie.length; i++) {
                writer.append(arrMovie[i] + "\t");
                if (i == (arrMovie.length - 1)) {
                    writer.write("\r\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
