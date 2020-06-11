package models;

public interface Account {
	
	int getAccountId(); // Devolve o nº de conta
	
	int getAccountBalance(); // Devolve o valor em saldo na conta
	
	void accountTransferIn(int transferAmount); // Actualiza o saldo da conta de destino(transferência)
	
	void accountTransferOut(int transferAmount); // Actualiza o saldo da conta de origem(transferência)
	
}
