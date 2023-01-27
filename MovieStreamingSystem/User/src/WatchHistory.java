import java.util.Objects;

public class WatchHistory {
    private String user_id;
    private String user_name;
    private String movie_id;
    private String movie_name;
    private String watch_date;
    private String start_time;
    private String end_time;
    //watch_time: On the movies' timeline where the user stopped watching
    //I will do a better watch_time, transform from ms to normal time format, so it would be more precise
    private long watch_time;
    //finish_or_not: a mark whether the user finish watching the film or not
    //if not, it should be practical for them to continue watching since we got this
    //0 represent no, 1 represent yes
    private int finish_or_not;
    //A simple feedback system for netflix: thumbs down, up, or supper up
    private String feedback;

    public WatchHistory() {
        user_id=null;
        user_name=null;
        movie_id=null;
        movie_name=null;
        watch_date=null;
        start_time=null;
        end_time=null;
        watch_time=0;
        finish_or_not=0;
        feedback=null;
    }

    //constructor
    public WatchHistory(String user_id, String user_name, String movie_id, String movie_name, String watch_date, String start_time, String end_time, long watch_time, int finish_or_not, String feedback) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.watch_date = watch_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.watch_time = watch_time;
        this.finish_or_not = finish_or_not;
        this.feedback = feedback;
    }

    //getters and setters
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getWatch_date() {
        return watch_date;
    }

    public void setWatch_date(String watch_date) {
        this.watch_date = watch_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public long getWatch_time() {
        return watch_time;
    }

    public void setWatch_time(long watch_time) {
        this.watch_time = watch_time;
    }

    public int getFinish_or_not() {
        return finish_or_not;
    }

    public void setFinish_or_not(int finish_or_not) {
        this.finish_or_not = finish_or_not;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_name);
    }



    //time exchange
    public static String timeExchange(long milliseconds)
    {
        long hours = ((milliseconds/1000)/60)/60;
        long minutes = (milliseconds/1000)/60-(hours*60);
        long seconds = (milliseconds/1000)%60;
        long ms = milliseconds%1000;
        return (String.format("%d hours %d Minutes %d Seconds %d Milliseconds",hours,minutes,seconds,ms));
    }

    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WatchHistory other = (WatchHistory) obj;
        return Objects.equals(user_name, other.user_name);
    }
     */


    @Override
    public String toString() {
        return "WatchHistory{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", movie_id='" + movie_id + '\'' +
                ", movie_name='" + movie_name + '\'' +
                ", watch_date='" + watch_date + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", watch_time='" + timeExchange(watch_time) + '\'' +
                ", finish_or_not=" + finish_or_not +
                ", feedback='" + feedback + '\'' +
                '}';
    }


}


