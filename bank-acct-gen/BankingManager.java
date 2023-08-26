
/*
 * 		Christina Gerstner
 */

import java.util.*;
import java.io.*;

/* Client Class */

public class BankingManager {
	private Scanner input = new Scanner(System.in);
	private Bank bank;
	public BankingManager()	{
		this.bank = new Bank();
		this.loadAccounts();
	}
	
	public void runProgram() {
		System.out.println("\n**************************************\n\tBank Start Up Complete\n**************************************");
		System.out.println("\n\tWelcome to the \"First Giant National Bank\"\n");
		
		String choice = "";
		while ( !choice.equals("E") ) {
			choice = this.mainMenu();
			switch ( choice.charAt(0) ) {
				case '1':
					this.createAccount();
					break;
				case '2':
					this.checkBalance();
					break;
				case '3':
					this.lookUpAccounts();
					break;
				case '4':
					this.deposit();
					break;
				case '5':
					this.withdraw();
					break;
				case '6':
					this.transfer();
					break;
				case '7':
					this.updateAccounts();
					break;
				case 'E':
					break;		
				default:
					System.out.println("\n\tInvalid Option");
			}
			
		}		
		
	}
	
	private String mainMenu() {
		System.out.println("\n\t(1) Create An Account\n\t(2) Check Balance\n\t(3) Look-up Accounts\n\t(4) Deposit\n\t(5) Withdraw\n\t(6) Transfer\n\t(7) Update Accounts\n\t(E) Exit");
		System.out.print(">>> ");
		return this.input.nextLine().toUpperCase();
	}
	
	private void createAccount() {
		System.out.print("\n\tSelect Account Type - (C) Checking (S) Savings (I) Investment\n>>> ");
		String type = this.input.nextLine().toUpperCase();
		
		System.out.print("\n\tSelect 4-Digit PIN\n>>> ");
		String pin = this.input.nextLine().toUpperCase();
		
		System.out.print("\n\tAmount to Deposit\n>>> ");
		double balance = Double.parseDouble( this.input.nextLine() );
		
		Account account = null;
		if (type.equals("C")) {
			account = new CheckingAccount(pin, balance);
			System.out.println("\n\n\t**** Checking Account Created! ****");
		}
		else if (type.equals("S")) {
			account = new SavingsAccount(pin, balance);
			System.out.println("\n\n\t**** Savings Account Created! ****");
		}
		else if (type.equals("I")) {
			System.out.println("\n\tEnter Volatility\n>>> ");
			double volatility = (int)(Math.random() * 8);
			account = new InvestmentAccount(pin, balance, volatility);
			System.out.println("\n\n\t**** Investment Account Created! ****");
		}
		this.bank.addAccount(account);
		System.out.println("\n" + account);
	}
	
	public void checkBalance() {
		System.out.print("\n\tEnter Account Number\n>>> ");
		String accountNumber = this.input.nextLine();
		
		System.out.print("\n\tEnter Account PIN\n>>> ");
		String pin = this.input.nextLine();
		
		Account account = this.bank.getAccount(accountNumber);
		if (account != null  &&  account.checkPin(pin))
			System.out.println("\n\n" + account);
		else
			System.out.println("\n\n\tSorry... there was an error");
	}
	
	public void lookUpAccounts() {
		System.out.print("\n\n\t(1)Checking\n\t(2)Savings\n\t(3)Investment\n\t(4)Accounts Outstanding\n>>> ");
		String option = this.input.nextLine();
		
		switch (option.charAt(0)) {
			case '1':
				for (CheckingAccount account : this.bank.getCheckingAccounts())
					System.out.println("\n" + account);
				break;
			case '2':
				for (SavingsAccount account : this.bank.getSavingsAccounts())
					System.out.println("\n" + account);
				break;
			case '3':
				for (InvestmentAccount account : this.bank.getInvestmentAccounts())
					System.out.println("\n" + account);
				break;
			case '4':
				for (CheckingAccount account : this.bank.getAccountsOutstanding())
					System.out.println("\n" + account);
				break;
			default:
				break;
		}
		
	}
	
	private void deposit() {
		System.out.print("\n\tEnter Account Number\n>>> ");
		String accountNumber = this.input.nextLine();
		
		System.out.print("\n\tEnter Amount\n>>> ");
		double amount = Double.parseDouble(this.input.nextLine());
		
		Account account = this.bank.getAccount(accountNumber);
		if (account != null) {
			account.deposit(amount);
			System.out.println("\n\tAmount of $" + amount + " was deposited!");
		}
		else
			System.out.println("\n\n\tSorry, there was an error...");
			
	}
	
