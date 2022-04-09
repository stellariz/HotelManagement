package ru.nsu.hotel_db.hotels;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findRoomByHotelHotelId(Long hotelId);
    Optional<Room> findRoomByRoomNumberAndHotelHotelId(Integer roomNumber, Long hotelId);
}
