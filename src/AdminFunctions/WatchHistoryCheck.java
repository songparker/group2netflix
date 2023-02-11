package AdminFunctions;

import Entities.WatchHistory;
import UserFunctions.Menus;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;


public class WatchHistoryCheck {


    public static void watchHistoryMenu() {
        System.out.println("\n1. Check specific watching history records"+
                "\n2. Check a simple statistic report"+
                "\n3. Return to Previous Menu");
    }


    public static void WatchHistoryCheck() throws FileNotFoundException{
        while(true) {

            System.out.println("\nWatching history Menu:");
            watchHistoryMenu();
            System.out.println();

            Scanner inFile = new Scanner(new FileReader("src/watchHistory.txt"));

            Set<WatchHistory> divideInFileSet = new HashSet<WatchHistory>();

            String[] lastWatch = new String[10];


            while (inFile.hasNextLine()) {
                String line = inFile.nextLine();
                lastWatch = line.split("\t", 9);

                WatchHistory historyObj = new WatchHistory();

                historyObj.setRecord_id(Long.parseLong(lastWatch[0]));
                historyObj.setUser_name(lastWatch[1]);
                historyObj.setMovie_id(lastWatch[2]);
                historyObj.setMovie_name(lastWatch[3]);
                historyObj.setWatch_date(lastWatch[4]);
                historyObj.setStart_time(lastWatch[5]);
                historyObj.setEnd_time(lastWatch[6]);
                historyObj.setWatch_time(lastWatch[7]);
                //historyObj.setFinish_or_not(Integer.parseInt(lastWatch[8]));
                historyObj.setFeedback(lastWatch[8]);

                divideInFileSet.add(historyObj);
            }

            Scanner input = new Scanner(System.in);

            System.out.println("Please enter the option number.");
            String option = input.nextLine();


            if (option.equals("1")) {
                System.out.println("\n1. Check all watching history sorted by record_id"+
                        "\n2. Check watching history by date"+
                        "\n3. Check watching history by user name"+
                        "\n4. Check watching history by movie ID"+
                        "\n5. Check watching history by movie name"+
                        "\n6. Return to Previous Menu");
                String subOption = input.nextLine();
                if (subOption.equals("1")) {
                    //Display Movie watching history in the HashSet sorted by record_id
                    System.out.println("\n\nPrint all watch history sorted by record_id");
                    divideInFileSet.stream().sorted(Comparator.comparing(WatchHistory::getRecord_id)).forEach(System.out::println);
                }
                else if(subOption.equals("2")) {
                    boolean flag = true;
                    while(flag){
                        System.out.println("\nPlease enter the date you want to search.");
                        String searchByDate = input.nextLine();
                        if (divideInFileSet.stream().filter(s -> s.getWatch_date().equals(searchByDate)).findAny().isEmpty())
                        {
                            System.out.println("We don't have any records for this date. Please try again");
                        }
                        else{
                            System.out.println("\nHere is the watch history in "+searchByDate);
                            divideInFileSet.stream().filter(s -> s.getWatch_date().equals(searchByDate)).forEach(System.out::println);
                            flag = false;
                        }
                    }
                }


                else if(subOption.equals("3")) {
//                    System.out.println("\nPlease enter the user name you want to search.");
//                    String searchByUName = input.nextLine();
                    //Display specific user's watching history
//                    System.out.println("\n\nPrint the watch history by "+searchByUName);
//                    divideInFileSet.stream().filter(s -> s.getUser_name().equals(searchByUName)).forEach(System.out::println);

                    boolean flag = true;
                    while(flag){
                        System.out.println("\nPlease enter the user name you want to search.");
                        String searchByUName = input.nextLine();
                        if (divideInFileSet.stream().filter(s -> s.getUser_name().equals(searchByUName)).findAny().isEmpty())
                        {
                            System.out.println("We don't have any records for this user. Please check your input and try again");
                        }
                        else{
                            System.out.println("\nHere is the watch history of "+searchByUName);
                            divideInFileSet.stream().filter(s -> s.getUser_name().equals(searchByUName)).forEach(System.out::println);
                            flag = false;
                        }
                    }

                }

                else if(subOption.equals("4")) {
                    //System.out.println("\nPlease enter the movie ID you want to search.");
                   // String searchByMID = input.nextLine();
                    //Display specific movie's watching history
//                    System.out.println("\n\nPrint the watch history by "+searchByMID);
//                    divideInFileSet.stream().filter(s -> s.getMovie_id().equals(searchByMID)).forEach(System.out::println);

                    boolean flag = true;
                    while(flag){
                        System.out.println("\nPlease enter the movie ID you want to search.");
                        String searchByMID = input.nextLine();
                        if (divideInFileSet.stream().filter(s -> s.getMovie_id().equals(searchByMID)).findAny().isEmpty())
                        {
                            System.out.println("We don't have any records for this movie ID. Please check your input and try again");
                        }
                        else{
                            System.out.println("\nHere is the watch history with the movie ID: "+searchByMID);
                            divideInFileSet.stream().filter(s -> s.getMovie_id().equals(searchByMID)).forEach(System.out::println);
                            flag = false;
                        }
                    }
                }

                else if(subOption.equals("5")) {
//                    System.out.println("\n\nPlease enter the movie name you want to search.");
//                    String searchByMName = input.nextLine();
                    //Display specific movie's watching history
//                    System.out.println("\n\nPrint the watch history by "+searchByMName);
//                    divideInFileSet.stream().filter(s -> s.getMovie_name().equalsIgnoreCase(searchByMName)).forEach(System.out::println);

                    boolean flag = true;
                    while(flag){
                        System.out.println("\n\nPlease enter the movie name you want to search.");
                        String searchByMName = input.nextLine();
                        if (divideInFileSet.stream().filter(s -> s.getMovie_name().equalsIgnoreCase(searchByMName)).findAny().isEmpty())
                        {
                            System.out.println("We don't have any records for movie. Please check your input and try again");
                        }
                        else{
                            System.out.println("\nHere is the watch history of movie : "+searchByMName);
                            divideInFileSet.stream().filter(s -> s.getMovie_name().equalsIgnoreCase(searchByMName)).forEach(System.out::println);
                            flag = false;
                        }
                    }

                }

                else if(subOption.equals("6")) {
                    try {
                        WatchHistoryCheck();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }

                else {System.out.println("Please only input valid options from 1 to 6.");}
            }

            else if(option.equals("2")) {
                System.out.println("\n\nThe Top 10 movies that has been watched the most times are:");
                Map<String,Long> movieWatchFreq =
                        divideInFileSet.stream().collect(Collectors.groupingBy
                                (WatchHistory::getMovie_name,Collectors.counting()));

                Map<String,Long> movieWatchRank = new LinkedHashMap<>();
                movieWatchFreq.entrySet().stream()
                        .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                        .forEachOrdered(e->movieWatchRank.put("\n"+e.getKey(),e.getValue()));

                //System.out.println(movieWatchRank);

                Set<Map.Entry<String,Long>> mapEntries = movieWatchRank.entrySet();
                List<Map.Entry<String,Long>> result = new LinkedList<>(mapEntries);
                Collections.sort(result,new Comparator<Map.Entry<String,Long>>(){
                    @Override
                    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });

                Long sort = Long.valueOf(1);
                Map<String,Long> top10 = new LinkedHashMap<>();
                for (Map.Entry<String,Long> newEntry : result) {
                    if (sort <= 10) {
                        top10.put(newEntry.getKey(),newEntry.getValue());
                        ++sort;
                    }
                }
                System.out.println(top10);

                //Display the film that get better feedbacks
                System.out.println("\n\nThe Top 10 movies that has been better feedbacks are:\n" +
                        "(Double Thumbs Up = 2; One Thumbs Up = 1; Thumbs Down = -1; Null = 0)");
                Map<String,Double> movieFeedback =
                        divideInFileSet.stream().collect(Collectors.groupingBy
                                (WatchHistory::getMovie_name,
                                        Collectors.averagingDouble(WatchHistory::feedbackPoint)));

                Map<String,Double> FeedbackRank = new LinkedHashMap<>();
                movieFeedback.entrySet().stream()
                        .sorted(Map.Entry.<String,Double>comparingByValue().reversed())
                        .forEachOrdered(e->FeedbackRank.put("\n"+e.getKey(),e.getValue()));

                //System.out.println(FeedbackRank);

                Set<Map.Entry<String,Double>> mapEntries1 = FeedbackRank.entrySet();
                List<Map.Entry<String,Double>> result1 = new LinkedList<>(mapEntries1);
                Collections.sort(result1,new Comparator<Map.Entry<String,Double>>(){
                    @Override
                    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });

                Long sortFeedback = Long.valueOf(1);
                Map<String,Double> feedbackTop10 = new LinkedHashMap<>();
                for (Map.Entry<String,Double> newEntry : result1) {
                    if (sortFeedback <= 10) {
                        feedbackTop10.put(newEntry.getKey(),newEntry.getValue());
                        ++sortFeedback;
                    }
                }
                System.out.println(feedbackTop10);


            }

            else if(option.equals("3")) {
                try {
                    Menus.AdminMenu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            else {System.out.println("Please only input valid options from 1 to 3.");}

        }

    }
}
