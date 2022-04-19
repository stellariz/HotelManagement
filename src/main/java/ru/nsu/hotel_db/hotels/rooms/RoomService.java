package ru.nsu.hotel_db.hotels.rooms;

import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.Room;
import ru.nsu.hotel_db.booking.BookingDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomService {
    Optional<Room> findRoomById(Long roomId);

    Room addNewRoom(RoomDTO roomDTO, Hotel hotel) throws IllegalArgumentException;

    List<Room> getFreeRoomsOnDate(LocalDate startDate, LocalDate endDate);

    List<Room> getRoomByBookingConditions(BookingDTO bookingDTO) throws IllegalArgumentException;
}
