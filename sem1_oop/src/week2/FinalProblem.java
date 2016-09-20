package week2;

/**
 * The final project made wen. aug. 31 at 4:30pm.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class FinalProblem {
	
	/**
	 * The commission rate.
	 */
	private static final double COMMISSION_RATE = 0.02;
	
	/**
	 * The 18th problem of the second chapter.
	 * @param args
	 */
	public static void main(String[] args) {
		
		/* The buying shares. */
		int sharesCount = 1000;
		double sharesPrice = 32.87;
		
		//The calculation of our buying method.
		double paidAmount = sharesCount * sharesPrice;
		double firstCommission = paidAmount * COMMISSION_RATE;
		
		//Outcome of our calculations.
		System.out.println("Joe paid " + paidAmount + "$ for the shares, the brooker received " + firstCommission + "$.");
		
		
		/* The sales shares. */
		//the shares are being sold 33.92$ each.
		sharesPrice = 33.92;
		
		//The calculation of our selling method.
		double soldAmount = sharesCount * sharesPrice;
		double secondCommission = soldAmount * COMMISSION_RATE;
		
		//Outcome of our calculations.
		System.out.println("Joe sold the shares for " + soldAmount + "$, the stockbroker received " + secondCommission + "$.");
		
		//Final calculations for profits.
		double fullCommission = firstCommission + secondCommission;
		double profit = soldAmount - paidAmount - fullCommission;
		
		//The result
		System.out.println("Joe's stockbroker received " + fullCommission + "$ in total.");
		if(profit > 0) {
			System.out.println("Joe made " + profit + "$ in profit.");
		} else {
			System.out.println("Joe lost " + profit + "$.");
		}
		
	}
	
}
