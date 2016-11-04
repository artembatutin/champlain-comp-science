package exercises.week4;

public class Temperature {
	
	private double ftemp;
	
	Temperature(double ftemp) {
		this.ftemp = ftemp;
	}
	
	public double getFahrenheit() {
		return ftemp;
	}
	
	public double getCelsius() {
		return (5/9.0) * (ftemp - 32);
	}
	
	public double getKelvin() {
		return ((5/9.0) * (ftemp - 32)) + 273;
	}
}
