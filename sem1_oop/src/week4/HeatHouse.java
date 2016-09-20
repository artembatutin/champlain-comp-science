package week4;

import java.util.Scanner;

public class HeatHouse {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Getting input.
		System.out.println("What is the temperature in Fahrenheit");
		double temp = scanner.nextDouble();
		Temperature conversion = new Temperature(temp);
		System.out.println("Fahrenheit: " + conversion.getFahrenheit() + " - Celsius: " + conversion.getCelsius() + " - Kelvin: " + conversion.getKelvin());
	}
}
