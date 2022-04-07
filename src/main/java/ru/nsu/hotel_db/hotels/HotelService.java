package ru.nsu.hotel_db.hotels;

import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.Room;
import ru.nsu.hotel_db.Entitiy.HotelsService;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    List<Room> getRoomsInHotel(Long hotelId);
    Hotel addNewHotel(HotelDTO hotelDTO);
    List<HotelsService> getServicesInHotel(Long hotelId);
    HotelsService addNewService(ServiceDTO serviceDTO, Long hotelId);
}
