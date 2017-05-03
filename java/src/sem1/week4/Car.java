package sem1.week4;

public class Car {
	
	private int speed;
	private final int year;
	private final String brand;
	
	Car(String brand, int year) {
		this.brand = brand;
		this.year = year;
		this.speed = 0;
	}
	
	public int accelerate() {
		return speed += 5;
	}
	
	public int brake() {
		return speed -= 5;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public int getYear() {
		return year;
	}
}
