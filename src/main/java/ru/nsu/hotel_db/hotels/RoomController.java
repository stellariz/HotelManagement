package ru.nsu.hotel_db.hotels;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final HotelService hotelService;

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
}
