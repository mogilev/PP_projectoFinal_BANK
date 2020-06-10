import java.util.Scanner;

import controllers.Bank;
import controllers.BankClass;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		int accountQty;
		double accountStartBalance;
		
		
		do {
			System.out.println("Quantas contas pretende adicionar?(Mínimo 2)");
			accountQty = in.nextInt();
		}	while (accountQty < 2);
		
		
		do {
			System.out.println("Qual o saldo inicial de cada conta?(Valor mínimo = 0.01)");
			accountStartBalance = in.nextDouble();
		}	while (accountStartBalance <= 0.01);
		
		
	/*	
		System.out.println("Quantas contas pretende adicionar?(Mínimo 2)");
	//	int accountQty = in.nextInt();
		
		
		System.out.println("Qual o saldo inicial de cada conta?");
	//	int AccountStartBalance = in.nextInt();
	*/
		
		Bank bank =  new BankClass(accountQty, accountStartBalance);
		
		int accQty = accountQty;
		Thread t = new Thread() {
			public void run() {
				bank.createTransaction(accQty);
			}
		};
		t.start();

		
		
			String input = in.nextLine();
			in.nextLine();
			if(input.isEmpty()) {
				bank.setThreadCreator(false);
		//		System.out.println("\n\n\n\n\n\n Por solicitação do utilizador, execução será terminada.\n\n\n\n\n\n");
		//		bank.setThreadCreator(false);
				while(bank.getThreadCreatorStopper()==true) {
				}

				try {
					java.util.concurrent.TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("\n\n\n\n\n\n2 Por solicitação do utilizador, execução foi interrompida.\n\n\n\n\n\n");
				
		
		
	//		in.close();
	//		System.exit(0);
		
		in.close();
		bank.executionSummary();
		
				System.exit(0);
		}
		
}}


