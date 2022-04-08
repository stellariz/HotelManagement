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
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;
    private final HotelServiceService hotelServiceService;

    @GetMapping
    public String getHotels(Model model) {
        var hotels = hotelService.getAllHotels();
        model.addAttribute("hotelsList", hotels);
        return "hotelsPage";
    }

    @GetMapping("/{id}")
    public String getRooms(@PathVariable("id") Long chosenHotelId, Model model) {
        var rooms = hotelService.getRoomsInHotel(chosenHotelId);
        model.addAttribute("roomsList", rooms);
        return "roomsPage";
    }

    @GetMapping("/services/{id}")
    public String getServicesInHotel(@PathVariable("id") Long chosenHotelId, Model model) {
        var services = hotelServiceService.getServicesInHotel(chosenHotelId);
        model.addAttribute("hotelServices", services);
        model.addAttribute("chosenHotelId", chosenHotelId);
        return "servicePage";
    }

    @GetMapping("/services/addService/{id}")
    public String getServiceForm(@PathVariable("id") Long chosenHotelId, Model model) {
        System.out.println(chosenHotelId);
        model.addAttribute("serviceDTO", new ServiceDTO());
        model.addAttribute("chosenHotelId", chosenHotelId);
        return "serviceForm";
    }

    @PostMapping("/services/addService/{id}")
    public String addService(@ModelAttribute("serviceDTO") @Valid ServiceDTO serviceDTO, BindingResult bindingResult, @PathVariable("id") Long chosenHotelId, Model model) {
        if (bindingResult.hasErrors()) {
            return "serviceForm";
        }
        try {
            hotelServiceService.addNewService(serviceDTO, hotelService.getHotelById(chosenHotelId).get());
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "serviceForm";
        }
        return "redirect:/hotels/services/" + chosenHotelId;
    }

    @GetMapping("/addHotel")
    public String addHotel(Model model) {
        model.addAttribute("hotel", new HotelDTO());
        return "hotelForm";
    }

    @PostMapping("/addHotel")
    public String saveNewHotel(@ModelAttribute("hotel") @Valid HotelDTO hotelDTO, BindingResult bindingResult, Model model) {
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

    @PostMapping
    public String removeHotel(@RequestParam("id") String hotelId) {
        hotelServiceService.removeAllServicesFromHotel(Long.valueOf(hotelId));
        hotelService.removeHotelById(Long.valueOf(hotelId));
        log.info("deleting hotel with id={}", hotelId);
        return "redirect:/hotels";
    }
}
