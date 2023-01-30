import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;


public class WatchHistoryCheck {


    public static void watchHistoryMenu() {
        System.out.println("\n1. Check all watching history sorted by user_id"+
                "\n2. Check watching history by date"+
                "\n3. Check watching history by user ID"+
                "\n4. Check watching history by user name"+
                "\n5. Check watching history by movie ID"+
                "\n6. Check watching history by movie name"+
                "\n7. Check a simple statistic report"+
                "\n8. exit");
    }

    /*
    public static void print(String arg) {
        System.out.println(arg);
    }
    */


    public static void main(String[] args) throws FileNotFoundException{
        while(true) {

            System.out.println("\nWatching history Menu:");
            watchHistoryMenu();
            System.out.println();

            // TODO Auto-generated method stub
            Scanner inFile = new Scanner(new FileReader("movie_watch_history.txt"));

            Set<WatchHistory> divideInFileSet = new HashSet<WatchHistory>();

            String[] lastWatch = new String[10];


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

            Scanner input = new Scanner(System.in);

            System.out.println("Please enter the option number.");
            String option = input.nextLine();


            if (option.equals("1")) {
                //Display Movie watching history in the HashSet sorted by User_id
                System.out.println("\n\nPrint all watch history sorted by user_id");
                divideInFileSet.stream().sorted(Comparator.comparing(WatchHistory::getUser_id)).forEach(System.out::println);
            }

            if(option.equals("2")) {
                System.out.println("\n\nPlease enter the date you want to search.");
                String searchByDate = input.nextLine();
                //Display specific watching history by date
                System.out.println("\n\nPrint the watch history in "+searchByDate);
                divideInFileSet.stream().filter(s -> s.getWatch_date().equals(searchByDate)).forEach(System.out::println);
            }

            if(option.equals("3")) {
                System.out.println("\n\nPlease enter the user ID you want to search.");
                String searchByUID = input.nextLine();
                //Display specific user's watching history
                System.out.println("\n\nPrint the watch history by "+searchByUID);
                divideInFileSet.stream().filter(s -> s.getUser_id().equals(searchByUID)).forEach(System.out::println);
            }

            if(option.equals("4")) {
                System.out.println("\n\nPlease enter the user name you want to search.");
                String searchByUName = input.nextLine();
                //Display specific user's watching history
                System.out.println("\n\nPrint the watch history by "+searchByUName);
                divideInFileSet.stream().filter(s -> s.getUser_name().equals(searchByUName)).forEach(System.out::println);
            }

            if(option.equals("5")) {
                System.out.println("\n\nPlease enter the movie ID you want to search.");
                String searchByMID = input.nextLine();
                //Display specific movie's watching history
                System.out.println("\n\nPrint the watch history by "+searchByMID);
                divideInFileSet.stream().filter(s -> s.getMovie_id().equals(searchByMID)).forEach(System.out::println);
            }

            if(option.equals("6")) {
                System.out.println("\n\nPlease enter the movie name you want to search.");
                String searchByMName = input.nextLine();
                //Display specific movie's watching history
                System.out.println("\n\nPrint the watch history by "+searchByMName);
                divideInFileSet.stream().filter(s -> s.getMovie_name().equalsIgnoreCase(searchByMName)).forEach(System.out::println);
            }

            if(option.equals("7")) {
                System.out.println("\n\nThe movie that has been watched the most times is:");
                Map<String,Long> movieWatchFreq =
                        divideInFileSet.stream().collect(Collectors.groupingBy
                                (WatchHistory::getMovie_name,Collectors.counting()));

                Map<String,Long> movieWatchRank = new LinkedHashMap<>();
                movieWatchFreq.entrySet().stream()
                        .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                                .forEachOrdered(e->movieWatchRank.put(e.getKey(),e.getValue()));

                System.out.println(movieWatchRank);
            }

            if(option.equals("8")) {
                System.exit(0);
            }

            //Display the film that get better feedbacks

            //Ranking the users by their watching time

        }



    }

}
