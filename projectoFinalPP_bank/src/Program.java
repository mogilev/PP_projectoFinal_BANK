/*
 *	UAL - Paradigmas de Programação - Projecto Final 2019/2020
 *	Grupo 1 - Bruno Firmino(30002416), Miguel Alves(30002304)
 *	Junho 2020
 *
 *Para utilizar o programa basta fazer run e seguir as instruções indicadas na consola.
 *Tal como indicado no enunciado, o programa correrá infinitamente.
 *No entanto, caso o utilizador queira, poderá em qualquer altura carregar em 'enter' e o programa terminará 
 *pouco tempo depois(poderá ser necessário aguardar alguns momentos) e aparecerá na consola um quadro com um resumo
 *do que foi executado.
 *
 */


import java.util.Scanner;

import controllers.Bank;
import controllers.BankClass;

public class Program {

	public static void main(String[] args) {
		
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
				System.out.println("\n\n\n\n\n\n Por solicitação do utilizador, execução será terminada.\n\n\n\n\n\n");
		//		bank.setThreadCreator(false);
				while(bank.getThreadCreatorStopper()==true) {
			//	}

				try {
					java.util.concurrent.TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				System.out.println("\n\n\n\n\n\n Por solicitação do utilizador, execução foi interrompida.\n\n\n\n\n\n");
				
		
		in.close();
		bank.executionSummary();
		
				System.exit(0);
		}
		
}}


