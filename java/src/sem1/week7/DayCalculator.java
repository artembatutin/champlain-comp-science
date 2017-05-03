package sem1.week7;

import java.util.Scanner;

public class DayCalculator {
	
	public static void main(String[] args) {
		//declaring variables
		Scanner input = new Scanner(System.in);
		Month month = null;
		
		//Getting the year
		System.out.println("What year are we considering?");
		int year = input.nextInt();
		
		//Getting the month.
		while(month == null) {
			System.out.println("What month are we considering? [ex: 2 for february]");
			Month mo = Month.get(input.nextInt());
			if(mo != Month.NON_EXISTANT) {
				month = mo;
				break;
			}
			System.out.println("I did not get that, can you repeat?");
		}

		//Calculating our days.
		MonthDays calc = new MonthDays(year, month);
		System.out.println("There is " + calc.getDays() + " days in " + calc.getYear() + " for the month " + calc.getMonth().name().toLowerCase() + ".");
	}
}
