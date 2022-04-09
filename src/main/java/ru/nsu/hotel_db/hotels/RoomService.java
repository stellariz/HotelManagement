package ru.nsu.hotel_db.hotels;

import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.Room;

import java.util.Optional;

public interface RoomService {
    Optional<Room> findRoomById(Long roomId);
    Room addNewRoom(RoomDTO roomDTO, Hotel hotel) throws IllegalArgumentException;
    void removeAllRoomsFromHotel(Long hotelId);
}