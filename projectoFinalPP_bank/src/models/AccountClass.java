package models;

public class AccountClass implements Account {

	private int accountId;
	private double accountBalance;
	private boolean insuficientFunds= false;
	
	
	public AccountClass(int accountId, double accountBalance) {
		this.accountId = accountId;
		this.accountBalance = accountBalance;
	}


	@Override
	public int getAccountId() {
		return this.accountId;
	}

	
	@Override
	public double getAccountBalance() {
		return this.accountBalance;
	}

	
	@Override
	public void accountTransferIn(double transferAmount) {
		this.accountBalance += transferAmount;
	}

	@Override
	public void accountTransferOut(double transferAmount) {
		this.accountBalance -= transferAmount;	
	}


	
}
