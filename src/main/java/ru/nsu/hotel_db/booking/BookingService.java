package ru.nsu.hotel_db.booking;

import ru.nsu.hotel_db.Entitiy.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
}
