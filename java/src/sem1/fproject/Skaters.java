package sem1.fproject;

/**
 * Represents a single Skaters.
 */
public class Skaters {
	
	/**
	 * The name of the fist person.
	 */
	private String firstPersonName;
	
	/**
	 * The name of this the second person.
	 */
	private String secondPersonName;
	
	/**
	 * The represented country of this skater.
	 */
	private String country;
	
	/**
	 * The technical score average of this skater.
	 */
	private double technical;
	
	/**
	 * The performance score average of this skater.
	 */
	private double performance;
	
	/**
	 * Creates a new {@link Skaters}.
	 * @param firstPersonName the last name of the skater.
	 * @param secondPersonName the first name of the skater.
	 * @param country the country of the skater.
	 * @param technical the technical score array of the skater.
	 * @param performance the performance score array of the skater.
	 */
	Skaters(String firstPersonName, String secondPersonName, String country, String[] technical, String[] performance) {
		this.setFirstPersonName(firstPersonName);
		this.setSecondPersonName(secondPersonName);
		this.setCountry(country);
		
		//Setting technical average.
		double tech = 0;
		for(String t : technical)
			tech += Double.parseDouble(t);
		tech /= technical.length;
		this.setTechnical(tech);
		
		//Setting performance average.
		double perf = 0;
		for(String p : performance)
			perf += Double.parseDouble(p);
		perf /= performance.length;
		this.setPerformance(perf);
	}
	
	/**
	 * Creates a new {@link Skaters} instance by copying another one.
	 * @param skater the instance to copy from.
	 */
	Skaters(Skaters skater) {
		setSecondPersonName(skater.getSecondPersonName());
		setFirstPersonName(skater.getFirstPersonName());
		setCountry(skater.getCountry());
		setTechnical(skater.getTechnical());
		setPerformance(skater.getPerformance());
	}
	
	/**
	 * Gets the average of this skater.
	 * @return his average.
	 */
	double getAverage() {
		return getTechnical() + getPerformance();
	}
	
	/**
	 * Copying this {@link Skaters} to a new instance.
	 * @return the skater instance.
	 */
	Skaters copy() {
		return new Skaters(this);
	}
	
	@Override
	public String toString() {
		return "[" + getCountry() + "] " + getSecondPersonName() + " & " + getFirstPersonName() + " - tech:" + getTechnical() + " : perf:" + getPerformance() + " | average: " + getAverage();
	}
	
	private String getFirstPersonName() {
		return firstPersonName;
	}
	
	private String getSecondPersonName() {
		return secondPersonName;
	}
	
	private String getCountry() {
		return country;
	}
	
	private double getTechnical() {
		return technical;
	}
	
	private double getPerformance() {
		return performance;
	}
	
	/* PS: Never will be used :) should of been final without that .... */
	
	public void setFirstPersonName(String firstPersonName) {
		this.firstPersonName = firstPersonName;
	}
	
	public void setSecondPersonName(String secondPersonName) {
		this.secondPersonName = secondPersonName;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setTechnical(double technical) {
		this.technical = technical;
	}
	
	public void setPerformance(double performance) {
		this.performance = performance;
	}
}

