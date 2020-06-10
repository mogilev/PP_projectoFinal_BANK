package models;

import controllers.Bank;

public class TransactionClass extends Thread implements Transaction {
	
	Bank bank;
	private int transactionId;
	private Account senderAccount;
	private Account receiverAccount;
	private double transactionAmount;
	
	
	public TransactionClass(Bank bank, int transactionId, Account senderAccount, Account receiverAccount, double transactionAmount) {
		this.bank = bank;
		this.transactionId = transactionId;
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.transactionAmount = transactionAmount;
		this.start();
	}
	

	@Override
	public int getTransactionId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSenderAccount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getReceiverAccount() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void run() {
		// TODO
		System.out.println("Teste\n " + "Thread "+ this.transactionId + " Conta origem = " + senderAccount.getAccountId() + "- Conta destino = " + receiverAccount.getAccountId() + " - Valor transferido" + String.format("%.2f", this.transactionAmount) );
	}
}