	private void withdraw(){
		System.out.print("\n\tEnter Account Number\n>>> ");
		String accountNumber = this.input.nextLine();
		
		System.out.print("\n\tEnter Account PIN\n>>> ");
		String pin = this.input.nextLine();
		
		System.out.print("\n\tEnter Amount\n>>> ");
		double amount = Double.parseDouble(this.input.nextLine());
		
		Account account = this.bank.getAccount(accountNumber);
		if (account != null  &&  account.withdraw(pin, amount))
			System.out.println("Amount of $" + amount + " was successfully withdrawn!");
		else
			System.out.println("\n\n\tSorry... there was an error");
	}
	
	private void transfer() {
		System.out.print("\n\tEnter 1st Account Number\n>>> ");
		String accountNumber1 = this.input.nextLine();
		
		System.out.print("\n\tEnter 2nd Account Number\n>>> ");
		String accountNumber2 = this.input.nextLine();
		
		System.out.print("\n\tEnter Account PIN\n>>> ");
		String pin = this.input.nextLine();
		
		System.out.print("\n\tEnter Amount\n>>> ");
		double amount = Double.parseDouble(this.input.nextLine());
		
		Account account1 = this.bank.getAccount(accountNumber1);
		Account account2 = this.bank.getAccount(accountNumber2);
		if (account1 != null  &&  account2 != null  &&  account1.withdraw(pin, amount)) {
			System.out.println("Amount of $" + amount + " was successfully transfered between accounts!");
			account2.deposit(amount);
		}
		else
			System.out.println("\n\n\tSorry... there was an error");
	}
	
	private void updateAccounts() {
		this.bank.updateAccounts();
		System.out.println("\n\n\tAll Accounts Updated!");
	}
	
		
	private void loadAccounts() {
		//Create file object for text file
		File file = new File("accounts.txt");
		
		//Reader object for file
		Reader reader = null;
		try {	//Try - catch for IOExceptions when handling files
			//Create and point reader to file
			reader = new FileReader(file);
			//Buffered Readers allow more control, require Reader to work with
			BufferedReader buffReader = new BufferedReader(reader);
			//Save each line in txt file here
			String line = null;
			
			//YES - YOU CAN ASSIGN VALUES INSIDE OF LOOPS/IFS - DON'T DO IT
			//	ON THE AP TEST!!!!
			while ( (line = buffReader.readLine()) != null) {			
				//Split the line of txt up into array (look at txt file)
				//	Each parameter is separated by a space (" ") character
				String[] parameters = line.split(" ", -1);
				
				//First item is single letter to indicate type of account
				//	to create. (C) Checking  (S) Savings  (I) Investment
				String type = parameters[0];
				//Second String will always be the pin number
				String pin = parameters[1];
				//Third String will always be the balance, we need to parse
				//	it to a double.
				double balance = Double.parseDouble(parameters[2]);
				
				//Create checking account
				if (type.equals("C"))
					this.bank.addAccount(new CheckingAccount(pin, balance));
				//Create savings account
				else if (type.equals("S"))
					this.bank.addAccount(new SavingsAccount(pin, balance));	
				//Create investment account
				else if (type.equals("I")) {
					//3rd parameter - volatility
					//Array will only have a 4th value if it was an Investment line
					double volatility = Double.parseDouble(parameters[3]);
					this.bank.addAccount(new InvestmentAccount(pin, balance, volatility));
				}
			}
		//Catch IOException - "Input/Output Exception"
		} catch (IOException ex) {
			//Trace code
			ex.printStackTrace();
			System.out.println("\n\t>>>>> DID YOU CHANGE MY FILE NAME OR JACK WITH MY FILE?\n");
		
		} finally {
			//The 'finally' keyword for try-except executes at the end regardless
			//	if there was an exception or code successfully executed.
			try {
				//Close the reader for 1) memory, 2) Restart at top next time
				reader.close();
				
			} catch (IOException ex) {
				//NOTHING 
			}
		}
	}
//=====================================================================================
	public static void main(String[] args) {
		new BankingManager().runProgram();
		//AccountGenerator.main(args);
	}
}