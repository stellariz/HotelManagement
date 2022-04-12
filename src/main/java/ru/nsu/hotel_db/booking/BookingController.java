package ru.nsu.hotel_db.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.hotel_db.hotels.RoomService;
import ru.nsu.hotel_db.organizations.OrganizationService;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@SessionAttributes({"bookingDTO"})
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    private final RoomService roomService;
    private final OrganizationService organizationService;

    @GetMapping
    public String getAllBookings(Model model) {
        var bookings = bookingService.getAllBookings();
        model.addAttribute("bookingsList", bookings);
        return "bookingPage";
    }

    @PostMapping
    public String updateBookingPage(@ModelAttribute("bookingDTO") BookingDTO bookingDTO, @RequestParam("roomId") Long roomId) {
        bookingService.addNewBooking(bookingDTO, roomService.findRoomById(roomId).get());
        return "redirect:/booking";
    }

    @GetMapping("/bookRoom")
    public String getBookingForm(Model model) {
        model.addAttribute("bookingDTO", new BookingDTO());
        return "bookingForm";
    }

    @PostMapping("/bookRoom")
    public String getAvailableRooms(@ModelAttribute("bookingDTO") @Valid BookingDTO bookingDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "bookingForm";
        }
        if (!bookingService.verifyOrganization(bookingDTO.getOrganization())) {
            model.addAttribute("errorMessage", "Sorry, but we have no booking contract with this organization yet");
            return "bookingForm";
        }
        var rooms = roomService.getRoomByBookingConditions(bookingDTO);
        model.addAttribute("roomsList", rooms);
        model.addAttribute("action", "Book");
        model.addAttribute("sale", organizationService.getOrganizationByName(bookingDTO.getOrganization()).get().getSale());
        return "roomsPage";
    }


    @PostMapping("/deleteBooking")
    public String removeBooking(@RequestParam("id") Long bookingId) {
        bookingService.removeBooking(bookingId);
        log.info("deleting booking with id={}", bookingId);
        return "redirect:/booking";
    }
}
