
/*
 * 		Christina Gerstner
 */

/* Checking account is a type of account at the bank. The only
 * difference is all the checking accounts have overdraft
 * protection. Meaning, they are allowed to go into the negative
 * balance. If account has a negative balance it is said
 * to be 'outstanding'.
 */

public class CheckingAccount extends Account{
	
	public CheckingAccount(String pin, double balance){
		super(pin, balance);
	}
	
	public boolean isOutstanding() {
		/* returns true this account is outstanding */
		//Account.balance < 0  -> true
		if (this.balance < 0)
			return true;
		else
			return false;
	}
	
	/* override withdraw method to allow negative balance */
	@Override public boolean withdraw(String pin, double amount){
		if (pin.equals(this.getPin())) {
			this.balance -= amount;
			return true;
		}
		else
			return false;
	}
	
	@Override public String toString() {
		return "Checking Account\n" + super.toString();
	}
}
