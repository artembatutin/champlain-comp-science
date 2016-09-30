package week6;

public class ShippingCharges {

    public static void main(String[] args) {
        int miles = 550;
        int charges = miles / 500;
        double rate = 1;
        double weight = 2;


        if(weight < 2)
            rate = 1.1;
        else if(weight < 6)
            rate = 2.2;
        else if(weight < 10)
            rate = 3.7;
        else if(weight >= 10)
            rate = 4.8;

        if(charges == 0)
            charges = 1;

        double finalPrice = rate * charges;
        System.out.println("Weight: " + weight + " miles: " + miles + " will cost you " + finalPrice);
    }
}
