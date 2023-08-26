
/*
 * 		Christina Gerstner
 */

import java.util.*;

public class Account {
	
	//randomly generate 10 individual digits, convert #'s to string, concatenate to string
	private String accountNumber = "";
	private String pin; //4 digit pin code for account
	protected double balance; //amount of money in account
	
	public Account(String pin, double balance){
		//takes pin #, takes starting balance amount
		//create an account number
		this.setPin(pin);
		this.balance = balance;
		Random rem = new Random();
		for (int i = 0; i < 10; i++) {
			int val = rem.nextInt(10);
			this.accountNumber += Integer.toString(val);
		}
	}
	
	protected String getAccountNumber(){
		//returns account number
		return this.accountNumber;
	}
	
	public boolean checkPin(String pin) {
		//returns true if pin matches account pin
		if (pin.equals(this.getPin()))
			return true;
		else
			return false;
	}
	
	public void deposit(double amount){
		//deposits amount into account (only if amount is positive)
		if (amount >= 0)
			this.balance += amount;
	}
	
	public boolean withdraw(String pin, double amount){
		/* withdraws given amount of money if correct pin and
		 * sufficient balance. Returns true if transaction
		 * was successful.
		 */
		//balance >= 0 && if amount <=(less than, preventing it from going in the negative) balance
		if (pin.equals(this.getPin()) && this.balance >= 0 && amount <= this.balance) {
			this.balance -= amount;
			return true;
		}
		else
			return false;
	}
	
	public boolean transfer(String pin, double amount, Account other){
		/* withdraws given amount of money if correct pin was
		 * given and deposits it into other account. Returns
		 * true if successful.
		 */
		if (pin.equals(this.getPin()) && this.balance >= 0 && amount <= this.balance) {
			other.balance += amount;
			this.balance -= amount;
			return true;
		}
		else
			return false;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String toString() {
		return "Account Number:\t" + this.accountNumber + "\nPin:\t\t" + this.pin + "\nBalance:\t$" + this.balance;
	}
}
