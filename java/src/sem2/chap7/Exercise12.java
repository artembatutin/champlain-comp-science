package sem2.chap7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise12 {

    public static void main(String[] arg) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./misc/chap7numbers.txt"));
        int[] arr = new int[12];

        int count = 0;
       while(scanner.hasNext()) {
            arr[count] = scanner.nextInt();
            count++;
        }

        System.out.println("total: " + Exercise11.getTotal(arr));
        System.out.println("average: " + Exercise11.getAverage(arr));
        System.out.println("high: " + Exercise11.getHighest(arr));
        System.out.println("low: " + Exercise11.getLowest(arr));

    }

}
