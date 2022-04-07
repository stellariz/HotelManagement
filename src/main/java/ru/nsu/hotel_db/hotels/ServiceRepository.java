package ru.nsu.hotel_db.hotels;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.HotelsService;

import java.util.List;

public interface ServiceRepository extends CrudRepository<HotelsService, Long> {
    List<HotelsService> findServiceByHotelHotelId(Long hotelId);
}
