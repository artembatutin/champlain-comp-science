package sem2.chap7;

import java.util.Scanner;

public class Exercise4 {

    public static void main(String[] arg) {
        int[] arr = {5, 3, 12, 20, 14, 4, 9, 18, 8};
        Scanner scanner = new Scanner(System.in);

        System.out.println("Your number?");
        int num = scanner.nextInt();

        greater(arr, num);
    }

    public static void greater(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > num) {
                System.out.println(arr[i]);
            }
        }
    }
}
