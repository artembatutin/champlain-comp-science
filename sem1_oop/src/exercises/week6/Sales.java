package exercises.week6;

import java.util.Scanner;

public class Sales {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Units?");
		int units = input.nextInt();
		double discount = 0;

		if(units >= 100) {
			discount = 50;
		} else if(units >= 50) {
			discount = 40;
		} else if(units >= 20) {
			discount = 30;
		} else if(units >= 10) {
			discount = 20;
		}

		double total = units * 99.00;
		double discounted = (total / 100) * discount;
		double finalPrice = total - discounted;

		System.out.println("total price: " + total);
		System.out.println("discount: " + discount + "%");
		System.out.println("discounted: " + discounted);
		System.out.println("price: " + finalPrice);

	}

}
