package controllers;

import java.util.List;
import models.Account;
import models.Transaction;

public interface Bank {
	
	int createAccount(int accountQty, double accountStartBalance); //Adiciona contas, cada uma com uma quantia inicial predefinida)
	
	double getTotalBalance(); // Devolve o saldo total existente no banco
	
	void setThreadCreator(boolean permission);
	
	boolean getThreadCreatorStopper(); 
	
	void createTransaction(int accountQty); // Adiciona transações 
	
	void internalTransfer(Account senderAccount, Account receiverAccount, double amount, int threadId);

	boolean accountHasFunds(int accountId, double amount); // Verifica se determinada conta de origem tem saldo suficiente para transferir fundos
	
	void transferReceipt(int transactionId, double transferAmount,int senderAccountId, int receiverAccountId); // Escreve, no ecrãn, os dados acerca da transferência efectuada 

	void executionSummary();
	
	// Manuseamento da lista:

	Account findAccount(int accountId);
	
	List<Account> getAccountList(); //// Devolve o conjunto de contas existentes	
	

	
	
	
}
