/**
 * GoldbachConject.java
 *  takes as input an even positive integer greater than 2
and writes it as a sum of two primes
 * @author saffywinton
 * @version05012018
 */
import java.util.Scanner;

public class GoldbachConject {
	
//collects user input and checks conditions
	
	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
		System.out.println("Please enter an even integer greater than 2");
		int input = kbd.nextInt();
		while (input <= 2|| input%2 != 0) {
			if (input <= 2) {
					System.out.println("Integer not greater than 2. Please enter"
							+ " another integer");
			input = kbd.nextInt();
		}else if (input%2 != 0) {
			System.out.println("Integer not even. Please enter"
					+ " another integer");
	input = kbd.nextInt();
		}else if (input%2 != 0 && input <= 2) {
			System.out.println("Integer not even and not gretaer than 2. Please enter"
					+ " another integer");
	input = kbd.nextInt();
		}
	}
		checkSum(input, 2);
		kbd.close();
	}
	
	//checks if input is a prime number and returns boolean value
	public static boolean isPrime (int n) {
		boolean primeflag = true;
		int i= 2; 
		
		while (i< n) {
			if (n % i == 0) {
				primeflag = false;
				break;
			} 
			i++;
		}
		return primeflag;
	}	

	//takes user input and checks sums of primes to find two primes that sum to input
	public static void checkSum (int input, int n) {
		if (isPrime(n) && isPrime(input-n)) {
			System.out.println(input + " can be written as the sum of the prime numbers " 
		+ n + " and " + (input-n) + ".");
			
		}
		else {
			n++;
			checkSum(input, n);
		}

	}
	}

