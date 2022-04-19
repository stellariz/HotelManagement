package ru.nsu.hotel_db.booking;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAll();

    List<Booking> findBookingByOrganizationNameAndBookingStartDateGreaterThanEqualAndBookingEndDateLessThanEqual(String name, LocalDate firstDate, LocalDate secondDate);

    Optional<Booking> findFirstByRoomRoomIdOrderByBookingStartDate(Long roomId);
}
