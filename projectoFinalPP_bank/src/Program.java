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
		
		
		in.close();
	

		}
		
}


