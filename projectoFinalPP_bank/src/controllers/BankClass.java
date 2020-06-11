package controllers;

import java.util.List;
import models.Account;
import models.AccountClass;
import models.Transaction;
import models.TransactionClass;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class BankClass implements Bank {
	private List<Account> accountList;
	private int nrOfAccounts;
	private int accountStartBalance;
	private boolean threadCreator = true;
	private boolean threadCreatorStopper = true;
	private int totalTransactions = 0;
	private int totalTransationAmount = 0;
	private Lock lock = new ReentrantLock();
	private Condition availableFund = lock.newCondition();
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	
	public BankClass(int nrOfAccounts, int accountStartBalance) {
		this.accountList = new ArrayList<Account>();
		this.nrOfAccounts = nrOfAccounts;
		this.accountStartBalance = accountStartBalance;
		this.createAccount(this.nrOfAccounts, this.accountStartBalance);
		
	}
	

	@Override
	public int createAccount(int accountQty, int accountStartBalance) {
		for (int i = 0; i < accountQty; i++) {
			Account account = (Account) new AccountClass((i+1), accountStartBalance);
			accountList.add((Account) account);
			}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("teste - todas as Contas criadas");
		return 0;
	}

	
	@Override
	public double getTotalBalance() {
		double totalBalance = 0.0;
		for (Account account: this.getAccountList()) {
			int amount = account.getAccountBalance();
			totalBalance += amount;
		}
		totalBalance = totalBalance/100;
		return totalBalance;
	}

	@Override
	public void createTransaction(int accountQty) {
		List<Thread> transactionsList =  new ArrayList<Thread>();
				
		while(threadCreator) {
			System.out.println("Inicio de um novo lote de transações"); // print de Teste
			for (Account account: this.getAccountList()) {
				Account senderAccount = account;
				Account receiverAccount;
				
				Random randomReceiver = new Random();
				int receiverId = randomReceiver.nextInt(accountQty); // Escolhemos, aleatoriamente, a conta de destino
				receiverId +=1; // Escolhemos, aleatoriamente, o Id da conta de destino
				receiverAccount = this.findAccount(receiverId); 
				
				Random randomAmount = new Random();	
				int value = randomAmount.nextInt(this.accountStartBalance);
				value += 1;
				transactionsList.add(new Thread(new TransactionClass(this, senderAccount, receiverAccount, value)));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//bloco de teste
			
	/*		
			try {
				System.out.println("Fim de um lote de transações");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			//fim do bloco de teste
			
		}
		threadCreatorStopper = false;
		System.out.println("threadCreatorStopper passou a falso");
		return;
	}

	
	
	@Override
	public boolean accountHasFunds(int accountId, int amount) {
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
	public void transferReceipt(double transferAmount, int senderAccountId, double senderBalance, int receiverAccountId, double receiverBalance) {
		transferAmount = transferAmount/100;
		senderBalance = senderBalance/100;
		receiverBalance = receiverBalance/100;
		
		System.out.println("Quantidade transferida: " +  formatter.format(transferAmount) + " --- de conta nº " + senderAccountId + "(" + formatter.format(senderBalance) + ") --- para conta destino nº " + receiverAccountId + "(" + formatter.format(receiverBalance) + ") --- Saldo final total: " + formatter.format(this.getTotalBalance()));
		
	}


	@Override
	public List<Account> getAccountList() {
		return this.accountList;
	}


	@Override
	public Account findAccount(int accountId) {
		for (Account account: this.getAccountList()) {
			if (account.getAccountId() == accountId) {
				return account;
			}
		}
		return null;
	}


	@Override
	public void setThreadCreator(boolean permission) {
		this.threadCreator = permission;
	}


	@Override
	public boolean getThreadCreatorStopper() {
		return this.threadCreatorStopper;
	}


	@Override
	public void executionSummary() {
		System.out.println("************************************************************");
		System.out.println("**      Devido à execução ter sido interrompida,          **");
		System.out.println("** algumas transferências poderão não ter sido concluídas **");
		System.out.println("************************************************************");
		
		System.out.println("Foram criadas " + this.nrOfAccounts + " contas, cada uma com " + formatter.format(((double)this.accountStartBalance/100)) + " euros.");
		System.out.println("Valor inicial em caixa: " + formatter.format((this.nrOfAccounts * ((double)this.accountStartBalance/100))) + " euros.");

		Collections.sort(accountList,
				new Comparator<Account>() {
				public int compare(Account a1, Account a2) {
					return Integer.compare(a2.getAccountBalance(), a1.getAccountBalance());
				}
		});
		Account maxAccount = this.getAccountList().get(0);
		Account maxAccount2 = this.getAccountList().get(1);
		
		Account minAccount = this.getAccountList().get(getAccountList().size()-1);
		Account minAccount2 = this.getAccountList().get(getAccountList().size()-2);
		
		System.out.println("Total de transacções realizadas: "+ this.totalTransactions);
		System.out.println("Valor total transacionado: " + ((double)this.totalTransationAmount/100));
		System.out.println("Maior saldo:      Conta nº " + maxAccount.getAccountId() + " -- " + formatter.format(((double)maxAccount.getAccountBalance()/100)));
		System.out.println("2º maior saldo:   Conta nº " + maxAccount2.getAccountId() + " -- " + formatter.format(((double)maxAccount2.getAccountBalance()/100)));
		System.out.println("Menor saldo:      Conta nº " + minAccount.getAccountId() + " -- " + formatter.format(((double)minAccount.getAccountBalance()/100)));
		System.out.println("2º menor saldo:   Conta nº " + minAccount2.getAccountId() + " -- " + formatter.format(((double)minAccount2.getAccountBalance()/100)));
		System.out.println("Valor total em caixa: " + ((double)this.getTotalBalance()));
		System.out.println("Diferença do valor em caixa(em relação ao inicio): " +  formatter.format(((this.nrOfAccounts * ((double)this.accountStartBalance/100))-(this.getTotalBalance()))));
	}


	@Override
	public void internalTransfer(Account senderAccount, Account receiverAccount, int amount) {

		lock.lock();    // 1
	/*
		senderAccount.accountTransferOut(amount);
        receiverAccount.accountTransferIn(amount);
        this.transferReceipt(threadId, amount, senderAccount.getAccountId(), receiverAccount.getAccountId());
        this.totalTransactions ++; // em duplicado com bloco mais abaixo, apagar se descomentar o de baixo!!
    	this.totalTransationAmount += amount;// em duplicado com bloco mais abaixo, apagar se descomentar o de baixo!!
       */
       
		try {
            while (!this.accountHasFunds(senderAccount.getAccountId(), amount)) {
        //    System.out.println("Transferência não realizada porque a conta " + senderAccount.getAccountId() + " tem apenas " + ((double)senderAccount.getAccountBalance()/100) + " - transferência agendada de " + ((double)amount/100)); //print de teste
                availableFund.await(); // Aguarda caso não tenha saldo suficiente
            } 
            senderAccount.accountTransferOut(amount);
            receiverAccount.accountTransferIn(amount);
            this.transferReceipt((double)amount, senderAccount.getAccountId(), (double)senderAccount.getAccountBalance(), receiverAccount.getAccountId(), (double)receiverAccount.getAccountBalance());
        	this.totalTransactions ++;
        	this.totalTransationAmount += amount;
            
          //  this.transferReceipt(threadId, amount, senderAccount.getAccountId(), receiverAccount.getAccountId());

            availableFund.signalAll(); // Sinaliza que efectuou a transferência com sucesso, para que as threads que aguardam saldo possam voltar a tentar

        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
		finally {
            lock.unlock();
        }   
	//	lock.unlock(); 

	}

}
