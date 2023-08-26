/**
 *
 * Just for fun, class I wrote to randomly generate Accounts for project.
 * Each time you run this class, the txt file will be erased and new 
 * Accounts will be randomly generated. Replace the integer in the call
 * to createAccounts(int) method in order to pick how many Accounts your
 * bank should start with.
 *
 */
import java.io.*;

public class AccountGenerator {
	public static void main(String[] args) {
		createAccounts(10);		
	}
	
	public static void createAccounts(int numberOfAccounts) {
		String[] types = {"C", "S", "I"};
		String type, pin, balance, line;
		
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter("accounts.txt"));
			
			for (int i = 0; i < numberOfAccounts; i++) {
				type = types[ (int)(Math.random() * 3) ];
				
				pin = "";
				for (int j = 0; j < 4; j++)
					pin += "" + (int)(Math.random() * 10);
				
				double money = Math.random() * 900 + 100;
				balance = "" + Math.round(money * 100.0) / 100.0;
				
				line = type + " " + pin + " " + balance;
				if (type.equals("I"))
					line += " " + (int)(Math.random() * 10);
				
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException ex) {
				//Nothing
			}
		}
	}
}