
/*
 * 		Christina Gerstner
 */

import java.util.*;

/* This class will represent a bank object. A bank object
 * has a list of Accounts & several methods for working
 * with this of accounts.
 */

public class Bank {
	
	private ArrayList<Account> accounts; //all accounts at the bank
	
	public Bank() {
		//returns list for accounts
		this.accounts = new ArrayList<Account>();
	}
	
	public Account getAccount(String accountNumber) {
		// return specified account to bank
		for (int i = 0; i < accounts.size(); i++){
			if (accountNumber.equals(this.accounts.get(i).getAccountNumber())){
				return this.accounts.get(i);
			}
		}
		return null;
	}
	
	public void addAccount(Account newAccount){
		accounts.add(newAccount);
	}
	
	public boolean deposit(String accountNumber, double amount) {
		// deposits specified amount to account. returns true if account with given # was found
		Account temp = getAccount(accountNumber);
		if (temp == null)
			return false;
		else {
			temp.deposit(amount);
			return true;
		}
	}
	
	public boolean withdraw(String accountNumber, String pin, double amount){
		// withdraws from specified account, returns success of transaction
		Account temp = getAccount(accountNumber);
		if (temp == null)
			return false;
		else {
			boolean x = temp.withdraw(pin, amount);
			return x;
		}
	}
	
	public boolean transfer(String account, String otherAccount, String pin, double amount) {
		//withdraws from first account, deposits into second account, returns success of transaction
		Account acc1 = getAccount(account);
		Account acc2 = getAccount(otherAccount);
		if (acc1 == null || acc2 == null)
			return false;
		else {
			boolean s = acc1.transfer(pin, amount, acc2);
			return s;
		}
	}
	
	public ArrayList<CheckingAccount> getCheckingAccounts() {
		//returns list of all checking accounts at the bank
		ArrayList<CheckingAccount> temp = new ArrayList<CheckingAccount>();
		for (int i = 0; i < accounts.size(); i++){			
			if (this.accounts.get(i) instanceof CheckingAccount) {
				temp.add((CheckingAccount)this.accounts.get(i));				
			}
		}
		return temp;
	}
	
	public ArrayList<SavingsAccount> getSavingsAccounts() {
		//returns list of all savings accounts at the bank
		ArrayList<SavingsAccount> temp = new ArrayList<SavingsAccount>();
		for (int i = 0; i < accounts.size(); i++){			
			if (this.accounts.get(i) instanceof SavingsAccount) {
				temp.add((SavingsAccount)this.accounts.get(i));				
			}
		}
		return temp;
	}
	
	public ArrayList<InvestmentAccount> getInvestmentAccounts() {
		//returns list of all Investment accounts at the bank
		ArrayList<InvestmentAccount> temp = new ArrayList<InvestmentAccount>();
		for (int i = 0; i < accounts.size(); i++){			
			if (this.accounts.get(i) instanceof InvestmentAccount) {
				temp.add((InvestmentAccount)this.accounts.get(i));				
			}
		}
		return temp;
	}
	
	public void updateAccounts() {
		//updates all accounts that are capable of updating
		ArrayList<SavingsAccount> temp = this.getSavingsAccounts();
		for (int i = 0; i < temp.size(); i++){			
			temp.get(i).update();
		}
	}
	
	public ArrayList<CheckingAccount> getAccountsOutstanding() {
		//returns list of all accounts that are consider "outstanding"
		ArrayList<CheckingAccount> outstandingAccounts = new ArrayList<CheckingAccount>();
		ArrayList<CheckingAccount> temp = this.getCheckingAccounts();
		for (int i = 0; i < temp.size(); i++){
			if(temp.get(i).isOutstanding()){
				outstandingAccounts.add(temp.get(i));
			}
		}
		return outstandingAccounts;
	}
	
}