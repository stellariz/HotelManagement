package ru.nsu.hotel_db.hotels.rooms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.Room;
import ru.nsu.hotel_db.booking.BookingDTO;
import ru.nsu.hotel_db.hotels.rooms.filters.FreeDateDTO;
import ru.nsu.hotel_db.organizations.filters.DateDTOFilter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public Optional<Room> findRoomById(Long roomId) {
        return roomRepository.findById(roomId);
    }

    @Override
    public Room addNewRoom(RoomDTO roomDTO, Hotel hotel) throws IllegalArgumentException {
        Room room = new Room(null, roomDTO.getRoomNumber(), hotel, roomDTO.getCapacity(), 0, roomDTO.getFloor(), null, roomDTO.getServicePrice());
        if (roomRepository.findRoomByRoomNumberAndHotelHotelId(room.getRoomNumber(), hotel.getHotelId()).isPresent()) {
            throw new IllegalArgumentException("Room with this number already exists in this hotel!");
        }
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getRoomByBookingConditions(BookingDTO bookingDTO) {
        return roomRepository.findRoomByCapacityAndFloorAndHotelHotelClass(bookingDTO.getStartBooking(), bookingDTO.getEndBooking(), bookingDTO.getRoomCapacity(), bookingDTO.getRoomFloor(), bookingDTO.getHotelClass());
    }

    @Override
    public List<Room> getVacatedRoomsToDate(FreeDateDTO freeDateDTO) {
        return roomRepository.findRoomsThatWillBeFreeOnDate(freeDateDTO.getFreeDate());
    }

    @Override
    public Double countPercentageBookedRooms() {
        return roomRepository.countPercentageBookedRooms();
    }

    @Override
    public List<Room> getFreeRoomsOnDate(LocalDate startDate, LocalDate endDate) {
        return roomRepository.findFreeRooms(startDate, endDate);
    }
}
