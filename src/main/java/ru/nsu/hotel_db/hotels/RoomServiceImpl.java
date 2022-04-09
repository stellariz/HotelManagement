package ru.nsu.hotel_db.hotels;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.Room;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public Room addNewRoom(RoomDTO roomDTO, Hotel hotel) throws IllegalArgumentException {
        Room room = new Room(null, roomDTO.getRoomNumber(), hotel, roomDTO.getCapacity(), 0, roomDTO.getFloor(), roomDTO.getServicePrice());
        if (roomRepository.findRoomByRoomNumberAndHotelHotelId(room.getRoomNumber(), hotel.getHotelId()).isPresent()) {
            throw new IllegalArgumentException("Room with this number already exists in this hotel!");
        }
        return roomRepository.save(room);
    }

    @Override
    public void removeAllRoomsFromHotel(Long hotelId) {
        roomRepository.deleteAll(roomRepository.findRoomByHotelHotelId(hotelId));
    }
}
