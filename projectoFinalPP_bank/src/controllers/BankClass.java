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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class BankClass implements Bank {
	private List<Account> accountList;
	private int nrOfAccounts;
	private double accountStartBalance;
	private boolean threadCreator = true;
	private boolean threadCreatorStopper = true;
	private int totalTransactions = 0;
	private double totalTransationAmount = 0.00;
	private Lock lock = new ReentrantLock();
	private Condition availableFund = lock.newCondition();
	
	
	public BankClass(int nrOfAccounts, double accountStartBalance) {
		this.accountList = new ArrayList<Account>();
		this.nrOfAccounts = nrOfAccounts;
		this.accountStartBalance = accountStartBalance;
		this.createAccount(this.nrOfAccounts, this.accountStartBalance);
	//	this.createTransaction(this.nrOfAccounts);
		
		
		
	}
	

	@Override
	public int createAccount(int accountQty, double accountStartBalance) {
		for (int i = 0; i < accountQty; i++) {
			Account account = (Account) new AccountClass((i+1), accountStartBalance);
			accountList.add((Account) account);
			}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("teste - todas as Contas criadas");
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
		int counter = 0; // utilizado para indicar ID a cada thread criada
		
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
				double value = 0.01 + (this.accountStartBalance - 0.01) * randomAmount.nextDouble(); // Geramos quantidade aleatória para ser transferida, entre 0.01 e o valor máximo definido inicialmente pelo utilizador.
				value = Math.floor(value*100) / 100; // Certificamo-nos que o valor da transferência tem apenas 2 casas decimais 
				counter++;
				transactionsList.add(new Thread(new TransactionClass(this, counter, senderAccount, receiverAccount, value)));
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//fim do bloco de teste
			
		}
		threadCreatorStopper = false;
		System.out.println("threadCreatorStopper passou a falso");
		return;
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
	//	System.out.println("\n Thread nº " + transactionId); //ver se faz sentido
		System.out.println("Quantidade transferida: " +  transferAmount + " - de conta origem nº " + senderAccountId + " - para conta destino nº " + receiverAccountId + " - Saldo final total: " + this.getTotalBalance());
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
		
		System.out.println("Foram criadas " + this.nrOfAccounts + " contas, cada uma com " + this.accountStartBalance + " euros.");

		Collections.sort(accountList,
				new Comparator<Account>() {
				public int compare(Account a1, Account a2) {
					return Double.compare(a1.getAccountBalance(), a2.getAccountBalance());
				}
		});
		Account maxAccount = this.getAccountList().get(0);
		Account maxAccount2 = this.getAccountList().get(1);
		
		Account minAccount = this.getAccountList().get(getAccountList().size()-1);
		Account minAccount2 = this.getAccountList().get(getAccountList().size()-2);
		
		System.out.println("Total de transacções realizadas: "+ this.totalTransactions);
		System.out.println("Valor total transacionado: "+ String.format("%.2f", this.totalTransationAmount));
		System.out.println("Conta com menor saldo: Id " + maxAccount.getAccountId()+ ", com " + String.format("%.2f",maxAccount.getAccountBalance()));
		System.out.println("Conta com 2º menor saldo: Id "+ + maxAccount2.getAccountId()+ ", com " + String.format("%.2f",maxAccount2.getAccountBalance()));
		System.out.println("Conta com maior saldo: Id " + minAccount.getAccountId()+ ", com " + String.format("%.2f",minAccount.getAccountBalance()));
		System.out.println("Conta com 2º maior saldo: Id " + minAccount2.getAccountId()+ ", com " + String.format("%.2f",minAccount2.getAccountBalance()));
	}


	@Override
	public void internalTransfer(Account senderAccount, Account receiverAccount, double amount, int threadId) {

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
            System.out.println("Transferência " + "não realizada porque conta " + senderAccount.getAccountId() + " tem apenas " + senderAccount.getAccountBalance() + " - transferência agendada de " + amount);
                availableFund.await();
            } 
            senderAccount.accountTransferOut(amount);
            receiverAccount.accountTransferIn(amount);
            this.transferReceipt(threadId, amount, senderAccount.getAccountId(), receiverAccount.getAccountId());
        	this.totalTransactions ++;
        	this.totalTransationAmount += amount;
            
          //  this.transferReceipt(threadId, amount, senderAccount.getAccountId(), receiverAccount.getAccountId());

            	availableFund.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
		finally {
            lock.unlock();
        }   
	//	lock.unlock(); 
		// TODO Auto-generated method stub
    //	lock.unlock();
	}

}
