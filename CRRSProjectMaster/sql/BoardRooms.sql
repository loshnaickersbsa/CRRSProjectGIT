Drop Database BoardRooms;
commit;

create database BoardRooms;
commit;
use BoardRooms;
commit;
create Table Buildings (
			BuildingID int unsigned not null primary key Auto_Increment,
            BuildingName varchar(50) not null);
commit;
Create Table Rooms (
			RoomID int unsigned not null Primary Key Auto_Increment,
            RoomLocation varchar(100) not null,
            Seats int not null,
            BuildingID int not null);
commit;
Create Table RoomEquipment (
			RoomID int unsigned not null,
            EquipmentID int unsigned not null,
            qty int not null,
            Primary Key (RoomID, EquipmentID));
commit;
Create Table ReservationEquipment (
			ReservationID int unsigned not null,
            EquipmentID int unsigned not null,
            qty int unsigned not null,
            Primary Key (ReservationID, EquipmentID));
commit;
Create Table Status (
			StatusID int unsigned not null Primary Key auto_increment,
            StatusDesc varchar(20) not null);
commit;
Create Table Reservations (
			ReservationID int unsigned not null Primary Key auto_increment,
            MeetingTitle varchar(100) not null,
            Attendees int not null,
            BookingDate datetime not null,
            MeetingStart datetime not null,
            MeetingEnd datetime not null,
            StatusID int not null,
            UserID char(4) not null,
            RoomID int not null,
            Notifydate datetime);
commit;
Create Table Equipment (
			EquipmentID int unsigned not null Primary Key auto_increment,
            EquipmentDesc varchar(100) not null);
commit;
Create Table Users (
		UserID char(4) not null Primary Key,
		name varchar(50) not null,
		password varchar(50) not null,
		Telephone varchar(15) not null,
		Title varchar(50) not null,
		email varchar(100) not null,
		Department varchar(50) not null,
        Admin bool not null,
		Exco bool default false);