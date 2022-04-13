package ru.nsu.hotel_db.bills;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.hotel_db.hotels.hotelServices.HotelServiceService;
import ru.nsu.hotel_db.сlients.ClientService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;
    private final ClientService clientService;
    private final HotelServiceService hotelServiceService;

    @GetMapping
    public String getBillPage(Model model) {
        model.addAttribute("billsList", billService.getAllBills());
        return "billsPage";
    }

    @GetMapping("/buy/*")
    public String getClientFormForBuying(Model model) {
        model.addAttribute("buyingServiceClientDTO", new BuyingServiceClientDTO());
        return "buyingServiceForm";
    }

    @PostMapping("/buy/{serviceId}")
    public String postNewTransaction(@PathVariable("serviceId") Long serviceId, @ModelAttribute("buyingServiceClientDTO") @Valid BuyingServiceClientDTO buyingServiceClientDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "buyingServiceForm";
        }
        if (clientService.getClientByName(buyingServiceClientDTO.getName()).isEmpty()) {
            model.addAttribute("errorMessage", "Sorry, but you haven't lived in our hotel yet.");
            return "buyingServiceForm";
        }
        billService.registerNewBill(buyingServiceClientDTO, hotelServiceService.getHotelsServiceById(serviceId).get(), clientService.getClientByName(buyingServiceClientDTO.getName()).get());
        return "redirect:/bill";
    }

    @GetMapping("/info/{clientName}")
    public String getClientBills(@PathVariable("clientName") String clientName, Model model){
        model.addAttribute("billsList", billService.getClientBills(clientService.getClientByName(clientName).get()));
        return "billsPage";
    }
}
