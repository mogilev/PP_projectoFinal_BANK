package controllers;

import java.util.List;
import models.Account;

public interface Bank {
	
	int createAccount(int accountQty, int accountStartBalance); //Adiciona contas indicadas pelo utilizador, cada uma com uma quantia inicial predefinida(também escolhida pelo utilizador)
	
	double getTotalBalance(); // Devolve o saldo total existente no banco
	
	void setThreadCreator(boolean permission); // Booleano de controlo de um ciclo
	
	boolean getThreadCreatorStopper(); // Booleano de controlo de um ciclo
	
	void createTransaction(int accountQty); // Adiciona transações 
	
	void internalTransfer(Account senderAccount, Account receiverAccount, int amount); // Executa a transferência entre duas contas. Caso a conta de origem não tenha fundos suficientes, fica a aguardar até a poder realizar 

	boolean accountHasFunds(int accountId, int amount); // Verifica se determinada conta de origem tem saldo suficiente para transferir fundos
	
	void transferReceipt(double transferAmount,int senderAccountId, double senderBalance, int receiverAccountId, double receiverBalance); // Escreve, no ecrã, os dados acerca da transferência efectuada 

	void executionSummary(); // Escreve, no ecrã, um resumo dos dados executados 
	
	// Manuseamento da lista:

	Account findAccount(int accountId); // Devolve o objecto conta através do seu Id
	
	List<Account> getAccountList(); // Devolve a lista de contas existentes	
	

	
	
	
}
