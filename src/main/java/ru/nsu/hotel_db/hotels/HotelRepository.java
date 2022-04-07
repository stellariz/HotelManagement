package ru.nsu.hotel_db.hotels;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Hotel;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
    List<Hotel> findAll();
}
