package sem1.week7;

/**
 * An enumeration of possible months in a year.
 */
public enum Month {
	
	NON_EXISTANT(0),
	
	JANUARY(31),
	
	FEBRUARY(28),
	
	MARCH(31),
	
	APRIL(30),
	
	MAY(31),
	
	JUNE(30),
	
	JULY(31),
	
	AUGUST(31),
	
	SEPTEMBER(30),
	
	OCTOBER(31),
	
	NOVEMBER(30),
	
	DECEMBER(31);
	
	private final int days;
	
	Month(int days) {
		this.days = days;
	}
	
	public static Month get(int index) {
		for(Month type : Month.values()) {
			if(type.ordinal() == index)
				return type;
		}
		return NON_EXISTANT;
	}
	
	/**
	 * Gets the count of days in this month.
	 * @param leap condition if this is for a leap year.
	 * @return the count of days.
	 */
	public int getDays(boolean leap) {
		return days + (this == FEBRUARY && leap ? 1 : 0);
	}
	
}
