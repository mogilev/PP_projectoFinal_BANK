import java.util.Scanner;

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
			System.out.println("Qual o saldo inicial de cada conta?");
			accountStartBalance = in.nextDouble();
		}	while (accountStartBalance <= 2);
		
		
	/*	
		System.out.println("Quantas contas pretende adicionar?(Mínimo 2)");
	//	int accountQty = in.nextInt();
		
		
		System.out.println("Qual o saldo inicial de cada conta?");
	//	int AccountStartBalance = in.nextInt();
	*/
		
	//	BankClass bankClass =  new BankClass(accountQty, AccountStartBalance);
		
		
		in.close();
	

		}
		
}


