package exercises.week5;

public class Pizza {
	
	private final PizzaSize size;
	
	private PizzaTopping[] toppings = new PizzaTopping[PizzaToppingType.values().length];
	
	Pizza(PizzaSize size) {
		this.size = size;
	}
	
	public PizzaSize getSize() {
		return size;
	}
	
	public PizzaTopping[] getToppings() {
		return toppings;
	}
	
	public void addTopping(PizzaTopping topping) {
		for(int t = 0; t < toppings.length; t++) {
			if(toppings[t] == topping) {
				toppings[t].computeAmount(topping.getAmount());
				break;
			}
			if(toppings[t] == null) {
				toppings[t] = topping;
				break;
			}
		}
	}
	
	public final double cost() {
		return size.getCost() + toppings.length * 2;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[" + size.toString() + "] -");
		for(PizzaTopping top : toppings) {
			if(top == null)
				continue;
			builder.append(" with " + top.getAmount() + " " + top.getType().toString().toLowerCase() + ",");
		}
		return builder.toString();
	}
}
