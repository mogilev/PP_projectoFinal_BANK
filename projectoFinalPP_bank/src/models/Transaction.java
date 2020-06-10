package models;

public interface Transaction {
	
	int getTransactionId(); // Devolve o id da transac��o(thread)
	
	int getSenderAccount(); // Devolve o Id da conta de origem
	
	int getReceiverAccount(); // Devolve o Id da conta de destino
	
}
