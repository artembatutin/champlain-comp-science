package sem1.week4;

public class Race {
	
	public static void main(String[] args) {
		//Creating our car.
		Car car = new Car("BMW I8", 2016);
		System.out.println("My car is a " + car.getBrand() + " " + car.getYear());
		
		//Accelerating
		for(int i = 0; i < 5; i++) {
			System.out.println("We accelerated! " + car.accelerate());
		}
		
		//I don't know why...
		System.out.println("Dam this car is fast!");
		
		//Braking.
		for(int i = 0; i < 5; i++) {
			System.out.println("We are breaking! " + car.brake());
		}
	}
	
}
