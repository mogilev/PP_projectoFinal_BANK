package models;

public interface Account {
	
	int getAccountId(); // Devolve o n� de conta
	
	double getAccountBalance(); // Devolve o valor em saldo na conta
	
	void accountTransferIn(double transferAmount); // Actualiza o saldo da conta de destino(transfer�ncia)
	
	void accountTransferOut(double transferAmount); // Actualiza o saldo da conta de origem(transfer�ncia)
	
}
