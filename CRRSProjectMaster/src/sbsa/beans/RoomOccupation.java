package sbsa.beans;

import java.io.Serializable;

public class RoomOccupation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String buildingName;
	private String roomLocation;
	private String occupation;
	
	public RoomOccupation(String buildingName, String roomLocation, String occupation) {
		this.buildingName = buildingName;
		this.roomLocation = roomLocation;
		this.occupation = occupation;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoomLocation() {
		return roomLocation;
	}

	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Override
	public String toString() {
		return "RoomOccupation [buildingName=" + buildingName + ", roomLocation=" + roomLocation + ", occupation="
				+ occupation + "]";
	}
	

}
