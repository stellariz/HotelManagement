package ru.nsu.hotel_db.booking;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Booking;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAll();
}
