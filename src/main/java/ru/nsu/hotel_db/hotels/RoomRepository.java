package ru.nsu.hotel_db.hotels;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Room;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    @Query(value = "select * from ROOM join BUILDING B on B.BUILDING_ID=ROOM.BUILDING_ID " +
            "join DEPARTMENT D on D.DEP_ID = B.DEP_ID " +
            "join HOTEL H on H.HOTEL_ID = D.HOTEL_ID " +
            "left join BOOKED_ROOMS BR on ROOM.ROOM_ID = BR.ROOM_ID " +
            "left join BOOKING B on B.BOOKING_ID = BR.BOOKING_ID " +
            "where ROOM.ROOM_ID not in (select ROOM_ID from CLIENT WHERE LIVING_STATUS = 1) " +
            "and H.HOTEL_ID=?1 "+
            "and (B.BOOKING_ID is null " +
            "or SYSDATE < START_DATE)", nativeQuery = true)
    List<Room> findFreeRoomsInHotel(Long hotelId);
}
