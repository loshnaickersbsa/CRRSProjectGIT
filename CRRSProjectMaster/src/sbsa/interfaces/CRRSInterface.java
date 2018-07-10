package sbsa.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import sbsa.beans.Building;
import sbsa.beans.Equipment;
import sbsa.beans.Reservation;
import sbsa.beans.Room;
import sbsa.beans.User;
import sbsa.exceptions.CRRSException;

public interface CRRSInterface {

	public Equipment addEquipment(Equipment equipment) throws SQLException;
	public Equipment editEquipment(Equipment equipment) throws SQLException;
	public Equipment deleteEquipment(Equipment equipment) throws SQLException;
	public ArrayList<Equipment> getAllEquipment() throws SQLException, CRRSException;
	public ArrayList<Equipment> findEquipment(String expression) throws CRRSException, SQLException;
	public Room addRoom(Room room) throws SQLException;
	public Room editRoom(Room room) throws SQLException;
	public Room deleteRoom(Room room) throws SQLException;
	public ArrayList<Room> getAllRooms() throws SQLException, CRRSException;
	public ArrayList<Room> findRooms(ArrayList<Equipment> equipment, Date startDate, Date endDate, int duration, Building building) throws SQLException, CRRSException;
	public Building addBuilding(Building building) throws SQLException;
	public Building editBuilding(Building building) throws SQLException;
	public Building deleteBuilding(Building building) throws SQLException;
	public ArrayList<Building> getAllBuildings() throws CRRSException, SQLException;
	public User login(String userID, String password) throws SQLException, CRRSException;
	public User addUser(User user) throws SQLException;
	public User editUser(User user) throws SQLException;
	public User deleteUser(User user) throws SQLException;
	public ArrayList<User> getAllUsers() throws SQLException, CRRSException;
	public ArrayList<User> findUser(String name) throws CRRSException, SQLException;
	public Reservation addReservation(Reservation reservation) throws SQLException;
	public Reservation editReservation(Reservation reservation) throws SQLException;
	public Reservation deleteReservation(Reservation reservation) throws SQLException;
	public ArrayList<Reservation> getAllReservations() throws SQLException, CRRSException;
	public ArrayList<Reservation> findReservations(User user, Date startDate, Date endDate) throws CRRSException, SQLException;
	public ArrayList<Reservation> findReservations(Room room, Date startDate, Date endDate) throws SQLException, CRRSException;
	public ArrayList<Reservation> findReservations(Date startDate, Date endDate) throws SQLException, CRRSException;
	
}
