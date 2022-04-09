package ru.nsu.hotel_db.hotels;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findRoomByHotelHotelId(Long hotelId);

    Optional<Room> findRoomByRoomNumberAndHotelHotelId(Integer roomNumber, Long hotelId);

    @Query(value = "SELECT * FROM ROOM left join CLIENT C2 on ROOM.ROOM_ID = C2.ROOM_ID where CLIENT_ID is null and HOTEL_ID=?1",
            nativeQuery = true)
    List<Room> findFreeRoomsInHotel(Long hotelId);

}
