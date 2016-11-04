package exercises.week6;

import java.util.Scanner;

public class TimeCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Seconds por favor?");
        double seconds = input.nextDouble();

        if(seconds >= 86400) {
            double day = seconds / 86400;
            double hours = (seconds - day * 86400) / 3600;
            double minutes = (seconds - day * 86400 - hours * 3600) / 60;
            seconds = seconds - day * 86400 - hours * 3600 - minutes * 60;
            System.out.println(day + " days, " + hours + " hours, " + minutes + " minutes and " + seconds + " seconds.");
        } else if(seconds >= 3600) {
            double hours = seconds / 3600;
            double minutes = (seconds - hours * 3600) / 60;
            seconds = seconds - hours * 3600 - minutes * 60;
            System.out.println(hours + " hours, " + minutes + " minutes and " + seconds + " seconds.");
        } else if(seconds >= 60) {
            double minutes = seconds / 60;
            seconds = seconds - minutes * 60;
            System.out.println(minutes + " minutes and " + seconds + " seconds.");
        } else {
            System.out.println(seconds + " minutes.");
        }
    }
}
