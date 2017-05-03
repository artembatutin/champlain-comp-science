package sem2.fproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

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

    public static void sort(LinkedList list) {
        //sorting birds
        list.sort();
        System.out.println("Sorted:");
        list.print();
    }

    public static void exclude(LinkedList<String> list) throws FileNotFoundException {
        //excluding birds
        Scanner ex = new Scanner(new File("./data/birds2.txt"));
        while(ex.hasNext()) {
            String bird = ex.nextLine();
            LinkedList.Node n = list.search(bird);
            if(n != null)
                n.decreaseFreq();
        }
        System.out.println("Excluded:");
        list.print();
    }
}
