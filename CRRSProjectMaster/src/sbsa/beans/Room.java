package sbsa.beans;

import java.io.Serializable;
import java.util.*;

import sbsa.exceptions.CRRSException;

public class Room implements Serializable{
	
	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", roomLocation=" + roomLocation + ", seats=" + seats + ", building="
				+ building + ", equipment=" + equipment + "]";
	}

	private static final long serialVersionUID = 1L;
	private int roomID;
	private String roomLocation;
	private int seats;
	private Building building;
	private ArrayList<Equipment> equipment;
	


	public Room (int roomID, String roomLocation, int seats, Building building, ArrayList<Equipment> equipment) throws CRRSException {
		this.roomID = roomID;
		this.building = building;
		setRoomLocation(roomLocation);
		setSeats(seats);
		this.equipment = equipment;
	}
	
	public Room(String roomLocation, int seats, Building building, ArrayList<Equipment> equipment) throws CRRSException {
		this(-1,roomLocation,seats,building, equipment);
	}

	public ArrayList<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(ArrayList<Equipment> equipment) {
		this.equipment = equipment;
	}
	
	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getRoomLocation() {
		return roomLocation;
	}

	public void setRoomLocation(String roomLocation) throws CRRSException {
		if (roomLocation.trim().length() > 0) {
		this.roomLocation = roomLocation;
		} else {
			throw new CRRSException("Room - Room Location can't be blank");
		}
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) throws CRRSException {
		if (seats > 0) {
		this.seats = seats; 
		} else {
			throw new CRRSException("Room - There must be at least one seat");
		}
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}
	

}
