package sem1.week2;

/**
 * The paragram looped exercise made wen. aug. 31 at 5;00pm.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class ParagramLooped {
	
	/**
	 * The 5th problem of the second chapter using the loop approach.
	 */
	public static void main(String[] args) {
		int blanks = 3;
		//Looping throught lines.
		for(int l = 0; l < 7; l++) {
			
			//Adding the blank spaces.
			for(int e = Math.abs(blanks); e > 0; e--) {
				System.out.print(" ");
			}
			
			//Adding the asterisks.
			for(int a = 0; a > Math.abs(blanks) * 2 - 7; a--) {
				System.out.print("*");
			}
			
			//Decrease the blanks value and goes to another line.
			blanks--;
			System.out.println();
			
		}
	}
	
}
