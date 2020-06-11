package models;

public interface Transaction {
	
	Account getSenderAccount(); // Devolve o Id da conta de origem
	
	Account getReceiverAccount(); // Devolve o Id da conta de destino
	
	int getTransactionAmount(); // Devolve o valor que será transferido
	
}
