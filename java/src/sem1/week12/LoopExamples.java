package sem1.week12;

public class LoopExamples {
	
	public static void main(String[] args) {
		
		int o = 1;
		
		if(o == 0) {
			
			for(int l = 0; l < 10; l++) {
				for(int i = 0; i < 15; i++) {
					System.out.print("#");
				}
				System.out.println();
			}
			
		} else if(o == 1) {
			
			for(int l = 7; l >= 1; l--) {
				for(int i = 0; i < l; i++) {
					System.out.print("*");
				}
				System.out.println();
			}
			
		} else {
			
			for(int s = 0; s < 6; s++) {
				System.out.print("#");
				for(int e = 0; e < s; e++) {
					System.out.print(" ");
				}
				System.out.print("#\n");
			}
			
		}
	}
}
