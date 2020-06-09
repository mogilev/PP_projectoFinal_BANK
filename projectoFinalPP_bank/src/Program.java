import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Quantas contas pretende adicionar?(Mínimo 2)");
		int accountQty = in.nextInt();
		
		System.out.println("Qual o saldo inicial de cada conta?");
		int AccountStartBalance = in.nextInt();
		
	//	BankClass bankClass =  new BankClass(accountQty, AccountStartBalance);
		
		
		in.close();
	

		}
		
}


