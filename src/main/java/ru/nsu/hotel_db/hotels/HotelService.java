package ru.nsu.hotel_db.hotels;

import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.Room;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    List<Hotel> getAllHotels();
    List<Room> getRoomsInHotel(Long hotelId);
    Hotel addNewHotel(HotelDTO hotelDTO) throws IllegalArgumentException;
    Optional<Hotel> getHotelById(Long hotelId);
    void removeHotelById(Long hotelId);
}
