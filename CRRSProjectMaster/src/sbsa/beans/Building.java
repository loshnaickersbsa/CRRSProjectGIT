package sbsa.beans;

import java.io.Serializable;
import sbsa.exceptions.*;

public class Building implements Serializable  {

	private static final long serialVersionUID = 1L;
	private int buildingID;
	private String buildingName;
	
	public Building(int buildingID, String buildingName ) throws CRRSException {
		this.buildingID = buildingID;
		setBuildingName(buildingName);;
	}
	
	public Building(String buildingName) throws CRRSException{
		this(-1,buildingName);
	}

	public int getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(int buildingID) {
		this.buildingID = buildingID;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) throws CRRSException {
		if (buildingName.trim().length() > 0) {
		this.buildingName = buildingName;
		} else {
			throw new CRRSException("Building - Building name can't be blank");
		}
	}

	@Override
	public String toString() {
		return "Building [buildingID=" + buildingID + ", buildingName=" + buildingName + "]";
	}
}
