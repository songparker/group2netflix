
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner enter = new Scanner(System.in);
		System.out.println("Welcome to our NetPlix system\nDo you have an account? Y/N");

		boolean flag = true;
		while (flag) {
			String str = enter.nextLine().toUpperCase();
			switch (str) {
			case "Y":
				Login.Login();
				flag = false;
				break;
			case "N":
				Register.Register();
				flag = false;
				break;
			default:
				System.out.println("Input error, please enter Y or N to choose");
				break;
			}
		}

		AddingMovie.AddingMovie();
	}

}
