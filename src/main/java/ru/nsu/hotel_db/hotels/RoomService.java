package ru.nsu.hotel_db.hotels;

import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.Room;

public interface RoomService {
    Room addNewRoom(RoomDTO roomDTO, Hotel hotel) throws IllegalArgumentException;
    void removeAllRoomsFromHotel(Long hotelId);
}
