package ru.nsu.hotel_db.hotels.hotelServices;

import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.HotelsService;

import java.util.List;
import java.util.Optional;

public interface HotelServiceService {
    List<HotelsService> getServicesInHotel(Long hotelId);
    HotelsService addNewService(ServiceDTO serviceDTO, Hotel hotel) throws IllegalArgumentException;
    Optional<HotelsService> getHotelsServiceById(Long serviceId);
    void removeAllServicesFromHotel(Long hotelId);
    void removeServiceFromHotel(Long serviceId);
}
