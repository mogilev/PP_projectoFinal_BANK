package models;

public interface Transaction {
	
	int getTransactionId(); // Devolve o id da transac��o(thread)
	
	Account getSenderAccount(); // Devolve o Id da conta de origem
	
	Account getReceiverAccount(); // Devolve o Id da conta de destino
	
	double getTransactionAmount();
	
}
