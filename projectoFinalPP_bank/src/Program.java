/*
 *	UAL - Paradigmas de Programa��o - Projecto Final 2019/2020
 *	Grupo 1 - Bruno Firmino(30002416), Miguel Alves(30002304)
 *	Junho 2020
 *
 *Para utilizar o programa basta fazer run e seguir as instru��es indicadas na consola.
 *Tal como indicado no enunciado, o programa correr� infinitamente.
 *No entanto, caso o utilizador queira, poder� em qualquer altura carregar em 'enter' e o programa terminar� 
 *pouco tempo depois(poder� ser necess�rio aguardar alguns momentos) e aparecer� na consola um quadro com um resumo
 *do que foi executado.
 *Mais informa��es acerca desta implementa��o no relat�rio do projecto
 *
 */


import java.util.Scanner;

import controllers.Bank;
import controllers.BankClass;

public class Program {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int accountQty;
		double dAccountStartBalance;
		
		do {
			System.out.println("Quantas contas pretende adicionar?(M�nimo 2)");
			accountQty = in.nextInt();
		}	while (accountQty < 2);
		
		
		do {
			System.out.println("Qual o saldo inicial de cada conta?(Valor m�nimo = 0.01)");
			dAccountStartBalance = in.nextDouble();
		}	while (dAccountStartBalance <= 0.01);
		
		dAccountStartBalance = dAccountStartBalance*100;
		int accountStartBalance = (int) dAccountStartBalance;
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
		if(input.isEmpty()) { // Se o utilizador decide interromper o programa, carregando no 'Enter'
			bank.setThreadCreator(false);
			System.out.println("\n\n\n Por solicita��o do utilizador, execu��o ser� terminada.\n\n\n");

			while(bank.getThreadCreatorStopper()==true) {

				try {
					java.util.concurrent.TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
					}
			}
				
			System.out.println("\n\n\n\n\n\n Por solicita��o do utilizador, execu��o foi interrompida.\n\n***   Por favor aguarde pelo quadro resumo.   ***\n\n\n\n\n");
				
			in.close();
			bank.executionSummary();
			System.exit(0);	
		}	
	}
	
	
}


