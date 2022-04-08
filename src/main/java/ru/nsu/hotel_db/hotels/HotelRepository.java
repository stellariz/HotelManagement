package ru.nsu.hotel_db.hotels;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
    List<Hotel> findAll();
    Optional<Hotel> findHotelByName(String hotelName);
}
