import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class WatchHistoryCheck {


    public static void main(String[] args) throws FileNotFoundException{

        // TODO Auto-generated method stub
        Scanner inFile = new Scanner (new FileReader("movie_watch_history.txt"));

        Set<WatchHistory> divideInFileSet = new HashSet<WatchHistory>();

        String [] lastWatch = new String [10];


        while (inFile.hasNextLine()) {
            String line = inFile.nextLine();
            lastWatch = line.split("#", 10);

            WatchHistory historyObj = new WatchHistory();

            historyObj.setUser_id(lastWatch[0]);
            historyObj.setUser_name(lastWatch[1]);
            historyObj.setMovie_id(lastWatch[2]);
            historyObj.setMovie_name(lastWatch[3]);
            historyObj.setWatch_date(lastWatch[4]);
            historyObj.setStart_time(lastWatch[5]);
            historyObj.setEnd_time(lastWatch[6]);
            historyObj.setWatch_time(lastWatch[7]);
            historyObj.setFinish_or_not(Integer.parseInt(lastWatch[8]));
            historyObj.setFeedback(lastWatch[9]);

            divideInFileSet.add(historyObj);
        }

        //Display Movie watching history in the HashSet sorted by User_id
        System.out.println("\n\nPrint all watch history sorted by user_id");
        divideInFileSet.stream().sorted(Comparator.comparing(WatchHistory :: getUser_id)).forEach(System.out :: println);

        //Display specific watching history by date
        System.out.println("\n\nPrint the watch history in a specific date");
        divideInFileSet.stream().filter(s->s.getWatch_date().equals("2023-01-05")).forEach(System.out :: println);

        //Display specific user's watching history
        System.out.println("\n\nPrint the watch history of a specific user");
        divideInFileSet.stream().filter(s->s.getUser_id().equals("0007")).forEach(System.out :: println);

        //Display the film that get better feedbacks

        //Ranking the users by their watching time



    }

}
