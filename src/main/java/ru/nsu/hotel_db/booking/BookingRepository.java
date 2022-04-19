package ru.nsu.hotel_db.booking;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAll();

//    @Query(value = "SELECT * from BOOKING join ORGANIZATION O on O.ORG_ID = BOOKING.ORG_ID where BOOKING_START_DATE >= ?1 and BOOKING_END_DATE <= ?2 and O.NAME = ?3",
//            nativeQuery = true)
//    List<Booking> findBookingInPeriod(LocalDate firstDate, LocalDate secondDate, String name);

    List<Booking> findBookingByOrganizationNameAndBookingStartDateGreaterThanEqualAndBookingEndDateLessThanEqual(String name, LocalDate firstDate, LocalDate secondDate);
}
