/*
Select * from Buildings;
Select * from Rooms;
Select * from Equipment;
Select * from Users;
Select * from Status;
Select * from RoomEquipment;
Select * from Reservations;
Select * from ReservationEquipment;

*/
Select r.RoomID, r.RoomLocation, r.Seats,b.BuildingName, e.EquipmentDesc
from Rooms r
inner join Buildings b on b.BuildingID = r.BuildingID
left outer join
RoomEquipment re on r.RoomID = re.RoomID
left outer join Equipment e on e.EquipmentID = re.EquipmentID;