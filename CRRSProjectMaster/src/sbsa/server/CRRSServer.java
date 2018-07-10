package sbsa.server;


import java.util.*;

import sbsa.beans.Building;
import sbsa.beans.Equipment;
import sbsa.beans.Reservation;
import sbsa.beans.Room;
import sbsa.beans.User;
import sbsa.dao.DataAccessObject;
import sbsa.exceptions.CRRSException;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

public class CRRSServer {
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private DataAccessObject dao;

	public static void main (String[] args)
	{
		new CRRSServer();
	}

	public CRRSServer() {
		//String daoServer, daoDatabase, daoPort,daoUsername,daoPassword;
		int serverPort = 9999;
		File file = new File("resources/Server.properties");
		try {
			FileInputStream input = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(input);
			serverPort=Integer.parseInt(properties.getProperty("ServerPort"));
			input.close();

		} catch (FileNotFoundException  e) {
			System.out.println("Properties file - File Not Found Exception occured : " + e.toString());
			System.exit(0);	
		} catch ( IOException e) {
			System.out.println("Properties file - IO Exception occured : " + e.toString());
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Properties file - General Exception occured : " + e.toString());
			System.exit(0);
		}

		dao = new DataAccessObject();
		try {
			serverSocket = new ServerSocket(serverPort);
			while(true) {
				System.out.println("=========== Server is listening for connections ===========");
				socket = serverSocket.accept();
				CRRSSockets socThread =  new CRRSSockets(socket);
				socThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Server Thread - Failed to open a server socket : " + e.toString());
			System.exit(0);
		}


	}

	class CRRSSockets extends Thread {


		private Socket socket;
		private ObjectOutputStream out = null;
		private ObjectInputStream in= null;


		public CRRSSockets(Socket socket) {
			this.socket = socket;
			System.out.println("\t"+ socket.getInetAddress().getHostAddress() + ":" + socket.getPort() +  " - Starting socket");
		}

		public void run() {
			String strInput = "";
			try {
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());

			} catch (IOException e) {
				System.out.println("Sockets Thread - IOException : " + e.toString());
			}

			try {
				while ((strInput =(String)in.readObject()) != "-=!=-") {
					try {
					System.out.println("Input: " + strInput);
					switch(strInput) {
					case "-=!=-" :
						System.out.println("\t"+ socket.getInetAddress().getHostAddress() + ":" + socket.getPort() +  " - Closing socket");
						break;
					case "addEquipment" :
						out.writeObject(dao.addEquipment((Equipment)in.readObject()));
						break;
					case "editEquipment" :
						out.writeObject(dao.editEquipment((Equipment)in.readObject()));
						break;
					case "deleteEquipment" :
						out.writeObject(dao.deleteEquipment((Equipment)in.readObject()));
						break;
					case "getAllEquipment" :
						out.writeObject(dao.getAllEquipment());
						break;
					case "findEquipment" :
						out.writeObject(dao.findEquipment((String)in.readObject()));
						break;
					case "addRoom" :
						out.writeObject(dao.addRoom((Room)in.readObject()));
						break;
					case "editRoom" :
						out.writeObject(dao.editRoom((Room)in.readObject()));
						break;
					case "deleteRoom" :
						out.writeObject(dao.deleteRoom((Room)in.readObject()));
						break;
					case "getAllRooms" :
						out.writeObject(dao.getAllRooms());
						break;
					case "findRooms" :
						out.writeObject(dao.findRooms((ArrayList<Equipment>)in.readObject(),(Date)in.readObject(),(Date)in.readObject(),Integer.parseInt((String)in.readObject()),(Building)in.readObject()));
						break;
					case "addBuilding" :
						out.writeObject(dao.addBuilding((Building)in.readObject()));
						break;
					case "editBuilding" :
						out.writeObject(dao.editBuilding((Building)in.readObject()));
						break;
					case "deleteBuilding" :
						out.writeObject(dao.deleteBuilding((Building)in.readObject()));
						break;
					case "getAllBuildings" :
						out.writeObject(dao.getAllBuildings());
						break;
					case "login" :
						System.out.println(" inside login");

						String u1 = (String)in.readObject();
						System.out.println(" inside login u1" + u1);

						String p1 = (String)in.readObject();
						System.out.println(" inside login" + p1);

						out.writeObject(dao.login(u1,p1));

						break;
					case "addUser" :
						out.writeObject(dao.addUser((User)in.readObject()));
						break;
					case "editUser" :
						out.writeObject(dao.editUser((User)in.readObject()));
						break;
					case "deleteUser" :
						out.writeObject(dao.deleteUser((User)in.readObject()));
						break;
					case "getAllUsers" :
						out.writeObject(dao.getAllUsers());
						break;
					case "findUser" :
						out.writeObject(dao.findUser((String)in.readObject()));
						break;
					case "addReservation" :
						out.writeObject(dao.addReservation((Reservation)in.readObject()));
						break;
					case "editReservation" :
						out.writeObject(dao.editReservation((Reservation)in.readObject()));
						break;
					case "deleteReservation" :
						out.writeObject(dao.deleteReservation((Reservation)in.readObject()));
						break;
					case "getAllReservations" :
						out.writeObject(dao.getAllReservations());
						break;
					case "findReservationsByUser" :
						out.writeObject(dao.findReservations((User)in.readObject(), (Date)in.readObject(), (Date)in.readObject()));
						break;
					case "findReservationsByRoom" :
						out.writeObject(dao.findReservations((Room)in.readObject(), (Date)in.readObject(), (Date)in.readObject()));
						break;
					case "findReservationsByDate" :
						out.writeObject(dao.findReservations((Date)in.readObject(), (Date)in.readObject()));
						break;
					case "getReportCancellations" :
						out.writeObject(dao.getReportCancellations((Date)in.readObject(), (Date)in.readObject()));
						break;	
					case "getReportRoomOccupation" :
						out.writeObject(dao.getReportRoomOccupation((Date)in.readObject(), (Date)in.readObject()));
						break;	
					case "getReportEquipment" :
						out.writeObject(dao.getReportEquipment((Date)in.readObject(), (Date)in.readObject()));
						break;	
					case "findWaitingReservations" :
						out.writeObject(dao.findWaitingReservations((Date)in.readObject(), (Date)in.readObject()));
						break;
					default :
						System.out.println("Invalid request : " + strInput);
						break;
					}
					out.flush();
					} catch (IOException e) {
						System.out.println("Sockets Thread - IOException : " + e.toString());
						try {
							out.writeObject(e);
						} catch (IOException q) {
							System.out.println("Sockets Thread Comms Error - " + e.toString());
						}
					} catch (ClassNotFoundException e) {
						System.out.println("Sockets Thread - ClassNotFoundException : " + e.toString());
						try {
							out.writeObject(e);
						} catch (IOException q) {
							System.out.println("Sockets Thread  Comms Error - " + e.toString());
						}
					} catch (SQLException e) {
						System.out.println("Sockets Thread - SQL Exception : " + e.toString());
						try {
							out.writeObject(e);
						} catch (IOException q) {
							System.out.println("Sockets Thread  Comms Error - " + e.toString());
						}
					} catch (CRRSException e) {
						System.out.println("Sockets Thread - Bean Error : " + e.toString());
						try {
							out.writeObject(e);
						} catch (IOException q) {
							System.out.println("Sockets Thread  Comms Error - " + e.toString());
						}
					} 
				}
				out.close();
				in.close();
				socket.close();
			}catch (IOException e) {
				System.out.println("Sockets Thread - IOException : " + e.toString());
			} catch (ClassNotFoundException e) {
				System.out.println("Sockets Thread - ClassNotFoundException : " + e.toString());
			} 

		}


	}

}
