package sem2.chap7;

public class TestArray {
	
	public static void main(String[] a) {
		System.out.println(scoresIncreasing(new int[] {1, 1, 2, 4, 4, 7}));//true
		System.out.println(scoresIncreasing(new int[] {1, 3, 2}));//false
	}
	
	public static boolean scoresIncreasing(int[] scores) {
		for(int i = 1; i < scores.length; i++) {
			if (scores[i-1]>scores[i])
				return false;
		}
		return true;
	}
}
