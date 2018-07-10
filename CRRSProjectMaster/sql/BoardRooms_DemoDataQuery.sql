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


-- Meeting information
select r.ReservationID, r.MeetingTitle, r.Attendees, r.BookingDate, r.MeetingStart, r.MeetingEnd, s.StatusDesc, u.Name, ro.RoomLocation, ro.Seats, b.BuildingName
from reservations r
inner join Status s on s.StatusID = r.StatusID
inner join Users u on u.UserID = r.UserID
inner join Rooms ro on ro.RoomID = r.RoomID
inner join Buildings b on b.BuildingID = ro.BuildingID;

-- Report 1 - Cancelled Meetings count
use boardrooms;
commit;
Select count(*) as Cancelled from reservations
where StatusID in (Select StatusID from Status where StatusDesc = 'Cancelled')
and Meetingstart > '2018-07-02' and MeetingStart < '2018-09-16' ;

-- Report 2 - Occupation for time period


Select @reportStart:='2018-07-01 00:00', @reportEnd:='2018-07-20 23:59';
Select b1.BuildingName, room1.RoomLocation, Round((sum(TIMESTAMPDIFF(minute,MeetingStart,MeetingEnd)) / TIMESTAMPDIFF(minute,@reportStart,@reportEnd)*100),2) Occupation from Rooms room1
left outer join Reservations res1 on res1.RoomID = room1.RoomID
left outer join Buildings b1 on b1.BuildingID = room1.BuildingID
where MeetingStart >= @reportStart and MeetingEnd <= @reportEnd
group by b1.BuildingName,room1.RoomLocation
order by Occupation desc;


-- Report 3 - Requested Equipment

Select e.EquipmentDesc, count(ree.ReservationID) as count from Equipment e
left outer join ReservationEquipment ree on ree.EquipmentID = e.EquipmentID 
left outer join Reservation r on ree.ReservationID = r.ReservationID
where r.MeetingStart >= '2017-07-02' and r.MeetingStart <= '2017-07-12'
group by e.EquipmentDesc
having count > 0;

-- left outer join RoomEquipment roe on roe.EquipmentID = e.EquipmentID










