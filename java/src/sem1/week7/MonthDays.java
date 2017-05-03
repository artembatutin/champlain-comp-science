package sem1.week7;

public class MonthDays {
	
	private final int year;
	
	private final Month month;
	
	private final boolean leap;
	
	public MonthDays(int year, Month month) {
		this.year = year;
		this.month = month;
		this.leap = isLeapYear(year);
	}
	
	public int getDays() {
		return getMonth().getDays(isLeap());
	}
	
	private static boolean isLeapYear(int year) {
		return year % 4 == 0 && (year % 400 == 0 || year % 100 != 0);
	}
	
	public int getYear() {
		return year;
	}
	
	public Month getMonth() {
		return month;
	}
	
	public boolean isLeap() {
		return leap;
	}
}
