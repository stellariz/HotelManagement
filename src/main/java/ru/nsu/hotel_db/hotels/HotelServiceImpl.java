package ru.nsu.hotel_db.hotels;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.Room;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Room> getRoomsInHotel(Long hotelId) {
        return roomRepository.findFreeRoomsInHotel(hotelId);
    }

    @Override
    public Hotel addNewHotel(HotelDTO hotelDTO) throws IllegalArgumentException {
        Hotel hotel = new Hotel(null, hotelDTO.getName(), hotelDTO.getHotelClass(), hotelDTO.getLevels());
        if (hotelRepository.findHotelByName(hotelDTO.getName()).isPresent()){
            throw new IllegalArgumentException("Hotel with this name already exists!");
        }
        return hotelRepository.save(hotel);
    }

    @Override
    public Optional<Hotel> getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId);
    }

    @Override
    public void removeHotelById(Long hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}
