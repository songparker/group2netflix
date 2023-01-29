public class FavoriteList {
    private String User_name;
    private String Movie_id;
    private String Movie_name;
    private String Add_date;

    public FavoriteList(String user_name, String movie_id, String movie_name, String add_date) {
        User_name = user_name;
        Movie_id = movie_id;
        Movie_name = movie_name;
        Add_date = add_date;
    }

    public FavoriteList() {

    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getMovie_id() {
        return Movie_id;
    }

    public void setMovie_id(String movie_id) {
        Movie_id = movie_id;
    }

    public String getMovie_name() {
        return Movie_name;
    }

    public void setMovie_name(String movie_name) {
        Movie_name = movie_name;
    }

    public String getAdd_date() {
        return Add_date;
    }

    public void setAdd_date(String add_date) {
        Add_date = add_date;
    }

    @Override
    public String toString() {
        return "FavoriteList{" +
                "User_name='" + User_name + '\'' +
                ", Movie_id='" + Movie_id + '\'' +
                ", Movie_name='" + Movie_name + '\'' +
                ", Add_date='" + Add_date + '\'' +
                '}';
    }
}
