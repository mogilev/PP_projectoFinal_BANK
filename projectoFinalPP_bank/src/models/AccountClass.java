package models;

public class AccountClass implements Account {

	private int accountId;
	private int accountBalance;
	
	
	public AccountClass(int accountId, int accountBalance) {
		this.accountId = accountId; // Identificação da conta
		this.accountBalance = accountBalance; // Saldo da conta
	}


	@Override
	public int getAccountId() { // Devolve a identificação da conta
		return this.accountId;
	}

	
	@Override
	public int getAccountBalance() { // Devolve o saldo da conta
		return this.accountBalance;
	}

	
	@Override
	public void accountTransferIn(int transferAmount) { // Realiza a recepção da transferência(conta de destino)
		this.accountBalance += transferAmount;
	}

	@Override
	public void accountTransferOut(int transferAmount) { // Realiza o envio da transferência(conta de origem)
		this.accountBalance -= transferAmount;	
	}

	
}
