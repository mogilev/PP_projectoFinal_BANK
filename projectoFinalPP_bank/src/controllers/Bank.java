package controllers;

public interface Bank {
	
	int createAccount(int accountQty, int accountStartBalance); //Adiciona contas, cada uma com uma quantia inicial predefinida)

	double getTotalBalance(); // Devolve o saldo total existente no banco
	
	int createTransaction(int accountQty); // Adiciona transações 

	boolean accountHasFunds(int accountId); // Verifica se determinada conta de origem tem saldo suficiente para transferir fundos
	
	void transferReceipt(int transactionId, double transferAmount,int senderAccountId, int receiverAccountId); // Escreve, no ecrãn, os dados acerca da transferência efectuada 
	
	
	
	// Manuseamento da lista:
	
	
	
}
