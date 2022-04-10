package ru.nsu.hotel_db.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Booking;
import ru.nsu.hotel_db.Entitiy.Room;
import ru.nsu.hotel_db.organizations.OrganizationRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService{
    private final BookingRepository bookingRepository;
    private final OrganizationRepository organizationRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public boolean verifyOrganization(String orgName) {
        return organizationRepository.findOrganizationByName(orgName).isPresent();
    }

    @Override
    public Booking addNewBooking(BookingDTO bookingDTO, Room room) {
        Booking booking = new Booking(null, organizationRepository.findOrganizationByName(bookingDTO.getOrganization()).get(), bookingDTO.getStartBooking(), bookingDTO.getEndBooking(), room);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookId) {
        return bookingRepository.findById(bookId).get();
    }
}
