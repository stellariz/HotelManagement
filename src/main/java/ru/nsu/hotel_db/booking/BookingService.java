package ru.nsu.hotel_db.booking;

import ru.nsu.hotel_db.Entitiy.Booking;
import ru.nsu.hotel_db.Entitiy.Room;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    boolean verifyOrganization(String orgName);
    Booking addNewBooking(BookingDTO bookingDTO, Room room);
    Booking getBookingById(Long bookId);
    void removeBooking(Long bookingId);
}
