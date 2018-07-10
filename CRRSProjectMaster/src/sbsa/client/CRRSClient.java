package sbsa.client;

import sbsa.beans.*;
import sbsa.exceptions.*;
import sbsa.interfaces.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
//edit -losh
public class CRRSClient implements CRRSInterface {

	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in= null;

	public CRRSClient() throws IOException {
		String serverAddress = "127.0.0.1";
		int serverPort = 9999;
		File file = new File("resources/Client.properties");
		try {
			FileInputStream input = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(input);
			serverAddress= properties.getProperty("ServerAddress");
			serverPort=Integer.parseInt(properties.getProperty("ServerPort"));
			input.close();

		} catch (FileNotFoundException  e) {
			System.out.println("Properties file - File Not Found Exception occured : " + e.toString());
			System.exit(0);	
		} catch (Exception e) {
			System.out.println("Properties file - General Exception occured : " + e.toString());
			System.exit(0);
		}


		socket = new Socket(serverAddress,serverPort);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());


	}

	@Override
	public Equipment addEquipment(Equipment equipment) {
		
		
		try {
			out.writeObject("addEquipment");
			out.flush();
			out.writeObject(equipment);
			out.flush();
			Equipment e = (Equipment)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public Equipment editEquipment(Equipment equipment) {
		try {
			out.writeObject("editEquipment");
			out.flush();
			out.writeObject(equipment);
			out.flush();
			Equipment e = (Equipment)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Equipment deleteEquipment(Equipment equipment) {
		try {
			out.writeObject("deleteEquipment");
			out.flush();
			out.writeObject(equipment);
			out.flush();
			Equipment e = (Equipment)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Equipment> getAllEquipment() {
		try {
			out.writeObject("getAllEquipment");
			out.flush();

			ArrayList<Equipment> e = (ArrayList<Equipment>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Equipment> findEquipment(String description) {
		try {
			out.writeObject("getAllEquipment");
			out.flush();
			out.writeObject(description);
			out.flush();
			ArrayList<Equipment> e = (ArrayList<Equipment>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Room addRoom(Room room) {
		try {
			out.writeObject("addRoom");
			out.flush();
			out.writeObject(room);
			out.flush();
			Room e = (Room)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Room editRoom(Room room) {
		try {
			out.writeObject("editRoom");
			out.flush();
			out.writeObject(room);
			out.flush();
			Room e = (Room)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Room deleteRoom(Room room) {
		try {
			out.writeObject("deleteRoom");
			out.flush();
			out.writeObject(room);
			out.flush();
			Room e = (Room)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Room> getAllRooms() {
		try {
			out.writeObject("getAllRoom");
			out.flush();
			ArrayList<Room> e = (ArrayList<Room>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Room> findRooms(ArrayList<Equipment> equipment, Date startDate, Date endDate, int duration, Building building) {
		try {
			out.writeObject("findRooms");
			out.flush();
			out.writeObject(equipment);
			out.flush();
			out.writeObject(startDate);
			out.flush();
			out.writeObject(endDate);
			out.flush();
			out.writeObject(building);
			out.flush();
			ArrayList<Room> e = (ArrayList<Room>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Building addBuilding(Building building) {
		try {
			out.writeObject("addBuilding");
			out.flush();
			out.writeObject(building);
			out.flush();
			Building e = (Building)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Building editBuilding(Building building) {
		try {
			out.writeObject("editBuilding");
			out.flush();
			out.writeObject(building);
			out.flush();
			Building e = (Building)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Building deleteBuilding(Building building) {
		try {
			out.writeObject("deleteBuilding");
			out.flush();
			out.writeObject(building);
			out.flush();
			Building e = (Building)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Building> getAllBuildings() {
		try {
			out.writeObject("getAllBuildings");
			out.flush();
			
			ArrayList<Building> e = (ArrayList<Building>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User login(String userID, String password) {
		try {
			out.writeObject("login");
			out.flush();
			out.writeObject(userID);
			out.flush();
			out.writeObject(password);
			out.flush();
			User e = (User)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User addUser(User user) {
		try {
			out.writeObject("addUser");
			out.flush();
			out.writeObject(user);
			out.flush();
			User e = (User)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User editUser(User user) {
		try {
			out.writeObject("editUser");
			out.flush();
			out.writeObject(user);
			out.flush();
			User e = (User)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User deleteUser(User user) {
		try {
			out.writeObject("deleteUser");
			out.flush();
			out.writeObject(user);
			out.flush();
			User e = (User)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<User> getAllUsers() {
		try {
			out.writeObject("getAllUsers");
			out.flush();
			ArrayList<User> e = (ArrayList<User>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<User> findUser(String name) {
		try {
			out.writeObject("findUser");
			out.flush();
			out.writeObject(name);
			out.flush();
			ArrayList<User> e = (ArrayList<User>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Reservation addReservation(Reservation reservation) {
		try {
			out.writeObject("addReservation");
			out.flush();
			out.writeObject(reservation);
			out.flush();
			Reservation e = (Reservation)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Reservation editReservation(Reservation reservation) {
		try {
			out.writeObject("editReservation");
			out.flush();
			out.writeObject(reservation);
			out.flush();
			Reservation e = (Reservation)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Reservation deleteReservation(Reservation reservation) {
		try {
			out.writeObject("deleteReservation");
			out.flush();
			out.writeObject(reservation);
			out.flush();
			Reservation e = (Reservation)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Reservation> getAllReservations() {
		try {
			out.writeObject("deleteReservation");
			out.flush();
			ArrayList<Reservation> e = (ArrayList<Reservation>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Reservation> findReservations(User user, Date startDate, Date endDate) {
		try {
			out.writeObject("findReservationsByUser");
			out.flush();
			out.writeObject(user);
			out.flush();
			out.writeObject(startDate);
			out.flush();
			out.writeObject(endDate);
			out.flush();
			ArrayList<Reservation> e = (ArrayList<Reservation>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Reservation> findReservations(Room room, Date startDate, Date endDate) {
		try {
			out.writeObject("findReservationsByRoom");
			out.flush();
			out.writeObject(room);
			out.flush();
			out.writeObject(startDate);
			out.flush();
			out.writeObject(endDate);
			out.flush();
			ArrayList<Reservation> e = (ArrayList<Reservation>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Reservation> findReservations(Date startDate, Date endDate) {
		try {
			out.writeObject("findReservationsByDate");
			out.flush();

			out.writeObject(startDate);
			out.flush();
			out.writeObject(endDate);
			out.flush();
			ArrayList<Reservation> e = (ArrayList<Reservation>)in.readObject();
			return e;
		} catch (Exception e) {
			return null;
		}
	}
	
	public int getReportCancellations(Date startDate, Date endDate) {
		try {
			out.writeObject("getReportCancellations");
			out.flush();

			out.writeObject(startDate);
			out.flush();
			out.writeObject(endDate);
			out.flush();
			int e = Integer.parseInt(in.readObject().toString());
			return e;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public ArrayList<RoomOccupation> getReportRoomOccupation(Date startDate, Date endDate ) {
		try {
			out.writeObject("getReportRoomOccupation");
			out.flush();

			out.writeObject(startDate);
			out.flush();
			out.writeObject(endDate);
			out.flush();
		
			return (ArrayList<RoomOccupation>)in.readObject();
		} catch (Exception e) {
			return null;
		}
	}
	
	public ArrayList<Equipment> getReportEquipment(Date startDate, Date endDate ) {
		try {
			out.writeObject("getReportEquipment");
			out.flush();

			out.writeObject(startDate);
			out.flush();
			out.writeObject(endDate);
			out.flush();
		
			return (ArrayList<Equipment>)in.readObject();
		} catch (Exception e) {
			return null;
		}
	}

}
