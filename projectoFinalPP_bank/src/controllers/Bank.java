package controllers;

import java.util.List;
import models.Account;

public interface Bank {
	
	int createAccount(int accountQty, double accountStartBalance); //Adiciona contas, cada uma com uma quantia inicial predefinida)
	
	double getTotalBalance(); // Devolve o saldo total existente no banco
	
	void createTransaction(int accountQty); // Adiciona transa��es 

	boolean accountHasFunds(int accountId, double amount); // Verifica se determinada conta de origem tem saldo suficiente para transferir fundos
	
	void transferReceipt(int transactionId, double transferAmount,int senderAccountId, int receiverAccountId); // Escreve, no ecr�n, os dados acerca da transfer�ncia efectuada 

	// Manuseamento da lista:

	Account findAccount(int accountId);
	
	List<Account> getAccountList(); //// Devolve o conjunto de contas existentes	
	

	
	
	
}
