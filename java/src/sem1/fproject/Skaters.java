package sem1.fproject;

import java.util.Scanner;

/**
 * Represents a single Skaters.
 */
public class Skaters {
	
	private final static int JUDGES = 8;
	
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
	
	private Skaters() {
		//Preventing empty creation of this object.
	}
	
	/**
	 * Creates a new {@link Skaters}.
	 * @param in the scanner to create our class.
	 */
	Skaters(Scanner in) {
		this.setFirstPersonName(in.nextLine());
		this.setSecondPersonName(in.nextLine());
		this.setCountry(in.nextLine());
		
		//Setting technical average.
		double tech = 0;
		for(int t = 0; t < JUDGES; t++)
			tech += in.nextDouble();
		tech /= JUDGES;
		this.setTechnical(tech);
		
		//Setting performance average.
		double perf = 0;
		for(int p = 0; p < JUDGES; p++)
			perf += in.nextDouble();
		perf /= JUDGES;
		this.setPerformance(perf);
		System.out.println(in.nextLine());//skip the enter char.
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

