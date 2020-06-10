package controllers;

import java.util.List;
import models.Account;
import models.AccountClass;
import models.Transaction;
import models.TransactionClass;

import java.util.Random;

import java.util.ArrayList;

public class BankClass implements Bank{
	private List<Account> accountList;
	private int nrOfAccounts;
	private double accountStartBalance;
	
	
	
	public BankClass(int nrOfAccounts, double accountStartBalance) {
		this.accountList = new ArrayList<Account>();
		this.nrOfAccounts = nrOfAccounts;
		this.accountStartBalance = accountStartBalance;
		this.createAccount(this.nrOfAccounts, this.accountStartBalance);
		
	}
	

	@Override
	public int createAccount(int accountQty, double accountStartBalance) {
		for (int i = 0; i < accountQty; i++) {
			Account account = (Account) new AccountClass((i+1), accountStartBalance);
			accountList.add((Account) account);
			}
		return 0;
	}

	
	@Override
	public double getTotalBalance() {
		double totalBalance = 0.0;
		for (Account account: this.getAccountList()) {
			double amount = account.getAccountBalance();
			totalBalance += amount;
		}
		return totalBalance;
	}

	@Override
	public void createTransaction(int accountQty) {
		// TODO Auto-generated method stub
		List<Thread> transactionsList =  new ArrayList<Thread>();
		int i = 0; // utilizado para indicar ID a cada thread criada
		
		while(true) {
			for (Account account: this.getAccountList()) {
				Account senderAccount = account;
				Account receiverAccount;
				
				Random randomReceiver = new Random();
				int receiverId = randomReceiver.nextInt(accountQty); // Escolhemos, aleatoriamente, a conta de destino
				receiverId +=1; // Escolhemos, aleatoriamente, o Id da conta de destino
				receiverAccount = this.findAccount(receiverId); 
				
				Random randomAmount = new Random();		
				double value = 0.01 + (this.accountStartBalance - 0.01) * randomAmount.nextDouble(); // Geramos quantidade aleatória para ser transferida, entre 0.01 e o valor máximo definido inicialmente pelo utilizador.
				value = Math.floor(value*100) / 100; // Certificamo-nos que o valor da transferência tem apenas 2 casas decimais 
				
				transactionsList.add(new Thread(new TransactionClass(this, i+1, senderAccount, receiverAccount, value)));
				i++;
			}
			
			
		}
	}

	
	
	
	
	@Override
	public boolean accountHasFunds(int accountId, double amount) {
		for (Account account : this.getAccountList()) {
			if (account.getAccountId() == accountId) {
				if (account.getAccountBalance() >= amount) {
					return true;
				}
			}
		}
		return false;
	}

	
	@Override
	public void transferReceipt(int transactionId, double transferAmount, int senderAccountId, int receiverAccountId) {
		System.out.println("\n Thread nº " + transactionId);
		System.out.println("Quantidade transferida: " + String.format("%.2f", transferAmount ) + " - de conta origem nº " + String.format("%.2f", senderAccountId) + " - para conta destino nº " + String.format("%.2f", receiverAccountId) + " - Saldo final total: " + String.format("%.2f", this.getTotalBalance()));
	}


	@Override
	public List<Account> getAccountList() {
		return this.accountList;
	}


	@Override
	public Account findAccount(int accountId) {
		// TODO Auto-generated method stub
		for (Account account: this.getAccountList()) {
			if (account.getAccountId() == accountId) {
				return account;
			}
		}
		return null;
	}

}
