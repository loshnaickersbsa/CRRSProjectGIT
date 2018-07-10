Truncate table Buildings;
Truncate table Rooms;
Truncate table Equipment;
Truncate Table Users;
Truncate Table Status;
Truncate Table RoomEquipment;
Truncate Table Reservations;
Truncate Table ReservationEquipment;

commit;

insert into Buildings(BuildingName) values ('5 Simmonds');
insert into Buildings(BuildingName) values ('6 Simmonds');
insert into Buildings(BuildingName) values ('3 Simmonds');
insert into Buildings(BuildingName) values ('Rosebank');

insert into Rooms(RoomLocation,Seats,BuildingID) values('420',10,1);
insert into Rooms(RoomLocation,Seats,BuildingID) values('321',7,1);
insert into Rooms(RoomLocation,Seats,BuildingID) values('128',20,1);
insert into Rooms(RoomLocation,Seats,BuildingID) values('612',16,1);
insert into Rooms(RoomLocation,Seats,BuildingID) values('111',8,2);
insert into Rooms(RoomLocation,Seats,BuildingID) values('222',10,2);
insert into Rooms(RoomLocation,Seats,BuildingID) values('333',10,2);
insert into Rooms(RoomLocation,Seats,BuildingID) values('101',6,3);
insert into Rooms(RoomLocation,Seats,BuildingID) values('102',14,3);
insert into Rooms(RoomLocation,Seats,BuildingID) values('103',18,3);
insert into Rooms(RoomLocation,Seats,BuildingID) values('104',22,3);
insert into Rooms(RoomLocation,Seats,BuildingID) values('Zebra',7,4);
insert into Rooms(RoomLocation,Seats,BuildingID) values('Camel',7,4);
insert into Rooms(RoomLocation,Seats,BuildingID) values('Lion',11,4);
insert into Rooms(RoomLocation,Seats,BuildingID) values('Cheetah',17,4);
insert into Rooms(RoomLocation,Seats,BuildingID) values('Leopard',13,4);

insert into Equipment(EquipmentDesc) values ('Whiteboard');
insert into Equipment(EquipmentDesc) values ('42" TV');
insert into Equipment(EquipmentDesc) values ('55" TV');
insert into Equipment(EquipmentDesc) values ('65" TV');
insert into Equipment(EquipmentDesc) values ('Projector');
insert into Equipment(EquipmentDesc) values ('Laptop Computer');
insert into Equipment(EquipmentDesc) values ('Power Cables');
insert into Equipment(EquipmentDesc) values ('Network Cables');
insert into Equipment(EquipmentDesc) values ('Microsoft Office');
insert into Equipment(EquipmentDesc) values ('Microsoft Excel');
insert into Equipment(EquipmentDesc) values ('Microsoft Powerpoint');
insert into Equipment(EquipmentDesc) values ('Desktop Computer');
insert into Equipment(EquipmentDesc) values ('Mouse');
insert into Equipment(EquipmentDesc) values ('Keyboard');
insert into Equipment(EquipmentDesc) values ('Tape Recorder');
insert into Equipment(EquipmentDesc) values ('Slide Projector');
insert into Equipment(EquipmentDesc) values ('PA System');
insert into Equipment(EquipmentDesc) values ('Microphone');
insert into Equipment(EquipmentDesc) values ('Microphone Stand');
insert into Equipment(EquipmentDesc) values ('Amplifier');
insert into Equipment(EquipmentDesc) values ('Speakers');

       
insert into Users(UserID,name,password,Telephone,Title,email,Department,admin,Exco) values ('S001','Stephan Potgieter', 'stephan','082 123 4567','Mr','Stephan.Potgieter@standardbank.co.za','ITO',true,true);
insert into Users(UserID,name,password,Telephone,Title,email,Department,admin,Exco) values ('T001','Tony Clifford', 'tony','082 123 7654','Mr','Tony.Clifford@standardbank.co.za','ITO',true,true);
insert into Users(UserID,name,password,Telephone,Title,email,Department,admin,Exco) values ('L001','Losh Naicker', 'losh','082 321 4567','Mr','losh.naicker@standardbank.co.za','ITO',true,true);
insert into Users(UserID,name,password,Telephone,Title,email,Department,admin,Exco) values ('P001','Philile Molokomme', 'philile','082 111 2222','Ms','Philile.Molokomme@standardbank.co.za','ITO',true,true);
insert into Users(UserID,name,password,Telephone,Title,email,Department,admin,Exco) values ('T002','Test User', 'test','082 222 1111','Mr','spotgieter@mweb.co.za','Testing Unit',false,false);
insert into Users(UserID,name,password,Telephone,Title,email,Department,admin,Exco) values ('E001','Exco User', 'exco','082 333 1111','Mr','spotgieterandroid@gmail.co.za','ITO',false,true);

Insert into Status(StatusDesc) values ('Booked');
insert into Status(StatusDesc) values ('Cancelled');
insert into Status(StatusDesc) values ('WaitingList');
            
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (1,1,2);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (2,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (3,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (4,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (6,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (8,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (9,1,3);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (10,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (11,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (12,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (13,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (14,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (15,1,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (2,2,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (4,2,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (7,3,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (8,4,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (10,7,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (11,4,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (13,3,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (15,9,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (15,15,1);
Insert into RoomEquipment(RoomID, EquipmentID,qty) values (15,21,1);


            
Insert Into Reservations(MeetingTitle,Attendees,BookingDate,MeetingStart,MeetingEnd,StatusID,UserID,RoomID,notifydate) values ('General Meeting',5,'2018-07-02 09:00','2018-07-06 09:00','2018-07-06 11:00',1,'T002',1,null);
Insert Into Reservations(MeetingTitle,Attendees,BookingDate,MeetingStart,MeetingEnd,StatusID,UserID,RoomID,notifydate) values ('Finding a Solution',3,'2018-07-03 11:20','2018-07-06 12:00','2018-07-06 13:00',1,'T002',2,null);
Insert Into Reservations(MeetingTitle,Attendees,BookingDate,MeetingStart,MeetingEnd,StatusID,UserID,RoomID,notifydate) values ('Secret Meeting',7,'2018-07-04 14:00','2018-07-06 10:00','2018-07-06 15:00',2,'E001',16,null);
Insert Into Reservations(MeetingTitle,Attendees,BookingDate,MeetingStart,MeetingEnd,StatusID,UserID,RoomID,notifydate) values ('Important Meeting',15,'2018-07-05 15:43','2018-07-09 11:00','2018-07-10 17:00',1,'E001',15,null);
Insert Into Reservations(MeetingTitle,Attendees,BookingDate,MeetingStart,MeetingEnd,StatusID,UserID,RoomID,notifydate) values ('Exco Meeting',15,'2018-07-08 15:43','2018-07-11 08:00','2018-07-11 09:00',1,'E001',15,null);

Insert Into ReservationEquipment(ReservationID,EquipmentID,qty) values (1,3,1);
Insert Into ReservationEquipment(ReservationID,EquipmentID,qty) values (4,4,2);
Insert Into ReservationEquipment(ReservationID,EquipmentID,qty) values (4,5,1);


