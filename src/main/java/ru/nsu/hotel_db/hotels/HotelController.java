package ru.nsu.hotel_db.hotels;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.hotel_db.hotels.rooms.RoomService;
import ru.nsu.hotel_db.hotels.hotelServices.HotelServiceService;
import ru.nsu.hotel_db.сlients.ClientService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;
    private final RoomService roomService;

    @GetMapping
    public String getHotels(Model model) {
        var hotels = hotelService.getAllHotels();
        model.addAttribute("hotelsList", hotels);
        model.addAttribute("percentage", roomService.countPercentageBookedRooms());
        return "hotelsPage";
    }

    @GetMapping("/addHotel")
    public String addHotel(Model model) {
        model.addAttribute("hotelDTO", new HotelDTO());
        return "hotelForm";
    }

    @PostMapping("/addHotel")
    public String saveNewHotel(@ModelAttribute("hotelDTO") @Valid HotelDTO hotelDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "hotelForm";
        }
        try {
            hotelService.addNewHotel(hotelDTO);
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "hotelForm";
        }
        return "redirect:/hotels";
    }
}
