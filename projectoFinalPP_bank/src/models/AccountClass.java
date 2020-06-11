package models;

public class AccountClass implements Account {

	private int accountId;
	private int accountBalance;
	
	
	public AccountClass(int accountId, int accountBalance) {
		this.accountId = accountId; // Identifica��o da conta
		this.accountBalance = accountBalance; // Saldo da conta
	}


	@Override
	public int getAccountId() { // Devolve a identifica��o da conta
		return this.accountId;
	}

	
	@Override
	public int getAccountBalance() { // Devolve o saldo da conta
		return this.accountBalance;
	}

	
	@Override
	public void accountTransferIn(int transferAmount) { // Realiza a recep��o da transfer�ncia(conta de destino)
		this.accountBalance += transferAmount;
	}

	@Override
	public void accountTransferOut(int transferAmount) { // Realiza o envio da transfer�ncia(conta de origem)
		this.accountBalance -= transferAmount;	
	}

	
}
