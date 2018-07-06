package sbsa.beans;

import java.io.Serializable;

import sbsa.exceptions.CRRSException;

public class Equipment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int equipmentID;
	private String equipmentDesc;
	private int qty;
	
	public Equipment(int id, String desc, int qty) throws CRRSException {
		equipmentID = id;
		setEquipmentDesc(desc);
		this.qty=qty;
	}
	
	public Equipment(String desc) throws CRRSException {
		this(0,desc,0);
	}
	
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(int equipmentID) {
		this.equipmentID = equipmentID;
	}

	public String getEquipmentDesc() {
		return equipmentDesc;
	}
	public void setEquipmentDesc(String equipmentDesc) throws CRRSException {

		if (equipmentDesc.trim().length() > 0) {
			this.equipmentDesc = equipmentDesc;
			} else {
				throw new CRRSException("Equipment - Equipment Description can't be blank");
			}
	}
	
	@Override
	public String toString() {
		return "Equipment [equipmentID=" + equipmentID + ", equipmentDesc=" + equipmentDesc + "]";
	}
	

}
