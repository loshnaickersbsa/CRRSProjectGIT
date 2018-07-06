package sbsa.beans;

import java.io.Serializable;
import java.util.*;

import sbsa.exceptions.CRRSException;

public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;
	private int reservationID;
	private String meetingTitle;
	private int attendees;
	private Date bookingDate;
	private Date meetingStart;
	private Date meetingEnd;
	private Status status;
	private User user;
	private Date notifyDate;
	private Room room;
	private ArrayList<Equipment> equipment;
	

	public Reservation(int reservationID, String meetingTitle, int attendees, Date bookingDate, Date meetingStart, Date meetingEnd, Status status, User user,  Room room, ArrayList<Equipment> equipment,  Date notifyDate) throws CRRSException {
		this.reservationID = reservationID;
		setMeetingTitle(meetingTitle);
		setAttendees(attendees);
		setBookingDate(bookingDate);
		setMeetingStart(meetingStart);
		setMeetingEnd(meetingEnd);
		this.status=status;
		this.user=user;
		this.room = room;
		this.equipment=equipment;
		setNotifyDate(notifyDate);
	}
	
	@Override
	public String toString() {
		return "Reservation [reservationID=" + reservationID + ", meetingTitle=" + meetingTitle + ", attendees="
				+ attendees + ", bookingDate=" + bookingDate + ", meetingStart=" + meetingStart + ", meetingEnd="
				+ meetingEnd + ", status=" + status + ", user=" + user + ", notifyDate=" + notifyDate + ", room=" + room
				+ ", equipment=" + equipment + "]";
	}

	public Reservation(String meetingTitle, int attendees, Date bookingDate, Date meetingStart, Date meetingEnd, Status status, User user, Room room, ArrayList<Equipment> equipment) throws CRRSException {
		this(-1,meetingTitle,attendees,bookingDate,meetingStart,meetingEnd,status,user,room, equipment,null);
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	public String getMeetingTitle() {
		return meetingTitle;
	}
	public void setMeetingTitle(String meetingTitle) throws CRRSException {
		this.meetingTitle = meetingTitle;
		if (meetingTitle.trim().length() > 0) {
			this.meetingTitle = meetingTitle;
			} else {
				throw new CRRSException("Reservation - Meeting Title can't be blank");
			}
	}
	public int getAttendees() {
		return attendees;
	}
	public void setAttendees(int attendees) throws CRRSException {
		if (attendees > 0) {
		this.attendees = attendees; 
		} else {
			throw new CRRSException("Reservation - must have at least one attendee");
		}
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		if (bookingDate == null) {
			Date d = new Date();
			this.bookingDate=d;
		} else {
		this.bookingDate = bookingDate;
		}
	}
	public Date getMeetingStart() {
		return meetingStart;
	}
	public void setMeetingStart(Date meetingStart) {
		this.meetingStart = meetingStart;
		
	}
	public Date getMeetingEnd() {
		return meetingEnd;
	}
	public void setMeetingEnd(Date meetingEnd) {
		this.meetingEnd = meetingEnd;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getNotifyDate() {
		return notifyDate;
	}
	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
	public ArrayList<Equipment> getEquipment() {
		return equipment;
	}
	public void setEquipment(ArrayList<Equipment> equipment) {
		this.equipment = equipment;
	}

	

}
