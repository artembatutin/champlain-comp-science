package sem1.week2;

import java.util.Scanner;

/**
 * A simple scanner catch.
 */
public class ScannerCatch {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int val1 = scanner.nextInt();
		int val2 = scanner.nextInt();
		
		scanner.nextLine();//Consuming the space that was left over.
		
		String name = scanner.nextLine();
		
		System.out.println("val1: " + val1 + " - val2: " + val2 + " - name: " + name);
	}
	
}
