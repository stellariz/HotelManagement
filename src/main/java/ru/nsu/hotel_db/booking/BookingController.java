package ru.nsu.hotel_db.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nsu.hotel_db.booking.filters.DateAndOrganizationFilterDTO;
import ru.nsu.hotel_db.hotels.rooms.RoomService;
import ru.nsu.hotel_db.organizations.OrganizationService;

import javax.validation.Valid;
import java.util.Optional;

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
    public String getAllBookings(@RequestParam Optional<String> filter, Model model) {
        if (filter.isEmpty()) {
            var bookings = bookingService.getAllBookings();
            model.addAttribute("bookingsList", bookings);
        }
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
        model.addAttribute("freeRooms", rooms.size());
        model.addAttribute("action", "book");
        model.addAttribute("sale", organizationService.getOrganizationByName(bookingDTO.getOrganization()).get().getSale());
        return "roomsPage";
    }


    @PostMapping("/deleteBooking")
    public String removeBooking(@RequestParam("id") Long bookingId) {
        bookingService.removeBooking(bookingId);
        return "redirect:/booking";
    }

    @GetMapping("/getOrganizationsByDate")
    public String getDateForm(Model model) {
        model.addAttribute("dateAndOrganizationDTO", new DateAndOrganizationFilterDTO());
        return "dateAndOrganizationForm";
    }

    @PostMapping("/getOrganizationsByDate")
    public String applyNameAndDateFilter(@ModelAttribute("dateDTO") @Valid DateAndOrganizationFilterDTO dateAndOrganizationFilterDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "dateAndOrganizationForm";
        }
        var bookings = bookingService.getBookingInPeriod(dateAndOrganizationFilterDTO);
        redirectAttributes.addFlashAttribute("bookingsList", bookings);
        return "redirect:/booking?filter=orgAndDate";
    }
}
