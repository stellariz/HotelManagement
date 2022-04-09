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
@RequestMapping("/services")
public class ServiceController {
    private final HotelServiceService hotelServiceService;
    private final HotelService hotelService;

    @GetMapping("/{id}")
    public String getServicesInHotel(@PathVariable("id") Long chosenHotelId, Model model) {
        var services = hotelServiceService.getServicesInHotel(chosenHotelId);
        model.addAttribute("hotelServices", services);
        model.addAttribute("chosenHotelId", chosenHotelId);
        return "servicePage";
    }

    @PostMapping
    public String removeServiceInHotel(@RequestParam("hotelId") String chosenHotelId, @RequestParam("serviceId") String serviceId) {
        hotelServiceService.removeServiceFromHotel(Long.valueOf(serviceId));
        log.info("Deleting service {} from hotel {}", serviceId, chosenHotelId);
        return "redirect:/services/" + chosenHotelId;
    }

    @GetMapping("/addService/{id}")
    public String getServiceForm(@PathVariable("id") Long chosenHotelId, Model model) {
        model.addAttribute("serviceDTO", new ServiceDTO());
        model.addAttribute("chosenHotelId", chosenHotelId);
        return "serviceForm";
    }

    @PostMapping("/addService/{id}")
    public String addService(@ModelAttribute("serviceDTO") @Valid ServiceDTO serviceDTO, BindingResult bindingResult, @PathVariable("id") Long chosenHotelId, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("chosenHotelId", chosenHotelId);
            return "serviceForm";
        }
        try {
            hotelServiceService.addNewService(serviceDTO, hotelService.getHotelById(chosenHotelId).get());
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("chosenHotelId", chosenHotelId);
            return "serviceForm";
        }
        return "redirect:/services/" + chosenHotelId;
    }
}
