package ru.nsu.hotel_db.hotels;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.HotelsService;
import ru.nsu.hotel_db.Entitiy.Room;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Room> getRoomsInHotel(Long hotelId) {
        return roomRepository.findFreeRoomsInHotel(hotelId);
    }

    @Override
    public Hotel addNewHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel(null, hotelDTO.getName(), hotelDTO.getHotelClass(), hotelDTO.getLevels());
        return hotelRepository.save(hotel);
    }

    @Override
    public List<HotelsService> getServicesInHotel(Long hotelId) {
        return serviceRepository.findServiceByHotelHotelId(hotelId);
    }

    @Override
    public HotelsService addNewService(ServiceDTO serviceDTO, Long hotelId) {
        HotelsService hotelsService = new HotelsService(null, hotelRepository.findById(hotelId).get(), serviceDTO.getName(), serviceDTO.getPrice(), 0);
        // here to check if service already exists
        return serviceRepository.save(hotelsService);
    }
}
