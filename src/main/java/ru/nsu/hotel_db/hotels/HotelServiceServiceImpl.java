package ru.nsu.hotel_db.hotels;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.HotelsService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceServiceImpl implements HotelServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public List<HotelsService> getServicesInHotel(Long hotelId) {
        return serviceRepository.findServiceByHotelHotelId(hotelId);
    }

    @Override
    public HotelsService addNewService(ServiceDTO serviceDTO, Hotel hotel) throws IllegalArgumentException {
        HotelsService hotelsService = new HotelsService(null, hotel, serviceDTO.getName(), serviceDTO.getPrice(), 0);
        if (serviceRepository.findHotelsServiceByNameAndHotel(serviceDTO.getName(), hotel).isPresent()){
            throw new IllegalArgumentException("Service with this name already exists");
        }
        return serviceRepository.save(hotelsService);
    }

    @Override
    public void removeAllServicesFromHotel(Long hotelId) {
        serviceRepository.deleteAll(serviceRepository.findServiceByHotelHotelId(hotelId));
    }

    @Override
    public void removeServiceFromHotel(Long serviceId) {
        serviceRepository.deleteHotelsServiceByServiceId(serviceId);
    }
}
