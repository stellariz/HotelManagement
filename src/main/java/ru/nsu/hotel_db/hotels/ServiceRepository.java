package ru.nsu.hotel_db.hotels;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.HotelsService;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends CrudRepository<HotelsService, Long> {
    List<HotelsService> findServiceByHotelHotelId(Long hotelId);
    Optional<HotelService> findHotelsServiceByNameAndHotel(String serviceName, Hotel hotel);
}
