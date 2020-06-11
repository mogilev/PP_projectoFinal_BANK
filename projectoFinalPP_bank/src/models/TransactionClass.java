package models;

import controllers.Bank;

public class TransactionClass extends Thread implements Transaction {
	
	Bank bank;
	private Account senderAccount;
	private Account receiverAccount;
	private int transactionAmount;
	
	
	public TransactionClass(Bank bank, Account senderAccount, Account receiverAccount, int transactionAmount) {
		this.bank = bank;
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.transactionAmount = transactionAmount;
		this.start();
	}
	

	@Override
	public Account getSenderAccount() {
		return this.senderAccount;
	}

	@Override
	public Account getReceiverAccount() {
		return this.receiverAccount;
	}

	@Override
	public int getTransactionAmount() {
		return this.transactionAmount;
	}

	
	public void run() { // Executa este bloco de código através de uma thread dedicada
		
		bank.internalTransfer(this.getSenderAccount(), this.getReceiverAccount(), this.getTransactionAmount()); // Executa uma transferência interna
	
	}

}
