package model;

import java.io.Serializable;

public class Boat implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -544958122654437423L;		// For serialization
	private double length;
	private BoatType type;
	
	public enum BoatType {
		SAILBOAT,
		MOTORSAILER,
		KAYAK_CANOE {
			public String toString() {		//Overrides default toString (face value)
				return "KAYAK/CANOE";
			}
		},
		OTHER
	}
	
	public Boat(BoatType type, double length) {
		this.type = type;
		this.length = length;
	}
	
	public double getLength() {
		return length;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public BoatType getType() {
		return type;
	}
	
	public void setType(BoatType type) {
		this.type = type;
	}

}
