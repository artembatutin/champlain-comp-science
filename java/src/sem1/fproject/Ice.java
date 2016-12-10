package sem1.fproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Calculates the results of the skating competition.
 */
public class Ice {
	
	/**
	 * The path to our skater's class.
	 */
	private static final String PATH = "./data/skaters.txt";
	
	/**
	 * The main method of our class.
	 * @param a starting arguments array.
	 * @throws FileNotFoundException
	 */
	public static void main(String[] a) throws FileNotFoundException {
		//Initializing variables.
		File file = new File(PATH);
		Scanner in = new Scanner(file);
		Skaters[] sk = new Skaters[lines(PATH) / 5];
		
		//Reading data.
		for(int i = 0; i < sk.length; i++) {
			sk[i] = new Skaters(in);
			System.out.println("read: " + sk[i].toString());
		}
		
		//Sorting array.
		for(int i = 0; i < sk.length; i++) {
			for(int s = 0; s < sk.length; s++) {
				if(sk[i].getAverage() > sk[s].getAverage()) {
					Skaters temp = sk[s].copy();
					sk[s] = sk[i].copy();
					sk[i] = temp;
				}
			}
		}
		
		//Displaying sorted array.
		System.out.println("Sorted:");
		for(Skaters s : sk) {
			System.out.println(s.toString());
		}
		
		//closing scanner even if the program will exit anyways.
		in.close();
	}
	
	/**
	 * Counts how many lines there is in a defined file path.
	 * @param path the path of the file
	 * @return number of lines there is.
	 * @throws FileNotFoundException file not found.
	 */
	private static int lines(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner in = new Scanner(file);
		int l = 0;
		while(in.hasNext()) {
			in.nextLine();
			l++;
		}
		return l;
	}
}
