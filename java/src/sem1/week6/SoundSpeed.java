package sem1.week6;

import java.util.Scanner;

public class SoundSpeed {

	public static void main(String[] args) {
		//start
		Scanner input = new Scanner(System.in);

		//Getting and creating the speed.
		System.out.println("What would be the distance in feets?");
		double distance = input.nextDouble();
		Sound sound = new Sound(distance);

		SoundMedium med = null;

		while(true) {
			//Getting the medium and calculating
			System.out.println("What would be the medium? [air, water, steel]");
			med = SoundMedium.get(input.nextLine());
			if(med != SoundMedium.UNDEFINED) {
				break;
			}
			System.out.println("I did not get that, can you repeat.");
		}

		System.out.println("The medium distance would be: " + sound.getDistance(med));

	}

}
