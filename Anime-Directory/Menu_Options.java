import java.util.*;
import java.util.Map.Entry;

public class Menu_Options {
	//private static String anime_name;
	//private static String genre; /* (may be more than 1 genre) */ 
	//private static String link;
	private static int id = 1;
	
	public static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	
	/*public Menu_Options(String name, String genre, String link) {
		this.anime_name = name;
		this.genre = genre;
		this.link = link;
	}*/
	
	public static void select_anime(String name) {
		Iterator<Map.Entry<String, ArrayList<String>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, ArrayList<String>> anime_id = iterator.next();
			ArrayList<String> anime_faqs = anime_id.getValue();
			if (anime_id.getKey().equalsIgnoreCase(name)) {
				System.out.println("\r\nAnime:\t" + name
								 + "\r\nGenre:\t" + anime_faqs.get(0)
								  + "\r\nLink:\t" + anime_faqs.get(1));
			}
		}
	}
	
	public static boolean double_checker(String name) { // checks if anime exists in hashmap etc.
		Iterator<Map.Entry<String, ArrayList<String>>> iterator = map.entrySet().iterator();
		boolean anime_present = false;
		while (iterator.hasNext()) {
			Map.Entry<String, ArrayList<String>> anime_id = iterator.next();
			if (anime_id.getKey().equalsIgnoreCase(name)) {
				anime_present = true;
				break;
			}
		} return anime_present;
	}
	
	public static void add_anime(String name, String genre, String link) {	// add an anime
		ArrayList<String> temp_anime_list = new ArrayList<String>();
		temp_anime_list.add(genre);
		temp_anime_list.add(link);
		
		map.put(name, temp_anime_list);
	}
	
	public static void delete_anime(String name) {	// delete an anime
		Iterator<Map.Entry<String, ArrayList<String>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, ArrayList<String>> anime_id = iterator.next();
			
			if (anime_id.getKey().equalsIgnoreCase(name)) {
				iterator.remove();
			}
		}
	}
	
	public static void update_anime(String name) { // update an anime
		select_anime(name);
		Scanner changeObj = new Scanner(System.in);
		System.out.println("\r\nWhich are you updating?\t\r\n (1) Genre\r\n (2) Link");
		String update_choice = changeObj.nextLine();
		
		if (update_choice.equals("1")) { // ask for new genre change
			Scanner genre_changeObj = new Scanner(System.in);
			System.out.println("\r\nWhat is the NEW genre?\r\n*Rewrite list; include change*");
			String new_genre = genre_changeObj.nextLine();
			
			Iterator<Map.Entry<String, ArrayList<String>>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, ArrayList<String>> anime_id = iterator.next();
				ArrayList<String> anime_faqs = anime_id.getValue();
				if (anime_id.getKey().equalsIgnoreCase(name)) {
					anime_faqs.set(0, new_genre);
					map.put(name, anime_faqs);
				}
			}
		} else if (update_choice.equals("2")) { // ask for new link change
			Scanner link_changeObj = new Scanner(System.in);
			System.out.println("\r\nWhat is the NEW link?");
			String new_link = link_changeObj.nextLine();
			
			Iterator<Map.Entry<String, ArrayList<String>>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, ArrayList<String>> anime_id = iterator.next();
				ArrayList<String> anime_faqs = anime_id.getValue();
				if (anime_id.getKey().equalsIgnoreCase(name)) {
					anime_faqs.set(1, new_link);
					map.put(name, anime_faqs);
				}
			}
		}
		System.out.println("\r\nNew Entry~~");
		select_anime(name);
	}
	
	public static void outputALL() { // outputs all anime
		for (Entry<String, ArrayList<String>> entry : map.entrySet()) {
			String name = entry.getKey();
            ArrayList<String> anime_faqs = entry.getValue();
            System.out.println("\r\n" + id + " |\t" + name
            						  + "\r\n  |\t" + anime_faqs.get(0)
            						  + "\r\n  |\t" + anime_faqs.get(1));
            id++;
         } id = 1;
	}
	
	
}
