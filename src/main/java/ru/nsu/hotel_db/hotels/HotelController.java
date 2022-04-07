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
    public String getServicesInHotel(@PathVariable("id") Long chosenHotelId, Model model){
        var services = hotelService.getServicesInHotel(chosenHotelId);
        model.addAttribute("hotelServices", services);
        model.addAttribute("chosenHotelId", chosenHotelId);
        return "servicePage";
    }

    @GetMapping("/services/addService/{id}")
    public String getServiceForm(@PathVariable("id") Long chosenHotelId, Model model){
        System.out.println(chosenHotelId);
        model.addAttribute("serviceDTO", new ServiceDTO());
        model.addAttribute("chosenHotelId", chosenHotelId);
        return "serviceForm";
    }

    @PostMapping("/services/addService/{id}")
    public String addService(@ModelAttribute("serviceDTO") @Valid ServiceDTO serviceDTO, BindingResult bindingResult, @PathVariable("id") Long chosenHotelId){
        if (bindingResult.hasErrors()){
            return "serviceForm";
        }
        hotelService.addNewService(serviceDTO, chosenHotelId);
        return "redirect:/hotels/services/" + chosenHotelId;
    }

    @GetMapping("/addHotel")
    public String addHotel(Model model) {
        model.addAttribute("hotel", new HotelDTO());
        return "hotelForm";
    }

    @PostMapping("/addHotel")
    public String saveNewHotel(@ModelAttribute("hotel") @Valid HotelDTO hotelDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "hotelForm";
        }
        hotelService.addNewHotel(hotelDTO);
        return "redirect:/hotels";
    }
}
