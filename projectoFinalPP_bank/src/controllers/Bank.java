package controllers;

import java.util.List;
import models.Account;

public interface Bank {
	
	int createAccount(int accountQty, int accountStartBalance); //Adiciona contas indicadas pelo utilizador, cada uma com uma quantia inicial predefinida(tamb�m escolhida pelo utilizador)
	
	double getTotalBalance(); // Devolve o saldo total existente no banco
	
	void setThreadCreator(boolean permission); // Booleano de controlo de um ciclo
	
	boolean getThreadCreatorStopper(); // Booleano de controlo de um ciclo
	
	void createTransaction(int accountQty); // Adiciona transa��es 
	
	void internalTransfer(Account senderAccount, Account receiverAccount, int amount); // Executa a transfer�ncia entre duas contas. Caso a conta de origem n�o tenha fundos suficientes, fica a aguardar at� a poder realizar 

	boolean accountHasFunds(int accountId, int amount); // Verifica se determinada conta de origem tem saldo suficiente para transferir fundos
	
	void transferReceipt(double transferAmount,int senderAccountId, double senderBalance, int receiverAccountId, double receiverBalance); // Escreve, no ecr�, os dados acerca da transfer�ncia efectuada 

	void executionSummary(); // Escreve, no ecr�, um resumo dos dados executados 
	
	// Manuseamento da lista:

	Account findAccount(int accountId); // Devolve o objecto conta atrav�s do seu Id
	
	List<Account> getAccountList(); // Devolve a lista de contas existentes	
	

	
	
	
}
