package sem2.chap7;

/**
 * Lets say the class is called ArrayOperations...
 */
public class Exercise11 {

    public static void main(String[] arg) {
        int[] arr = { 3, 6, 12, 18, 12, 4, 8, 1, 19 };
        
        System.out.println("total: " + getTotal(arr));
        System.out.println("average: " + getAverage(arr));
        System.out.println("high: " + getHighest(arr));
        System.out.println("low: " + getLowest(arr));
    }

    public static int getTotal(int[] arr) {
        int res = 0;
        for(int i : arr)
            res += i;
        return res;
    }

    public static float getTotal(float[] arr) {
        float res = 0;
        for(float i : arr)
            res += i;
        return res;
    }

    public static double getTotal(double[] arr) {
        double res = 0;
        for(double i : arr)
            res += i;
        return res;
    }

    public static long getTotal(long[] arr) {
        long res = 0;
        for(long i : arr)
            res += i;
        return res;
    }

    public static int getAverage(int[] arr) {
        int res = 0;
        for(int i : arr)
            res += i;
        res /= arr.length;
        return res;
    }

    public static float getAverage(float[] arr) {
        float res = 0;
        for(float i : arr)
            res += i;
        res /= arr.length;
        return res;
    }

    public static double getAverage(double[] arr) {
        double res = 0;
        for(double i : arr)
            res += i;
        res /= arr.length;
        return res;
    }

    public static long getAverage(long[] arr) {
        int res = 0;
        for(long i : arr)
            res += i;
        res /= arr.length;
        return res;
    }

    public static int getHighest(int[] arr) {
        int res = arr[0];
        for(int i : arr)
            res = i > res ? i : res;
        return res;
    }

    public static float getHighest(float[] arr) {
        float res = arr[0];
        for(float i : arr)
            res = i > res ? i : res;
        return res;
    }

    public static double getHighest(double[] arr) {
        double res = arr[0];
        for(double i : arr)
            res = i > res ? i : res;
        return res;
    }

    public static long getHighest(long[] arr) {
        long res = arr[0];
        for(long i : arr)
            res = i > res ? i : res;
        return res;
    }

    public static int getLowest(int[] arr) {
        int res = arr[0];
        for(int i : arr)
            res = i < res ? i : res;
        return res;
    }

    public static float getLowest(float[] arr) {
        float res = arr[0];
        for(float i : arr)
            res = i < res ? i : res;
        return res;
    }

    public static double getLowest(double[] arr) {
        double res = arr[0];
        for(double i : arr)
            res = i < res ? i : res;
        return res;
    }

    public static long getLowest(long[] arr) {
        long res = arr[0];
        for(long i : arr)
            res = i < res ? i : res;
        return res;
    }
}
