package week3;

import java.util.Scanner;

class Circle {
	
	private static final double PI = 3.14159;
	
	private double radius;
	
	Circle(Scanner scanner) {
		System.out.println("Whats our radius?");
		this.radius = scanner.nextDouble();
	}
	
	double getRadius() {
		return radius;
	}
	
	double getArea() {
		return radius * radius * PI;
	}
	
	double getCirconference() {
		return 2 * radius * PI;
	}
}
