
/*
 * 		Christina Gerstner
 */

import java.util.*;

/* Investment account is a type of savings account at the bank. The
 * difference between an investment account & a savings account
 * is the interest rate is randomly determined (mimic a changing
 * stock on market) each month depending on how "volatile" the
 * underlying stock is. Also, an investment account loses money in
 * a month the balance will level off at 0.
 */

public class InvestmentAccount extends SavingsAccount{
	
	private double volatility; /* how volatile the stock is. If volatility is equal to 0.03,
	 							* then this indicates the interest rate each month will be randomly
	 							* selected between -0.03 & 0.03	*/
	
	
	public InvestmentAccount(String pin, double balance, double volatility){
		//take a third value that sets the volatility of the account
		super(pin, balance);
		this.volatility = volatility;
	}

	/* override the update method to first randomly calculate the new
	 * interest rate based on the volatility & protect against the balance
	 * dropping in negative after the interest is applied. Interest rate
	 * should be randomly selected between -2% & +2%.
	 */
	@Override public void update() {
		Random rem = new Random();
		this.interestRate = rem.nextDouble() * 2 * this.volatility - this.volatility;
		this.balance += this.balance * this.interestRate;
	}

	@Override public String toString() {
		//return "Investment Account\n" + super.toString() + "\nVolatolity:\t" + this.volatility;
		return "Investment Account\n" + "Account Number:\t" + this.getAccountNumber() + "\nPin:\t\t" + this.getPin() + "\nBalance:\t$" + this.balance;
	}
}