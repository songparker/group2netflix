package UserFunctions;

import Entities.Movie;

import java.time.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class MoviePlaying {
    private static Thread thread;
    private static boolean isPaused;
    private static boolean isStopped;

    public static void play(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        //AddingWatchHistory.addIntoWatchHistory(movie);
        String feedback = null;
        isStopped = false;
        isPaused = false;
        thread = new Thread(new RunnableTask());
        thread.start();
		Instant start = Instant.now();

        //scanner.nextLine();

        while (!isStopped) {
            String input = scanner.nextLine();
            if (input.equals(" ")) {
                if (isPaused) {
                    resumeThread();
                    System.out.println("Resume");
                } else {
                    pauseThread();
                    System.out.println("Pause");
                }
            } else if (input.equalsIgnoreCase("S")) {
                stopThread();
                System.out.println("How do you like this movie?\n" +
                        "1 - Thumbs up; 2 - Two Thumbs up; 3 - Thumbs down; 4 or else - No Comment");
                String userFb = scanner.nextLine();
                if (userFb.equals("1")) {
                    feedback = "Thumbs Up";
                }
                else if (userFb.equals("2")) {
                    feedback = "Two Thumbs Up";
                }
                else if (userFb.equals("3")) {
                    feedback = "Thumbs Down";
                }
                else {
                    feedback = "-";
                }
                break;
            }else {
                System.out.println("Invalid input. Please enter either a space to pause/resume playing or 'S' to stop it.");
            }
        }
		Instant end = Instant.now();
		
		BufferedWriter bw ;
        try{
            bw= new BufferedWriter(new FileWriter("src/watchHistory.txt",true));;

            String userName = LogIn.accountFound().getUserName();

            int movieId = movie.getM_Id();

            String movieName = movie.getM_Title();

            Date date = Date.from(end);
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String watchDate = dateFormat.format(date);

            long watchTime = Duration.between(start,end).toMillis();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
            String startW = start.atZone(ZoneId.systemDefault()).format(formatter);
            String endW = end.atZone(ZoneId.systemDefault()).format(formatter);


            String reccord = indexRecord()+ "\t" + userName + "\t" + movieId + "\t" + movieName + "\t"
                    + watchDate + "\t" + startW + "\t" + endW+ "\t"
                    + timeExchange(watchTime) + "\t" + feedback;
            if(!checkItemExisted(reccord)){
                bw.write(reccord + "\r\n");
            }
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            System.out.println("Playing stopped, Backing to main menu...\n");
            Menus.UserMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        scanner.close();
    }

    public static void pauseThread() {
        isPaused = true;
    }

    public static void resumeThread() {
        isPaused = false;
        synchronized (thread) {
            thread.notify();
        }
    }

    public static void stopThread() {
        isStopped = true;
        thread.interrupt();
    }

    private static class RunnableTask implements Runnable {
        @Override
        public void run() {
            int i = 1000;
            while (!isStopped) {
                if (isPaused) {
                    synchronized (thread) {
                        try {
                            thread.wait();
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
                System.out.println("Movie Playing: " + i--);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
	
    private static boolean checkItemExisted(String str) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/watchHistory.txt"));
        List<String> recordList = new ArrayList<>();
        String record;
        while ((record = br.readLine()) != null){
            recordList.add(record);
        }
        boolean anyMatch = false;
        for(String favRecord: recordList){
            String[] arrExist = favRecord.split("\t");
            String[] arrNew = str.split("\t");
            if((arrNew[0].equals(arrExist[0]) && (arrNew[1].equals(arrExist[1])))){
                anyMatch = true;
                break;
            }
        }
        return anyMatch;
    }

	
	public static int indexRecord() {
        int max = Integer.MIN_VALUE;
        try (BufferedReader br = new BufferedReader(new FileReader("src/watchHistory.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int firstArg = Integer.parseInt(line.split("\t")[0]);
                max = Math.max(max, firstArg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return max+1;
    }

    public static String timeExchange(long milliseconds)
    {
        long hours = ((milliseconds/1000)/60)/60;
        long minutes = (milliseconds/1000)/60-(hours*60);
        long seconds = (milliseconds/1000)%60;
        long ms = milliseconds%1000;
        return (String.format("%dH%dM%dS%dSS",hours,minutes,seconds,ms));
    }
}
