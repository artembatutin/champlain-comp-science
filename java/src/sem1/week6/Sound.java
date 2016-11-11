package sem1.week6;

public class Sound {

	private final double distance;

	Sound(double distance) {
		this.distance = distance;
	}

	public double getDistance() {
		return distance;
	}

	public double getDistance(SoundMedium medium) {
		return distance / medium.getFeet();
	}
}
