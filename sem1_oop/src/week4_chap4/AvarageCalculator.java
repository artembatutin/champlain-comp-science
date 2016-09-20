package week4_chap4;

import java.util.Scanner;

public class AvarageCalculator {
	
	public static void main(String[] args) {
		//variables.
		Scanner input = new Scanner(System.in);
		Score[] scores = new Score[3];
		
		//Getting the grades.
		System.out.println("Please type your three grades.");
		scores[0] = new Score(input.nextInt());
		System.out.println("Second one?");
		scores[1] = new Score(input.nextInt());
		System.out.println("Last one?");
		scores[2] = new Score(input.nextInt());
		
		Score av = getAverage(scores);
		System.out.println();
		System.out.println("Your average score is: " + av.toString());
	}
	
	private static Score getAverage(Score... scores) {
		int sum = 0;
		System.out.println("Your three grades are:");
		for(Score s : scores) {
			sum += s.getGrade();
			System.out.println(s.toString());
		}
		sum /= scores.length;
		return new Score(sum);
	}
}
