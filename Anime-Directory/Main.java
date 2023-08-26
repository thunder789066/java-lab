import java.util.*;
import java.lang.*;

public class Main {
	
	public static String menu_choice = null;
	public static void menu() {
		Scanner menuObj = new Scanner(System.in);
		System.out.println("\r\nAnime Directory Menu"
				+ "\r\n------------------------"
				+ "\r\n (1) Add an Anime"
				+ "\r\n (2) Delete an Anime"
				+ "\r\n (3) Update an Anime"
				+ "\r\n (4) Output all Anime"
				+ "\r\n (q) Quit Menu"
				+ "\r\n\r\nWhat would you like to do?");
		menu_choice = menuObj.nextLine();
	}
	
	public static boolean check;
	public static String genre_shortlist = null;
	public static String anime_link = null;
	public static void user_add_anime_faqs() {
		Scanner genreObj = new Scanner(System.in);
		System.out.println("What is the genre for this Anime?"
				+ "\r\n*Display genre as: 'action, comedy, etc.'"
				+ "\r\nor if none: 'n/a'*");
		genre_shortlist = genreObj.nextLine();
		
		Scanner linkObj = new Scanner(System.in);
		System.out.println("What is the link for this Anime show?");
		anime_link = linkObj.nextLine();
	}
	
	public static String get_anime_name() {
		Scanner nameObj = new Scanner(System.in);
		System.out.println("What is the name of the Anime show?");
		String anime_name = nameObj.nextLine();
		return anime_name;
	}
	
	public static void main(String[] args) {
		//String nameTEMP = "Magi";
		//String genreTEMP = "action, adventure, comedy";
		//String linkTEMP = "www.googe.com";
		
		while (true) {
			Main.menu();
			
			// user ADD anime
			if (menu_choice.equals("1")) {
				String anime_name = Main.get_anime_name();
				check = Menu_Options.double_checker(anime_name);
				if (check == true) {
					System.out.println("\r\n" + anime_name
							+ " already exists in Anime Directory.\r\nCurrent Entry~~");
					Menu_Options.select_anime(anime_name);
				} else {
					Main.user_add_anime_faqs();
					Menu_Options.add_anime(anime_name, genre_shortlist, anime_link);
				}
			}
			
			// user DELETE anime
			else if (menu_choice.equals("2")) {
				String anime_name = Main.get_anime_name();
				check = Menu_Options.double_checker(anime_name);
				if (check == true) {
					Menu_Options.delete_anime(anime_name);
				} else if (Menu_Options.map.isEmpty() == true) {
					System.out.println("\r\nAnime Directory is empty.");
				} else if (check == false) {
					System.out.println("\r\n" + anime_name + " does not exist in Anime Directory.");
				}
			}
			
			// user UPDATE anime
			else if (menu_choice.equals("3")) {
				String anime_name = Main.get_anime_name();
				check = Menu_Options.double_checker(anime_name);
				if (check == true) {
					Menu_Options.update_anime(anime_name);
				} else {
					System.out.println("\r\n" + anime_name + " does not exist in Anime Directory.");
				}
			}
			
			// OUTPUT all anime
			else if (menu_choice.equals("4")) {
				Menu_Options.outputALL();
			}
			
			// QUIT menu & stop program (current run)
			else if (Main.menu_choice.equals("q")) {
				break;
			}
		}
		
	}

}
