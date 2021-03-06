package sbsa.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import sbsa.beans.Building;
import sbsa.beans.Equipment;
import sbsa.beans.Reservation;
import sbsa.beans.Room;
import sbsa.beans.RoomOccupation;
import sbsa.beans.Status;
import sbsa.beans.User;
import sbsa.exceptions.CRRSException;
import sbsa.interfaces.CRRSInterface;

public class DataAccessObject implements CRRSInterface {
	Connection con = null;
	Properties prop = null;

	public DataAccessObject() {
		if (!loadProperties()) {
			System.err.println("DAO properties error. DAO terminating");
			return;
		}
		try {
			Class.forName(prop.getProperty("dbDriver")); // Ensures that the
															// Driver
															// is loaded into
															// memory

			System.out.println("Registered the Dataase Driver class...>>>>>>>>>");

			String url = prop.getProperty("dbProtocol") + ":" + prop.getProperty("dbSubprotocol") + "://"
					+ prop.getProperty("dbHostName") + ":" + prop.getProperty("dbPort") + "/"
					+ prop.getProperty("dbName");
			String user = prop.getProperty("dbUser");
			String password = prop.getProperty("dbPassword");

			con = DriverManager.getConnection(url, user, password); // connect
																	// to
																	// the
																	// database
			System.out.println("Database Connection opened...>>>>>>>>>>>");
		} catch (ClassNotFoundException e) {
			System.err.println("Error loading DAO driver - " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Error connecting to the database server - " + e.getMessage());
			System.err.println("SQLState = " + e.getSQLState());
			System.err.println("ErrorCode = " + e.getErrorCode());
		}

	}

	private boolean loadProperties() {
		prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("resources/dao.properties");
			prop.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error initializing DAO");
			System.err.println("resources/dao.properties file missing.");
			return false;
		} catch (IOException e) {
			System.err.println("Error initializing DAO");
			System.err.println("IOException reading resources/dao.properties.");
			return false;
		}
		if (!validProperties()) {
			System.err.println("DAO properties error. DAO terminating");
			return false;
		}
		return true;
	}

	private boolean validProperties() {
		if (prop.getProperty("dbDriver") == null) {
			System.err.println("DAO property 'dbDriver' not found.");
			return false;
		}
		if (prop.getProperty("dbHostName") == null) {
			System.err.println("DAO property 'dbHostName' not found.");
			return false;
		}
		if (prop.getProperty("dbPort") == null) {
			System.err.println("DAO property 'dbPort' not found.");
			return false;
		}
		if (prop.getProperty("dbUser") == null) {
			System.err.println("DAO property 'dbUser' not found.");
			return false;
		}
		if (prop.getProperty("dbPassword") == null) {
			System.err.println("DAO property 'dbPassword' not found.");
			return false;
		}
		if (prop.getProperty("dbProtocol") == null) {
			System.err.println("DAO property 'dbProtocol' not found.");
			return false;
		}
		if (prop.getProperty("dbSubprotocol") == null) {
			System.err.println("DAO property 'dbSubprotocol' not found.");
			return false;
		}
		if (prop.getProperty("dbName") == null) {
			System.err.println("DAO property 'dbName' not found.");
			return false;
		}
		return true;
	}

	public void close() {
		try {
			con.close();
			System.out.println("Database Connection closed...>>>>>>>>>>>");
		} catch (SQLException e) {
			System.err.println("Error closing the database server connection - " + e.getMessage());
			System.err.println("SQLState = " + e.getSQLState());
			System.err.println("ErrorCode = " + e.getErrorCode());
		}
	}

	public void commit() throws SQLException {
		if (!con.getAutoCommit())
			con.commit();
	}

