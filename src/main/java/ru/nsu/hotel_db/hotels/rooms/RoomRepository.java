package ru.nsu.hotel_db.hotels.rooms;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findRoomByHotelHotelId(Long hotelId);

    Optional<Room> findRoomByRoomNumberAndHotelHotelId(Integer roomNumber, Long hotelId);

    @Query(value = "SELECT * FROM ROOM WHERE ROOM_ID not in (select ROOM.ROOM_ID from ROOM join CLIENT C2 on ROOM.ROOM_ID = C2.ROOM_ID where CHECK_OUT_TIME > SYSDATE) and ROOM_ID not in (select B.ROOM_ID from ROOM join BOOKING B on ROOM.ROOM_ID = B.ROOM_ID where ((BOOKING_START_DATE <= ?1 and BOOKING_END_DATE >= ?2) or (BOOKING_START_DATE >= ?1 and BOOKING_END_DATE <= ?2)))",
            nativeQuery = true)
    List<Room> findFreeRooms(LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT * FROM ROOM join HOTEL H on H.HOTEL_ID = ROOM.HOTEL_ID WHERE ROOM_ID not in (select ROOM.ROOM_ID from ROOM join CLIENT C2 on ROOM.ROOM_ID = C2.ROOM_ID where CHECK_OUT_TIME > SYSDATE) and ROOM_ID not in (select B.ROOM_ID from ROOM join BOOKING B on ROOM.ROOM_ID = B.ROOM_ID where ((BOOKING_START_DATE <= ?1 and BOOKING_END_DATE >= ?2) or (BOOKING_START_DATE >= ?1 and BOOKING_END_DATE <= ?2))) and CAPACITY=?3 and FLOOR=?4 and HOTEL_CLASS=?5",
            nativeQuery = true)
    List<Room> findRoomByCapacityAndFloorAndHotelHotelClass(LocalDate startBookingDate, LocalDate endBookingDate, Integer capacity, Integer floor, Integer hotelClass);

    @Query(value = "SELECT count(*) as number_of_free_rooms from ( SELECT * FROM ROOM WHERE ROOM_ID not in (select ROOM.ROOM_ID from ROOM join CLIENT C2 on ROOM.ROOM_ID = C2.ROOM_ID where CHECK_OUT_TIME > SYSDATE) and ROOM_ID not in (select B.ROOM_ID from ROOM join BOOKING B on ROOM.ROOM_ID = B.ROOM_ID where ((BOOKING_START_DATE <= ?1 and BOOKING_END_DATE >= ?2) or (BOOKING_START_DATE >= ?1 and BOOKING_END_DATE <= ?2))) )", nativeQuery = true)
    Integer getNumberOfFreeRooms(LocalDate startDate, LocalDate endDate);

    @Query(value = "select * from ROOM where ROOM_ID in (select ROOM_ID from (select C2.ROOM_ID, CHECK_OUT_TIME, row_number() over (PARTITION BY C2.ROOM_ID order by CHECK_IN_TIME desc ) as row_number from ROOM join CLIENT C2 on ROOM.ROOM_ID = C2.ROOM_ID) where row_number = 1 and CHECK_OUT_TIME <= ?)", nativeQuery = true)
    List<Room> findRoomsThatWillBeFreeOnDate(LocalDate freeDate);

    @Query(value = "SELECT ROUND(100.0 * COUNT(UNIQUE BR.ROOM_ID) / COUNT(UNIQUE ROOM.ROOM_ID), 2) as percentage_booked_rooms from ROOM left join BOOKING BR on ROOM.ROOM_ID = BR.ROOM_ID",
            nativeQuery = true)
    Double countPercentageBookedRooms();
}
