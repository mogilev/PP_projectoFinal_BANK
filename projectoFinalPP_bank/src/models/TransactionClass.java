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
	public Account getSenderAccount() {
		// TODO Auto-generated method stub
		return this.senderAccount;
	}

	@Override
	public Account getReceiverAccount() {
		// TODO Auto-generated method stub
		return this.receiverAccount;
	}

	@Override
	public double getTransactionAmount() {
		return this.transactionAmount;
	}

	
	
	public void run() {
		// TODO
		
		//threadId, amount, senderAccount.getAccountId(), receiverAccount.getAccountId()
		bank.internalTransfer(this.getSenderAccount(), this.getReceiverAccount(), this.getTransactionAmount(), this.getTransactionId());
//		System.out.println("Teste\n " + "Thread "+ this.transactionId + " Conta origem = " + senderAccount.getAccountId() + " - Conta destino = " + receiverAccount.getAccountId() + " - Valor transferido " + String.format("%.2f", this.transactionAmount) + " - Valor total: " + bank.getTotalBalance());
		
	}

}
