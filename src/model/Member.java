package model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Member implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3780893980442847296L;		// For serialization
	private int id;
	private long pNr;
	private String name;
	private List<Boat> boats;
	
	public Member(String name, long pNr) {
		this.name = name;
		this.pNr = pNr;
	}
	
	public Member(int id, String name, long pNr) {
		this.id = id;
		this.pNr = pNr;
		this.name = name;
		boats = new ArrayList<Boat>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public long getPNr() {
		return pNr;
	}
	
	public void setPNr(long pNr) {
		this.pNr = pNr;
	}
	
	public void addBoat(Boat.BoatType type, double length) {
		Boat boat = new Boat(type, length);
		boats.add(boat);
	}
	
	public boolean deleteBoat(int boatNr) {
		Boat boat = getBoat(boatNr);
		if(boat != null) {
			boats.remove(boat);
			return true;
		}
		return false;
	}
	
	public Boat getBoat(int boatNr) {
		Boat boat;
		try {
			boat = boats.get(boatNr);
		}
		catch (IndexOutOfBoundsException e) {
			return null;
		}
		return boat;
	}
	
	public List<Boat> getBoats() {
		return boats;
	}
	
	public int getNrOfBoats() {
		return boats.size();
	}
}
