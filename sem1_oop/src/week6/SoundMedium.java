package week6;

/**
 * An enumeration of sound mediums types.
 */
public enum SoundMedium {
	
	UNDEFINED(0),
	
	AIR(1100),

	WATER(4900),

	STEEL(16400);

	private final int feet;

	SoundMedium(int feet) {
		this.feet = feet;
	}
	
	public static SoundMedium get(String input) {
		input = input.toLowerCase();
		for(SoundMedium type : SoundMedium.values()) {
			if(type.name().toLowerCase().equals(input))
				return type;
		}
		return UNDEFINED;
	}

	public int getFeet() {
		return feet;
	}
}
