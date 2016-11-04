package exercises.week5;

/**
 * An enumeration of possible pizza topping types.
 */
public enum PizzaToppingType {
	
	UNDEFINED,
	
	CHEESE,
	
	PEPPERONI,
	
	HAM,
	
	CHICKEN,
	
	TOMATOES,
	
	GREEN_PEPPER,
	
	ANCHOIVES;
	
	public static PizzaToppingType get(String input) {
		input = input.toLowerCase();
		for(PizzaToppingType type : PizzaToppingType.values()) {
			if(type.name().toLowerCase().equals(input))
				return type;
		}
		return UNDEFINED;
	}
}
