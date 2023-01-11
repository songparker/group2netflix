
import java.util.Objects;

public class Movie {
	private int M_Id;
	private String M_Title;
	private String M_Director;
	private String M_Actors;
	private String M_Year;
	private double M_Rating;
	private String M_Genre;
	private String M_Country;
	private String M_Language;

//Default constructor
	public Movie() {
	}

//Parameterized constructor
	public Movie(int m_Id, String m_Title, String m_Director, String m_Actors, String m_Year, double m_Rating,
			String m_Genre, String m_Country, String m_Language) {
		M_Id = m_Id;
		M_Title = m_Title;
		M_Director = m_Director;
		M_Actors = m_Actors;
		M_Year = m_Year;
		M_Rating = m_Rating;
		M_Genre = m_Genre;
		M_Country = m_Country;
		M_Language = m_Language;
	}

//Setters and getters
	public int getM_Id() {
		return M_Id;
	}

	public void setM_Id(int m_Id) {
		M_Id = m_Id;
	}

	public String getM_Title() {
		return M_Title;
	}

	public void setM_Title(String m_Title) {
		M_Title = m_Title;
	}

	public String getM_Director() {
		return M_Director;
	}

	public void setM_Director(String m_Director) {
		M_Director = m_Director;
	}

	public String getM_Actors() {
		return M_Actors;
	}

	public void setM_Actors(String m_Actors) {
		M_Actors = m_Actors;
	}

	public String getM_Year() {
		return M_Year;
	}

	public void setM_Year(String m_Year) {
		M_Year = m_Year;
	}

	public double getM_Rating() {
		return M_Rating;
	}

	public void setM_Rating(double m_Rating) {
		M_Rating = m_Rating;
	}

	public String getM_Genre() {
		return M_Genre;
	}

	public void setM_Genre(String m_Genre) {
		M_Genre = m_Genre;
	}

	public String getM_Country() {
		return M_Country;
	}

	public void setM_Country(String m_Country) {
		M_Country = m_Country;
	}

	public String getM_Language() {
		return M_Language;
	}

	public void setM_Language(String m_Language) {
		M_Language = m_Language;
	}

//Equals method
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Movie movie = (Movie) o;
		return M_Id == movie.M_Id && Double.compare(movie.M_Rating, M_Rating) == 0
				&& Objects.equals(M_Title, movie.M_Title) && Objects.equals(M_Director, movie.M_Director)
				&& Objects.equals(M_Actors, movie.M_Actors) && Objects.equals(M_Year, movie.M_Year)
				&& Objects.equals(M_Genre, movie.M_Genre) && Objects.equals(M_Country, movie.M_Country)
				&& Objects.equals(M_Language, movie.M_Language);
	}

//Hash code method
	@Override
	public int hashCode() {
		return Objects.hash(M_Id, M_Title, M_Director, M_Actors, M_Year, M_Rating, M_Genre, M_Country, M_Language);
	}

//toString method
	@Override
	public String toString() {
		return "Movie Title = " + M_Title + "\nM_Director = " + M_Director + "\nM_Actors = " + M_Actors + "\nM_Year = "
				+ M_Year + "\nM_Rating = " + M_Rating + "\nM_Genre = " + M_Genre + "\nM_Country = " + M_Country
				+ "\nM_Language = " + M_Language + "\n\n";
	}
}