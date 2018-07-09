package sbsa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import sbsa.exceptions.CRRSException;

public class Room implements Serializable {

	@Override
	public String toString() {
		String freeFromString = null;
		String freeUntilString = null;
		if (freeFrom != null) {
			freeFromString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(freeFrom);
		}
		if (freeUntil != null) {
			freeUntilString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(freeUntil);
		}
		return "Room [roomID=" + roomID + ", roomLocation=" + roomLocation + ", seats=" + seats + ", building="
				+ building + ", equipment=" + equipment + ", freeFrom=" + freeFromString + ", freeUntil="
				+ freeUntilString + "]";
	}

	private static final long serialVersionUID = 1L;
	private int roomID;
	private String roomLocation;
	private int seats;
	private Building building;
	private ArrayList<Equipment> equipment;
	private Date freeFrom;
	private Date freeUntil;

	public Room(int roomID, String roomLocation, int seats, Building building, ArrayList<Equipment> equipment)
			throws CRRSException {
		this.roomID = roomID;
		this.building = building;
		setRoomLocation(roomLocation);
		setSeats(seats);
		this.equipment = equipment;
	}

	public Room(String roomLocation, int seats, Building building, ArrayList<Equipment> equipment)
			throws CRRSException {
		this(-1, roomLocation, seats, building, equipment);
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

	public Date getFreeFrom() {
		return freeFrom;
	}

	public void setFreeFrom(Date freeFrom) {
		this.freeFrom = freeFrom;
	}

	public Date getFreeUntil() {
		return freeUntil;
	}

	public void setFreeUntil(Date freeUntil) {
		this.freeUntil = freeUntil;
	}

}
