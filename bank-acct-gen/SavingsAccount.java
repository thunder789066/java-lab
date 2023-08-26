
/*
 * 		Christina Gerstner
 */

/* Savings Account is a type of account at the bank. The
 * difference is a savings account has an interest rate
 * of 2% & a maximum number of withdraws you can make
 * in a given month of 3. At the end of each month all
 * savings accounts are updated which resets the withdraws
 * & applies the interest rate to the balance.
 */

public class SavingsAccount extends Account{

	public SavingsAccount(String pin, double balance){
		super(pin, balance);
	}
	
	private int totalWithdraws; //total # of withdraws that has taken play this month
	protected static final int WITHDRAW_LIMIT = 3; //total allowed # of withdraws in a single month
	protected double interestRate; //interest rate on this account
	
	public void update() {
		/* resets the withdraws for the month & applies interest */
		totalWithdraws = 0;
		this.balance += this.balance * 0.02;
	}
	
	/* override withdraw method to check if account has reached max # of withdraws first */
	@Override public boolean withdraw(String pin, double amount){
		if (pin.equals(this.getPin()) && totalWithdraws < WITHDRAW_LIMIT) {
			this.balance -= amount;
			this.totalWithdraws++;
			return true;
		}
		else
			return false;
	}

	@Override public String toString() {
		return "Savings Account\n" + super.toString() + "\nInterest Rate:\t" + this.interestRate + "\nCurrent # of Withdraws:\t" + this.totalWithdraws;
	}
}
