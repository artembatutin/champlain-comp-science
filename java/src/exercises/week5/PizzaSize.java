package exercises.week5;

public enum PizzaSize {
	
	/**
	 * Non existing size.
	 */
	UNDEFINED(0),
	
	/**
	 * The small pizza size.
	 */
	SMALL(10),
	/**
	 * The medium pizza size.
	 */
	MEDIUM(12),
	
	/**
	 * The large pizza size.
	 */
	LARGE(14);
	
	final double cost;
	
	PizzaSize(double cost) {
		this.cost = cost;
	}
	
	public double getCost() {
		return cost;
	}
	
	public static PizzaSize get(String input) {
		input = input.toLowerCase();
		for(PizzaSize size : PizzaSize.values()) {
			if(size.name().toLowerCase().equals(input))
				return size;
		}
		return UNDEFINED;
	}
	
}
