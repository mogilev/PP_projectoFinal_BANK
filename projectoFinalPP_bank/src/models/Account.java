package models;

public interface Account {
	
	int getAccountId(); // Devolve o n� de conta
	
	int getAccountBalance(); // Devolve o valor em saldo na conta
	
	void accountTransferIn(int transferAmount); // Actualiza o saldo da conta de destino(transfer�ncia)
	
	void accountTransferOut(int transferAmount); // Actualiza o saldo da conta de origem(transfer�ncia)
	
}
