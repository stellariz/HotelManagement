package ru.nsu.hotel_db.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    @GetMapping
    public String getAllBookings(Model model){
        var bookings = bookingService.getAllBookings();
        model.addAttribute("bookingsList", bookings);
        return "bookingPage";
    }
}
