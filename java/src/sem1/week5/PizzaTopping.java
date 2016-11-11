package sem1.week5;

public class PizzaTopping {
	
	private int amount;
	
	private final PizzaToppingType type;
	
	PizzaTopping(PizzaToppingType type, int amount) {
		this.type = type;
		this.amount = amount;
	}
	
	public void computeAmount(int amount) {
		this.amount += amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public PizzaToppingType getType() {
		return type;
	}
}
