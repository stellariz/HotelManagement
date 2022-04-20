package ru.nsu.hotel_db.hotels.rooms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nsu.hotel_db.booking.BookingService;
import ru.nsu.hotel_db.hotels.HotelService;
import ru.nsu.hotel_db.organizations.filters.DateDTOFilter;
import ru.nsu.hotel_db.сlients.ClientService;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final HotelService hotelService;
    private final BookingService bookingService;
    private final ClientService clientService;

    @GetMapping("/{id}")
    public String getRooms(@PathVariable("id") Long chosenHotelId, Model model) {
        var rooms = hotelService.getAllRoomsInHotel(chosenHotelId);
        model.addAttribute("roomsList", rooms);
        model.addAttribute("chosenHotelId", chosenHotelId);
        return "roomsPage";
    }

    @GetMapping("/addRoom/{id}")
    public String getRoomForm(@PathVariable("id") Long chosenHotelId, Model model) {
        model.addAttribute("chosenHotelId", chosenHotelId);
        model.addAttribute("roomDTO", new RoomDTO());
        return "roomForm";
    }

    @PostMapping("/addRoom/{id}")
    public String addNewRoom(@ModelAttribute("roomDTO") @Valid RoomDTO roomDTO, BindingResult bindingResult, @PathVariable("id") Long chosenHotelId, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("chosenHotelId", chosenHotelId);
            return "roomForm";
        }
        try {
            roomService.addNewRoom(roomDTO, hotelService.getHotelById(chosenHotelId).get());
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("chosenHotelId", chosenHotelId);
            return "roomForm";
        }
        return "redirect:/rooms/" + chosenHotelId;
    }

    @GetMapping("/info/{id}")
    public String getRoomInfo(@PathVariable("id") Long roomId, Model model) {
        var booking = bookingService.getNearestRoomBooking(roomId);
        if (booking.isEmpty()) {
            model.addAttribute("freeDate", "not booked yet");
        } else {
            model.addAttribute("freeDate", booking.get().getBookingStartDate());
        }
        model.addAttribute("roomId", roomId);
        model.addAttribute("dateDTO", new DateDTOFilter());
        return "roomInfoPage";
    }

    @PostMapping("/info/{id}")
    public String updateRoomStory(@PathVariable("id") Long roomId, @ModelAttribute("dateDTO") @Valid DateDTOFilter dateDTOFilter, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "roomInfoPage";
        }
        var roomStory = clientService.getClientsInRoomAndPeriod(roomId, dateDTOFilter);
        redirectAttributes.addFlashAttribute("roomStory", roomStory);
        redirectAttributes.addFlashAttribute("filter", "true");
        return "redirect:/rooms/info/" + roomId + "?filter=date";
    }
}
