package sem2.fproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * {@link LinkedList} controller test class.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class Main {
	
	/**
	 * A prompt based launch.
	 * @param a arguments, not used.
	 */
	public static void main(String[] a) {
		LinkedList<String> l = new LinkedList<>();
		try {
			add(l);
			sort(l);
			exclude(l);
		} catch(FileNotFoundException | InputMismatchException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adding birds to the {@link LinkedList}.
	 * @param list list to add to.
	 * @throws FileNotFoundException file may not exist.
	 */
	public static void add(LinkedList<String> list) throws FileNotFoundException {
		//reading birds
		Scanner in = new Scanner(new File("./data/birds1.txt"));
		while(in.hasNext()) {
			String bird = in.nextLine();
			LinkedList.Node n = list.search(bird);
			if(n != null) {
				n.increaseFreq();
			} else {
				list.addLast(bird);
			}
		}
		System.out.println("Listed:");
		list.print();
	}
	
	/**
	 * Sorts the {@link LinkedList}.
	 * @param list list to be sorted.
	 */
	public static void sort(LinkedList list) {
		//sorting birds
		list.sort();
		System.out.println("Sorted:");
		list.print();
	}
	
	/**
	 * Excludes some birds from {@link LinkedList}.
	 * @param list list to exclude from.
	 * @throws FileNotFoundException file may not exist.
	 */
	public static void exclude(LinkedList<String> list) throws FileNotFoundException {
		//excluding birds
		Scanner ex = new Scanner(new File("./data/birds2.txt"));
		while(ex.hasNext()) {
			String bird = ex.nextLine();
			list.remove(bird);
		}
		System.out.println("Excluded:");
		list.print();
	}
}
