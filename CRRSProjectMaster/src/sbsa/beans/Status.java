package sbsa.beans;

import java.io.Serializable;

import sbsa.exceptions.CRRSException;

public class Status implements Serializable {

	private static final long serialVersionUID = 1L;
	private int statusID;
	private String statusDesc;


	public Status(int statusID, String statusDesc) throws CRRSException {
		this.statusID=statusID;
		setStatusDesc(statusDesc);
	}
	
	public Status(String statusDesc) throws CRRSException {
		this(0,statusDesc);
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) throws CRRSException {

		if (statusDesc.trim().length() > 0) {
			this.statusDesc = statusDesc;
		} else {
			throw new CRRSException("Status - Status can't be blank");
		}


	}

	@Override
	public String toString() {
		return "Status [statusID=" + statusID + ", statusDesc=" + statusDesc + "]";
	}

}
