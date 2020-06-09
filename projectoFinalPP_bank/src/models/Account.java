package models;

public interface Account {
	
	int getAccountId(); // Devolve o nº de conta
	
	double getAccountBalance(); // Devolve o valor em saldo na conta
	
	void accountTransferIn(double transferAmount); // Actualiza o saldo da conta de destino(transferência)
	
	void accountTransferOut(double transferAmount); // Actualiza o saldo da conta de origem(transferência)
	
}
