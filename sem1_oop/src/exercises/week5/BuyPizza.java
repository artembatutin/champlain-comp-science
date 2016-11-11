package exercises.week5;

import java.util.Scanner;

public class BuyPizza {
	
	private static final String TOPPINGS;
	
	static {
		StringBuffer builder = new StringBuffer();
		builder.append("[");
		for(PizzaToppingType type : PizzaToppingType.values()) {
			builder.append(type.toString().toLowerCase() + ",");
		}
		builder.append("]");
		TOPPINGS = builder.toString();
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Pizza pizza = null;
		PizzaSize size = null;
		
		//Getting the size.
		while(size == null) {
			System.out.println("What size the pizza you want? [small, medium, large]");
			PizzaSize size2 = PizzaSize.get(input.nextLine());
			if(size2 != PizzaSize.UNDEFINED) {
				size = size2;
				break;
			}
			System.out.println("I did not get that, can you repeat?");
		}
		
		pizza = new Pizza(size);
		
		while(true) {
			while(true) {
				System.out.println("What topping you would like to add?");
				System.out.println("The possible toppings are: " + TOPPINGS);
				PizzaToppingType top = PizzaToppingType.get(input.nextLine());
				if(top != PizzaToppingType.UNDEFINED) {
					System.out.println("How many " + top.name().toLowerCase() + " toppings?");
					int amount = input.nextInt();
					input.nextLine();
					pizza.addTopping(new PizzaTopping(top, amount));
					break;
				}
				System.out.println("I did not get that, can you repeat?");
			}
			System.out.println("Any other topping? [yes, no]");
			String answer = input.nextLine();
			if(!answer.toLowerCase().equals("yes")) {
				break;
			}
		}
		
		System.out.println();
		System.out.println("Baking pizza... please wait.");
		
		System.out.println("Your pizza: " + pizza.toString());
		System.out.println("Will cost you " + pizza.cost());
	}
}
