package ru.nsu.hotel_db.hotels;

import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.HotelsService;

import java.util.List;

public interface HotelServiceService {
    List<HotelsService> getServicesInHotel(Long hotelId);
    HotelsService addNewService(ServiceDTO serviceDTO, Hotel hotel) throws IllegalArgumentException;
    void removeAllServicesFromHotel(Long hotelId);
    void removeServiceFromHotel(Long serviceId);
}
