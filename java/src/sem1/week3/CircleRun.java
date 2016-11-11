package sem1.week3;

import java.util.Scanner;

public class CircleRun {
	
	public static void main(String[] s) {
		Scanner scanner = new Scanner(System.in);
		if(true) {
			System.out.println("How many pennies?");
			int pennies = scanner.nextInt();
			int dollars = pennies / 100;
			int left = (pennies - (dollars * 100));
			System.out.println("There is " + dollars + " dollars and " + left + " pennies.");
		} else {
			Circle cirlce = new Circle(scanner);
			System.out.println("From radius: " + cirlce.getRadius());
			System.out.println("We get area: " + cirlce.getArea());
			System.out.println("We get circumference: " + cirlce.getCirconference());
		}
	}
}
