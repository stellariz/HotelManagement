package ru.nsu.hotel_db.hotels;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Room;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findRoomByHotelHotelId(Long hotelId);

    Optional<Room> findRoomByRoomNumberAndHotelHotelId(Integer roomNumber, Long hotelId);

    @Query(value = "SELECT * FROM ROOM left join CLIENT C2 on ROOM.ROOM_ID = C2.ROOM_ID where CLIENT_ID is null and HOTEL_ID=?1",
            nativeQuery = true)
    List<Room> findFreeRoomsInHotel(Long hotelId);

    /**
     * Найти все комнаты, незабронированные на текущую дату по условиям
     *
     * @param capacity
     * @param floor
     * @param hotelClass
     * @return
     */
    @Query(value = "select * from ROOM join HOTEL H on H.HOTEL_ID = ROOM.HOTEL_ID where ROOM_ID not in (select B.ROOM_ID from ROOM join BOOKING B on ROOM.ROOM_ID = B.ROOM_ID where START_DATE < ?1 or END_DATE > ?2) and CAPACITY=?3 and FLOOR=?4 and HOTEL_CLASS=?5", nativeQuery = true)
    List<Room> findRoomByCapacityAndFloorAndHotelHotelClass(LocalDate startBookingDate, LocalDate endBookingDate, Integer capacity, Integer floor, Integer hotelClass);
}