	@Override
	public Equipment addEquipment(Equipment equipment) throws SQLException {
		int r = 0;
		String ADDEQUIP = "INSERT INTO equipment (EquipmentDesc) VALUES(?);";
		PreparedStatement ps = con.prepareStatement(ADDEQUIP,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, equipment.getEquipmentDesc());
		r = ps.executeUpdate();
		if (r > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				equipment.setEquipmentID(rs.getInt(1));
			}
			commit();
			return equipment;
		} else
			return null;
	}

	@Override
	public Equipment editEquipment(Equipment equipment) throws SQLException {
		int r = 0;
		String UPDATEEQUIP = "UPDATE Equipment SET EquipmentDesc = ? WHERE EquipmentID = ?;";
		PreparedStatement ps = con.prepareStatement(UPDATEEQUIP);
		ps.setString(1, equipment.getEquipmentDesc());
		ps.setInt(2, equipment.getEquipmentID());
		r = ps.executeUpdate();
		if (r > 0) {
			commit();
			return equipment;
		} else
			return null;
	}

	@Override
	public Equipment deleteEquipment(Equipment equipment) throws SQLException {
		int r = 0;
		String DELETEEQUIP = "DELETE FROM Equipment WHERE EquipmentID = ?;";
		PreparedStatement ps = con.prepareStatement(DELETEEQUIP);
		ps.setInt(1, equipment.getEquipmentID());
		r = ps.executeUpdate();
		if (r > 0) {
			commit();
			return equipment;
		} else
			return null;
	}

	@Override
	public ArrayList<Equipment> getAllEquipment() throws SQLException, CRRSException {
		String ALLEQUIPMENT = "SELECT EquipmentID, EquipmentDesc, 0 AS qty FROM Equipment;";
		PreparedStatement ps = con.prepareStatement(ALLEQUIPMENT);
		ResultSet rs = ps.executeQuery();
		return equipmentArrayList(rs);
	}

	@Override
	public ArrayList<Equipment> findEquipment(String expression) throws CRRSException, SQLException {
		String FINDEQUIPMENT = "SELECT EquipmentID, EquipmentDesc, 0 AS qty FROM Equipment WHERE EquipmentDesc LIKE ?;";
		PreparedStatement ps = con.prepareStatement(FINDEQUIPMENT);
		ps.setString(1, expression);
		ResultSet rs = ps.executeQuery();
		return equipmentArrayList(rs);
	}

	public ArrayList<Equipment> equipmentArrayList(ResultSet resultSet) throws CRRSException, SQLException {
		ArrayList<Equipment> v = new ArrayList<Equipment>();
		while (resultSet.next()) {
			v.add(constructEquipment(resultSet));
		}
		if (v.size() > 0)
			return v;
		else
			return null;
	}

	public Equipment constructEquipment(ResultSet resultSet) throws CRRSException, SQLException {
		Equipment equipment = new Equipment(resultSet.getInt("EquipmentID"), resultSet.getString("EquipmentDesc"),
				resultSet.getInt("qty"));
		return equipment;
	}

	@Override
	public Room addRoom(Room room) throws SQLException {
		int r = 0;
		String ADDROOM = "INSERT INTO rooms (RoomLocation,Seats,BuildingID) VALUES(?,?,?);";
		PreparedStatement ps = con.prepareStatement(ADDROOM,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, room.getRoomLocation());
		ps.setInt(2, room.getSeats());
		ps.setInt(3, room.getBuilding().getBuildingID());
		r = ps.executeUpdate();
		if (r > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				room.setRoomID(rs.getInt(1));
				if (addRoomEquipment(room)) {
					commit();
					return room;
				}
			}
			return null;
		} else
			return null;
	}

	public boolean addRoomEquipment(Room room) throws SQLException {
		if (room.getEquipment() == null)
			return true;
		int r = 0;
		int count = 0;
		String ADDROOMEQUIPMENT = "INSERT INTO roomequipment (RoomID, EquipmentID, qty) VALUES(?,?,?);";
		PreparedStatement ps = con.prepareStatement(ADDROOMEQUIPMENT);
		for (Equipment e : room.getEquipment()) {
			ps.setInt(1, room.getRoomID());
			ps.setInt(2, e.getEquipmentID());
			ps.setInt(3, e.getQty());
			r = ps.executeUpdate();
			if (r > 0) {
				count++;
			}
		}
		if (count == room.getEquipment().size()) {
			return true;
		} else
			return false;
	}

	@Override
	public Room editRoom(Room room) throws SQLException {
		int r = 0;
		String UPDATEROOM = "UPDATE rooms SET RoomLocation = ?, Seats = ?, BuildingID = ? WHERE RoomID = ?;";
		PreparedStatement ps = con.prepareStatement(UPDATEROOM);
		ps.setString(1, room.getRoomLocation());
		ps.setInt(2, room.getSeats());
		ps.setInt(3, room.getBuilding().getBuildingID());
		ps.setInt(4, room.getRoomID());
		r = ps.executeUpdate();
		if (r > 0) {
			if ((deleteRoomEquipment(room.getRoomID())) && (addRoomEquipment(room))) {
				commit();
				return room;
			}
			return null;
		} else
			return null;
	}

	@Override
	public Room deleteRoom(Room room) throws SQLException {
		int r = 0;
		String DELETEROOM = "DELETE FROM rooms WHERE RoomID = ?;";
		PreparedStatement ps = con.prepareStatement(DELETEROOM);
		ps.setInt(1, room.getRoomID());
		r = ps.executeUpdate();
		if (r > 0) {
			commit();
			return room;
		} else
			return null;
	}

	public boolean deleteRoomEquipment(int roomID) throws SQLException {
		int r = 0;
		String DELETEROOMEQUIPMENT = "DELETE FROM roomequipment WHERE RoomID = ?;";
		PreparedStatement ps = con.prepareStatement(DELETEROOMEQUIPMENT);
		ps.setInt(1, roomID);
		r = ps.executeUpdate(DELETEROOMEQUIPMENT);
		return true;
	}

	public ArrayList<Equipment> getRoomEquipment(int roomID) throws SQLException, CRRSException {
		String ROOMEQUIPMENT = "SELECT EquipmentID, EquipmentDesc, qty "
				+ "FROM equipment AS e, INNER JOIN roomequipment AS re" + " ON (e.equipmentID = re.equipmentID)"
				+ " WHERE re.roomID = ?;";
		PreparedStatement ps = con.prepareStatement(ROOMEQUIPMENT);
		ps.setInt(1, roomID);
		ResultSet rs = ps.executeQuery();
		return equipmentArrayList(rs);
	}

	@Override
	public ArrayList<Room> getAllRooms() throws SQLException, CRRSException {
		String ALLROOMS = "SELECT RoomID, RoomLocation, Seats, BuildingID, BuildingName "
				+ "FROM rooms AS r, buildings AS b " + "INNER JOIN ON r.BuildingID = b.BuildingID;";
		PreparedStatement ps = con.prepareStatement(ALLROOMS);
		ResultSet rs = ps.executeQuery();
		return roomArrayList(rs);
	}

	@Override
	public ArrayList<Room> findRooms(ArrayList<Equipment> equipment, Date startDate, Date endDate, int duration,
			Building building) throws SQLException, CRRSException {
		String start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDate);
		String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate);
		System.out.println("startDate = " + start);
		System.out.println("endDate = " + end);
		String DROPOPENTIME = "DROP TEMPORARY TABLE opentime;";
		String DROPOPENTIME1 = "DROP TEMPORARY TABLE opentime1;";
		String DROPOPENTIME2 = "DROP TEMPORARY TABLE opentime2;";
		String CREATEOPENTIME = "CREATE TEMPORARY TABLE opentime "
				+ "(RoomID int, FreeFrom DATETIME, FreeUntil DATETIME);";
		String CREATEOPENTIME1 = "CREATE TEMPORARY TABLE opentime1 "
				+ "(RoomID int, FreeFrom DATETIME, FreeUntil DATETIME);";
		String CREATEOPENTIME2 = "CREATE TEMPORARY TABLE opentime2 "
				+ "(RoomID int, FreeFrom DATETIME, FreeUntil DATETIME);";
		String INSERTOPENTIME1 = "INSERT INTO opentime1 (RoomID, FreeFrom) SELECT roomid, ? " + "FROM rooms;";
		String INSERTOPENTIME2 = "INSERT INTO opentime2 (RoomID, FreeFrom) "
				+ "SELECT reservations.RoomID, MeetingEnd FROM reservations, opentime1 "
				+ "WHERE opentime1.RoomID = reservations.RoomID " + "AND ((MeetingStart > ?  AND MeetingStart < ?) "
				+ "OR (MeetingEnd > ? AND MeetingEnd < ?) " + "OR (MeetingStart < ? and MeetingEnd > ?));";
		String SELECTOPENTIME1 = "SELECT DISTINCT reservations.roomid, MeetingStart " + "FROM reservations, opentime "
				+ "WHERE opentime.RoomID = reservations.RoomID " + "AND MeetingStart > opentime.FreeFrom "
				+ "ORDER BY roomid, meetingstart;";
		String MERGEROWS = "INSERT INTO opentime (RoomID, FreeFrom) " + "Select RoomID, FreeFrom FROM opentime1 "
				+ "UNION " + "Select RoomID, FreeFrom FROM opentime2;";
		String UPDATEOPENTIME1 = "UPDATE opentime " + "SET FreeUntil = ? " + "WHERE opentime.RoomID = ? "
				+ "AND opentime.FreeFrom < ? " + "AND opentime.FreeUntil IS NULL;";
		String UPDATEOPENTIME2 = "UPDATE opentime SET FreeUntil = ? WHERE FreeUntil IS NULL;";
		String DELETEOPENTIME1 = "DELETE FROM opentime WHERE roomid IN " + "(SELECT roomid FROM reservations "
				+ "WHERE meetingstart < ? " + "AND meetingend > ?);";
		String DELETEOPENTIME2 = "DELETE FROM opentime WHERE (SELECT TIMESTAMPDIFF(MINUTE, FreeFrom, FreeUntil) < ?);";
		String DELETEOPENTIME3 = "DELETE FROM opentime WHERE RoomID NOT IN (Select RoomID FROM rooms WHERE BuildingID = ?);";
		String FINDROOMS = "SELECT r.RoomID, r.RoomLocation, r.Seats, r.BuildingID, b.BuildingName, ot.FreeFrom, ot.FreeUntil "
				+ "FROM opentime AS ot INNER JOIN rooms AS r INNER JOIN buildings AS b "
				+ "ON (ot.RoomID = r.RoomID AND r.BuildingID = b.BuildingID) " + "ORDER BY r.RoomID, ot.FreeFrom;";

		int r = 0;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(DROPOPENTIME);
			r = ps.executeUpdate();
		} catch (SQLException e) {
		}
		try {
			ps = con.prepareStatement(DROPOPENTIME1);
			r = ps.executeUpdate();
		} catch (SQLException e) {
		}
		try {
			ps = con.prepareStatement(DROPOPENTIME2);
			r = ps.executeUpdate();
		} catch (SQLException e) {
		}
		ps = con.prepareStatement(CREATEOPENTIME);
		r = ps.executeUpdate();
		ps = con.prepareStatement(CREATEOPENTIME1);
		r = ps.executeUpdate();
		ps = con.prepareStatement(CREATEOPENTIME2);
		r = ps.executeUpdate();
		ps = con.prepareStatement(INSERTOPENTIME1);
		ps.setString(1, start);
		r = ps.executeUpdate();
		ps = con.prepareStatement(INSERTOPENTIME2);
		ps.setString(1, start);
		ps.setString(3, start);
		ps.setString(5, start);
		ps.setString(2, end);
		ps.setString(4, end);
		ps.setString(6, end);
		r = ps.executeUpdate();
		ps = con.prepareStatement(MERGEROWS);
		r = ps.executeUpdate();
		ps = con.prepareStatement(SELECTOPENTIME1);
		ResultSet rs = ps.executeQuery();
		ps = con.prepareStatement(UPDATEOPENTIME1);
		while (rs.next()) {
			Date dt = rs.getTimestamp("meetingstart");
			String ms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);
			ps.setString(1, ms);
			ps.setInt(2, rs.getInt("RoomID"));
			ps.setString(3, ms);
			r = ps.executeUpdate();
		}
		ps = con.prepareStatement(UPDATEOPENTIME2);
		ps.setString(1, end);
		r = ps.executeUpdate();
		ps = con.prepareStatement(DELETEOPENTIME1);
		ps.setString(1, start);
		ps.setString(2, end);
		r = ps.executeUpdate();
		ps = con.prepareStatement(DELETEOPENTIME2);
		ps.setInt(1, duration);
		r = ps.executeUpdate();
		ps = con.prepareStatement(DELETEOPENTIME3);
		ps.setInt(1, building.getBuildingID());
		r = ps.executeUpdate();
		ps = con.prepareStatement(FINDROOMS);
		rs = ps.executeQuery();
		ArrayList<Room> rooms = roomArrayList(rs);
		rs.beforeFirst();
		for (Room room : rooms) {
			rs.next();
			room.setFreeFrom(rs.getTimestamp("FreeFrom"));
			room.setFreeUntil(rs.getTimestamp("FreeUntil"));
		}
		return rooms;
	}

	public ArrayList<Room> roomArrayList(ResultSet resultSet) throws CRRSException, SQLException {
		ArrayList<Room> v = new ArrayList<Room>();
		while (resultSet.next()) {
			v.add(constructRoom(resultSet));
		}
		if (v.size() > 0)
			return v;
		else
			return null;
	}

	public Room constructRoom(ResultSet resultSet) throws CRRSException, SQLException {
		Building building = constructBuilding(resultSet);
		Room room = new Room(resultSet.getInt("RoomID"), resultSet.getString("RoomLocation"), resultSet.getInt("Seats"),
				building, getRoomEquipment(resultSet.getInt("RoomID")));
		return room;
	}

	@Override
	public Building addBuilding(Building building) throws SQLException {
		int r = 0;
		String ADDBUILDING = "INSERT INTO buildings (BuildingName) VALUES(?);";
		PreparedStatement ps = con.prepareStatement(ADDBUILDING, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, building.getBuildingName());
		r = ps.executeUpdate();
		if (r > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				building.setBuildingID(rs.getInt(1));
				commit();
			}
			return building;
		} else
			return null;
	}

	@Override
	public Building editBuilding(Building building) throws SQLException {
		int r = 0;
		String UPDATEBUILDING = "UPDATE Buildings SET BuildingName = ? WHERE BuildingID = ?;";
		PreparedStatement ps = con.prepareStatement(UPDATEBUILDING);
		ps.setString(1, building.getBuildingName());
		ps.setInt(2, building.getBuildingID());
		r = ps.executeUpdate();
		if (r > 0) {
			commit();
			return building;
		} else
			return null;
	}

	@Override
	public Building deleteBuilding(Building building) throws SQLException {
		int r = 0;
		String DELETEBUILDING = "DELETE FROM buildings WHERE BuildingID = ?;";
		PreparedStatement ps = con.prepareStatement(DELETEBUILDING);
		ps.setInt(1, building.getBuildingID());
		r = ps.executeUpdate();
		if (r > 0) {
			commit();
			return building;
		} else
			return null;
	}

	@Override
	public ArrayList<Building> getAllBuildings() throws CRRSException, SQLException {
		String ALLBUILDINGS = "SELECT BuildingID, BuildingName FROM buildings;";
		PreparedStatement ps = con.prepareStatement(ALLBUILDINGS);
		ResultSet rs = ps.executeQuery();
		return buildingArrayList(rs);
	}

	public ArrayList<Building> buildingArrayList(ResultSet resultSet) throws CRRSException, SQLException {
		ArrayList<Building> v = new ArrayList<Building>();
		while (resultSet.next()) {
			v.add(constructBuilding(resultSet));
		}
		if (v.size() > 0)
			return v;
		else
			return null;
	}

	public Building constructBuilding(ResultSet resultSet) throws CRRSException, SQLException {
		Building building = new Building(resultSet.getInt("BuildingID"), resultSet.getString("BuildingName"));
		return building;
	}

	@Override
	public User login(String userID, String password) throws SQLException, CRRSException {
		String LOGIN = "SELECT name, Telephone, Title, email, Department, Admin, Exco "
				+ "FROM users WHERE UserID = ? and password = ?;";
		PreparedStatement ps = con.prepareStatement(LOGIN);

		// user login losh
		ps.setString(1, userID);
		ps.setString(2, password);
		
		System.out.println( ps.toString() );
		
		ResultSet rs = ps.executeQuery();
		
		User user = null;
		
		while (rs.next()) {
			user = new User(userID, rs.getString("name"), password, rs.getString("Telephone"), rs.getString("Title"),
					rs.getString("Department"), rs.getString("email"), rs.getBoolean("Admin"), rs.getBoolean("Exco"));
		}
		return user;
	}

	@Override
	public User addUser(User user) throws SQLException {
		int r = 0;
		String ADDUSER = "INSERT INTO users (UserID, name, password, Telephone, Title, email, Department, Admin, Exco) "
				+ "VALUES(?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(ADDUSER);
		ps.setString(1, user.getPersonID());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getTelephone());
		ps.setString(5, user.getTitle());
		ps.setString(6, user.getEmail());
		ps.setString(7, user.getDepartment());
		ps.setBoolean(8, user.isAdmin());
		ps.setBoolean(9, user.isExco());
		r = ps.executeUpdate();
		if (r > 0) {
			commit();
			return user;
		} else
			return null;
	}

	@Override
	public User editUser(User user) throws SQLException {
		int r = 0;
		String EDITUSER = "UPDATE users SET name = ?, password = ?, Telephone = ?, Title = ?, email = ?, Department = ?, "
				+ "Admin = ?, Exco = ? WHERE userID = ?;";
		PreparedStatement ps = con.prepareStatement(EDITUSER);
		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getTelephone());
		ps.setString(4, user.getTitle());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getDepartment());
		ps.setBoolean(7, user.isAdmin());
		ps.setBoolean(8, user.isExco());
		ps.setString(9, user.getPersonID());
		r = ps.executeUpdate();
		if (r > 0) {
			commit();
			return user;
		} else
			return null;
	}

	@Override
	public User deleteUser(User user) throws SQLException {
		int r = 0;
		String DELETEUSER = "DELETE FROM users WHERE userID = ?;";
		PreparedStatement ps = con.prepareStatement(DELETEUSER);
		ps.setString(1, user.getPersonID());
		r = ps.executeUpdate();
		if (r > 0) {
			commit();
			return user;
		} else
			return null;
	}

	@Override
	public ArrayList<User> getAllUsers() throws SQLException, CRRSException {
		String ALLUSERS = "SELECT UserID, name, password, Telephone, Title, email, Department, Admin, Exco FROM users;";
		PreparedStatement ps = con.prepareStatement(ALLUSERS);
		ResultSet rs = ps.executeQuery();
		return userArrayList(rs);
	}

	public ArrayList<User> userArrayList(ResultSet resultSet) throws CRRSException, SQLException {
		ArrayList<User> v = new ArrayList<User>();
		while (resultSet.next()) {
			v.add(constructUser(resultSet));
		}
		if (v.size() > 0)
			return v;
		else
			return null;
	}

	public User constructUser(ResultSet resultSet) throws CRRSException, SQLException {
		User user = new User(resultSet.getString("UserID"), resultSet.getString("name"),
				resultSet.getString("password"), resultSet.getString("Telephone"), resultSet.getString("Title"),
				resultSet.getString("Department"), resultSet.getString("email"), resultSet.getBoolean("Admin"),
				resultSet.getBoolean("exco"));
		return user;
	}

	@Override
	public ArrayList<User> findUser(String expression) throws CRRSException, SQLException {
		String FINDUSERS = "SELECT UserID, name, password, Telephone, Title, email, Department, Admin, Exco "
				+ "FROM users WHERE name LIKE ?;";
		PreparedStatement ps = con.prepareStatement(FINDUSERS);
		ps.setString(1, expression);
		ResultSet rs = ps.executeQuery();
		return userArrayList(rs);
	}

	@Override
	public Reservation addReservation(Reservation reservation) throws SQLException {
		int r = 0;
		String ADDRESERVATION = "INSERT INTO reservations (MeetingTitle,Attendees,BookingDate, MeetingStart, "
				+ "MeetingEnd,StatusID, UserID, RoomID, NotifyDate) VALUES(?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(ADDRESERVATION, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, reservation.getMeetingTitle());
		ps.setInt(2, reservation.getAttendees());
		ps.setDate(3, new java.sql.Date(reservation.getBookingDate().getTime()));
		ps.setDate(4, new java.sql.Date(reservation.getMeetingStart().getTime()));
		ps.setDate(5, new java.sql.Date(reservation.getMeetingEnd().getTime()));
		ps.setInt(6, reservation.getStatus().getStatusID());
		ps.setString(7, reservation.getUser().getPersonID());
		ps.setInt(8, reservation.getRoom().getRoomID());
		ps.setDate(9, new java.sql.Date(reservation.getNotifyDate().getTime()));
		r = ps.executeUpdate();
		if (r > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				reservation.setReservationID(rs.getInt(1));
				addReservationEquipment(reservation);
				commit();
			}
			return reservation;
		} else
			return null;
	}

	public boolean addReservationEquipment(Reservation reservation) throws SQLException {
		int r = 0;
		int count = 0;
		String ADDRESERVATIONEQUIPMENT = "INSERT INTO reservationequipment (ReservationID, EquipmentID, qty) VALUES(?,?,?);";
		PreparedStatement ps = con.prepareStatement(ADDRESERVATIONEQUIPMENT);
		for (Equipment e : reservation.getEquipment()) {
			ps.setInt(1, reservation.getReservationID());
			ps.setInt(2, e.getEquipmentID());
			ps.setInt(3, e.getQty());
			r = ps.executeUpdate();
			if (r > 0) {
				count++;
			}
		}
		if (count == reservation.getEquipment().size()) {
			return true;
		} else
			return false;
	}

	@Override
	public Reservation editReservation(Reservation reservation) throws SQLException {
		int r = 0;
		String EDITRESERVATION = "UPDATE reservations SET MeetingTitle = ?, Attendees = ?, BookingDate = ?, MeetingStart = ?, "
				+ "MeetingEnd = ?,StatusID = ?, UserID = ?, RoomID = ?, NotifyDate = ? WHERE ReservationID = ?;";
		PreparedStatement ps = con.prepareStatement(EDITRESERVATION);
		ps.setString(1, reservation.getMeetingTitle());
		ps.setInt(2, reservation.getAttendees());
		ps.setDate(3, new java.sql.Date(reservation.getBookingDate().getTime()));
		ps.setDate(4, new java.sql.Date(reservation.getMeetingStart().getTime()));
		ps.setDate(5, new java.sql.Date(reservation.getMeetingEnd().getTime()));
		ps.setInt(6, reservation.getStatus().getStatusID());
		ps.setString(7, reservation.getUser().getPersonID());
		ps.setInt(8, reservation.getRoom().getRoomID());
		ps.setDate(9, new java.sql.Date(reservation.getNotifyDate().getTime()));
		ps.setInt(10, reservation.getReservationID());
		r = ps.executeUpdate();
		if (r > 0) {
			if ((deleteReservationEquipment(reservation.getReservationID()))
					&& (addReservationEquipment(reservation))) {
				commit();
				return reservation;
			}
			return null;
		} else
			return null;
	}

	@Override
	public Reservation deleteReservation(Reservation reservation) throws SQLException {
		int r = 0;
		String DELETERESERVATION = "DELETE FROM reservations WHERE ReservationID = ?;";
		PreparedStatement ps = con.prepareStatement(DELETERESERVATION);
		ps.setInt(1, reservation.getReservationID());
		r = ps.executeUpdate();
		if (r > 0) {
			return reservation;
		} else
			return null;
	}

	public boolean deleteReservationEquipment(int reservationID) throws SQLException {
		int r = 0;
		String DELETERESERVATIONEQUIPMENT = "DELETE FROM reservationequipment WHERE ReservationID = ?;";
		PreparedStatement ps = con.prepareStatement(DELETERESERVATIONEQUIPMENT);
		ps.setInt(1, reservationID);
		r = ps.executeUpdate();
		return true;
	}

	@Override
	public ArrayList<Reservation> getAllReservations() throws SQLException, CRRSException {
		String ALLRESERVATIONS = "SELECT r.ReservationID, r.MeetingTitle, r.Attendees, r.BookingDate, r.MeetingStart, r.MeetingEnd, "
				+ "r.StatusID, r.UserID, r.RoomID, NotifyDate, StatusDesc, name, password, Telephone, Title, email, Department, "
				+ "Admin, Exco, RoomLocation, Seats, rm.BuildingID, BuildingName "
				+ "FROM reservations AS r INNER JOIN status AS s INNER JOIN rooms AS rm INNER JOIN buildings AS b "
				+ "INNER JOIN users AS u "
				+ "ON (r.StatusID = s.StatusID AND r.roomID = rm.roomID AND rm.buildingID = b.buildingID AND r.UserID = u.USERID);";
		PreparedStatement ps = con.prepareStatement(ALLRESERVATIONS);
		ResultSet rs = ps.executeQuery();
		return reservationArrayList(rs);
	}

	public ArrayList<Reservation> reservationArrayList(ResultSet resultSet) throws CRRSException, SQLException {
		ArrayList<Reservation> v = new ArrayList<Reservation>();
		while (resultSet.next()) {
			v.add(constructReservation(resultSet));
		}
		if (v.size() > 0)
			return v;
		else
			return null;
	}

	public Reservation constructReservation(ResultSet resultSet) throws CRRSException, SQLException {
		Status status = new Status(resultSet.getInt("StatusID"), resultSet.getString("StatusDesc"));
		User user = constructUser(resultSet);
		Room room = constructRoom(resultSet);
		Reservation reservation = new Reservation(resultSet.getInt("ReservationID"),
				resultSet.getString("MeetingTitle"), resultSet.getInt("Attendees"), resultSet.getDate("BookingDate"),
				resultSet.getDate("MeetingStart"), resultSet.getDate("MeetingEnd"), status, user, room,
				getReservationEquipment(resultSet.getInt("ReservationID")), resultSet.getDate("NotifyDate"));
		return reservation;
	}

	public Status constructStatus(ResultSet resultSet) throws CRRSException, SQLException {
		Status status = new Status(resultSet.getInt("StatusID"), resultSet.getString("StatusDesc"));
		return status;
	}

	public ArrayList<Equipment> getReservationEquipment(int reservationID) throws SQLException, CRRSException {
		String RESERVATIONEQUIPMENT = "SELECT e.EquipmentID, EquipmentDesc, qty"
				+ " FROM equipment AS e INNER JOIN reservationequipment AS re" 
				+ " ON (e.equipmentID = re.equipmentID)"
				+ " WHERE re.reservationID = ?;";
		PreparedStatement ps = con.prepareStatement(RESERVATIONEQUIPMENT);
		ps.setInt(1, reservationID);
		ResultSet rs = ps.executeQuery();
		return equipmentArrayList(rs);
	}

	@Override
	public ArrayList<Reservation> findReservations(User user, Date startDate, Date endDate) throws CRRSException, SQLException {
		String FINDRESERVATIONS = "SELECT r.ReservationID, r.MeetingTitle, r.Attendees, r.BookingDate, r.MeetingStart, "
				+ "r.MeetingEnd, r.StatusID, r.UserID, r.RoomID, NotifyDate, StatusDesc, name, password, Telephone, "
				+ "Title, email, Department, Admin, Exco, RoomLocation, Seats, rm.BuildingID, BuildingName "
				+ "FROM reservations AS r INNER JOIN status AS s INNER JOIN rooms AS rm INNER JOIN buildings AS b "
				+ "INNER JOIN users AS u "
				+ "ON (r.StatusID = s.StatusID AND r.roomID = rm.roomID AND rm.buildingID = b.buildingID AND r.UserID = u.USERID) "
				+ "WHERE r.userID = ? AND ((meetingStart BETWEEN ? AND ?) OR (meetingEnd BETWEEN ? AND ?)) ;";
		PreparedStatement ps = con.prepareStatement(FINDRESERVATIONS);
		ResultSet rs = ps.executeQuery();
		return reservationArrayList(rs);
	}

	@Override
	public ArrayList<Reservation> findReservations(Room room, Date startDate, Date endDate) throws SQLException, CRRSException {
		String FINDRESERVATIONS = "SELECT r.ReservationID, r.MeetingTitle, r.Attendees, r.BookingDate, r.MeetingStart, "
				+ "r.MeetingEnd, r.StatusID, r.UserID, r.RoomID, NotifyDate, StatusDesc, name, password, Telephone, "
				+ "Title, email, Department, Admin, Exco, RoomLocation, Seats, rm.BuildingID, BuildingName "
				+ "FROM reservations AS r INNER JOIN status AS s INNER JOIN rooms AS rm INNER JOIN buildings AS b "
				+ "INNER JOIN users AS u "
				+ "ON (r.StatusID = s.StatusID AND r.roomID = rm.roomID AND rm.buildingID = b.buildingID AND r.UserID = u.USERID) "
				+ "WHERE r.roomID = ? AND ((meetingStart BETWEEN ? AND ?) OR (meetingEnd BETWEEN ? AND ?)) ;";
		PreparedStatement ps = con.prepareStatement(FINDRESERVATIONS);
		ResultSet rs = ps.executeQuery();
		return reservationArrayList(rs);
	}

	@Override
	public ArrayList<Reservation> findReservations(Date startDate, Date endDate) throws SQLException, CRRSException {
		String FINDRESERVATIONS = "SELECT r.ReservationID, r.MeetingTitle, r.Attendees, r.BookingDate, r.MeetingStart, "
				+ "r.MeetingEnd, r.StatusID, r.UserID, r.RoomID, NotifyDate, StatusDesc, name, password, Telephone, "
				+ "Title, email, Department, Admin, Exco, RoomLocation, Seats, rm.BuildingID, BuildingName "
				+ "FROM reservations AS r INNER JOIN status AS s INNER JOIN rooms AS rm INNER JOIN buildings AS b "
				+ "INNER JOIN users AS u "
				+ "ON (r.StatusID = s.StatusID AND r.roomID = rm.roomID AND rm.buildingID = b.buildingID AND r.UserID = u.USERID) "
				+ "WHERE ((meetingStart BETWEEN ? AND ?) OR (meetingEnd BETWEEN ? AND ?)) ;";
		PreparedStatement ps = con.prepareStatement(FINDRESERVATIONS);
		ResultSet rs = ps.executeQuery();
		return reservationArrayList(rs);
	}
	
	public int getReportCancellations(Date startDate, Date endDate) {

        String GETCANCELLATIONS = "Select count(*) as Cancelled from reservations " 
                     + "where StatusID in (Select StatusID from Status where StatusDesc = 'Cancelled')"
                     + "and Meetingstart > ? and MeetingStart < ? ;";
        
        String fromDate = resolveDate(startDate);
        String toDate =resolveDate(endDate);
        

        try {
               PreparedStatement ps = con.prepareStatement(GETCANCELLATIONS);
               ps.setString(1, fromDate);
               ps.setString(2, toDate);
               ResultSet rs = ps.executeQuery();
               rs.next();
               int retInt = rs.getInt("Cancelled");
               return retInt;
        } catch (SQLException e) {
               System.out.println("SQL Error" + e.getMessage());
               return 0;
        }
 }
 
 private String resolveDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String outDate = String.valueOf(cal.get(Calendar.YEAR)) + "-";
        if ((cal.get(Calendar.MONTH)+1) < 10 ) {
               outDate += "0" + (cal.get(Calendar.MONTH)+1) + "-";
        } else {
               outDate +=  (cal.get(Calendar.MONTH)+1) + "-";
        }

        if (cal.get(Calendar.DAY_OF_MONTH) < 10 ) {
               outDate += "0" + cal.get(Calendar.DAY_OF_MONTH);
        } else {
               outDate +=  cal.get(Calendar.DAY_OF_MONTH) ;
        }
        return outDate;
 }

 public ArrayList<RoomOccupation> getReportRoomOccupation(Date startDate, Date endDate ) {
     String GETCANCELLATIONS = "Select b1.BuildingName, room1.RoomLocation, Round((sum(TIMESTAMPDIFF(minute,MeetingStart,MeetingEnd)) / TIMESTAMPDIFF(minute,?,?)*100),2) Occupation from Rooms room1"
                  + " left outer join Reservations res1 on res1.RoomID = room1.RoomID"
                  + " left outer join Buildings b1 on b1.BuildingID = room1.BuildingID"
                  + " where MeetingStart >= ? and MeetingEnd <= ?"
                  + " group by b1.BuildingName,room1.RoomLocation"
                  + " order by Occupation desc;";

     String fromDate = resolveDate(startDate);
     String toDate =resolveDate(endDate);

     try {
            PreparedStatement ps = con.prepareStatement(GETCANCELLATIONS);
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            ps.setString(3, fromDate);
            ps.setString(4, toDate);
            ResultSet rs = ps.executeQuery();
            ArrayList<RoomOccupation> roArrayList = new ArrayList<RoomOccupation>();
            while (rs.next()) {
                  RoomOccupation ro = new RoomOccupation(rs.getString("BuildingName"), rs.getString("RoomLocation"), rs.getString("Occupation"));
                  roArrayList.add(ro);
            }
            return roArrayList;
     } catch (SQLException e) {
            System.out.println("SQL Error" + e.getMessage());
            return null;
     }
 }
 
 public ArrayList<Equipment> getReportEquipment(Date startDate, Date endDate ) {
        String GETCANCELLATIONS = "Select e.EquipmentDesc, count(ree.ReservationID) as count from Equipment e " 
                     + "left outer join ReservationEquipment ree on ree.EquipmentID = e.EquipmentID "
                     + "left outer join Reservations r on ree.ReservationID = r.ReservationID "
                     + "where r.MeetingStart >= ? and r.MeetingStart <= ? "
                     + " group by e.EquipmentDesc "
                     + "having count > 0;";

        String fromDate = resolveDate(startDate);
        String toDate =resolveDate(endDate);

        try {
               PreparedStatement ps = con.prepareStatement(GETCANCELLATIONS);
               ps.setString(1, fromDate);
               ps.setString(2, toDate);

               ResultSet rs = ps.executeQuery();
               ArrayList<Equipment> eArrayList = new ArrayList<Equipment>();
               while (rs.next()) {
                     try {
                            Equipment eq = new Equipment(1, rs.getString("EquipmentDesc"), rs.getInt("Count"));
                            eArrayList.add(eq);
                     } catch (CRRSException e) {

                     }
               }
               return eArrayList;
        } catch (SQLException e) {
               System.out.println("SQL Error" + e.getMessage());
               return null;
        }
 }
 

	public ArrayList<Reservation> findWaitingReservations(Date startDate, Date endDate) throws SQLException, CRRSException {
		String FINDRESERVATIONS = "SELECT r.ReservationID, r.MeetingTitle, r.Attendees, r.BookingDate, r.MeetingStart, "
				+ "r.MeetingEnd, r.StatusID, r.UserID, r.RoomID, NotifyDate, StatusDesc, name, password, Telephone, "
				+ "Title, email, Department, Admin, Exco, RoomLocation, Seats, rm.BuildingID, BuildingName "
				+ "FROM reservations AS r INNER JOIN status AS s INNER JOIN rooms AS rm INNER JOIN buildings AS b "
				+ "INNER JOIN users AS u "
				+ "ON (r.StatusID = s.StatusID AND r.roomID = rm.roomID AND rm.buildingID = b.buildingID AND r.UserID = u.USERID) "
				+ "WHERE ((meetingStart BETWEEN ? AND ?) OR (meetingEnd BETWEEN ? AND ?)) "
				+ "AND  s.StatusID = 3";
		PreparedStatement ps = con.prepareStatement(FINDRESERVATIONS);
		ps.setString(1, resolveDate(startDate));
		ps.setString(2, resolveDate(endDate));
		ps.setString(3, resolveDate(startDate));
		ps.setString(4, resolveDate(endDate));
		ResultSet rs = ps.executeQuery();
		ArrayList<Reservation> resList = new ArrayList<Reservation>();
		
		while (rs.next()) {
			Status status = new Status(rs.getInt("StatusID"), rs.getString("StatusDesc"));
			User user = new User(rs.getString("UserID"), rs.getString("name"), rs.getString("password"), rs.getString("telephone"), rs.getString("title"), rs.getString("department"), rs.getString("email"), rs.getBoolean("admin"), rs.getBoolean("exco"));
			Building building = new Building(rs.getInt("BuildingID"),rs.getString("BuildingName"));
			Room room = new Room(rs.getInt("roomID"), rs.getString("RoomLocation"), rs.getInt("Seats"), building, null);
			ArrayList<Equipment> equipment = getReservationEquipment(rs.getInt("ReservationID"));
			Reservation reservation = new Reservation(rs.getInt("reservationID"), rs.getString("MeetingTitle"), rs.getInt("Attendees"), rs.getDate("BookingDate"), rs.getDate("meetingStart"), rs.getDate("meetingEnd"), status, user, room, equipment, rs.getDate("notifyDate"));
			resList.add(reservation);
			
		}
		return resList;
	}

}
