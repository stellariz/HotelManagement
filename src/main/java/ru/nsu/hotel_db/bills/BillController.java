package ru.nsu.hotel_db.bills;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.hotel_db.Entitiy.Bill;
import ru.nsu.hotel_db.hotels.hotelServices.HotelServiceService;
import ru.nsu.hotel_db.сlients.ClientService;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = {"/info/{clientName}", "/info"})
    public String getClientBills(@PathVariable(value = "clientName", required = false) String clientName, @RequestParam("filter") Optional<Long> roomId, Model model) {
        if (roomId.isEmpty()) {
            model.addAttribute("billsList", billService.getClientBills(clientService.getClientByName(clientName).get()));
        } else {
            model.addAttribute("billsList", billService.getCurrentVisitorBillsFromRoom(roomId.get()));
        }
        return "billsPage";
    }

    @GetMapping("/evict")
    public String createBillBeforeEvict(@RequestParam(value = "clientId") Long clientId, @RequestParam(value="clientName") String clientName, @RequestParam(value = "checkInTime") String checkInDate, @RequestParam(value = "checkOutTime") String checkOutTime, Model model) {
        List<Bill> billList = billService.getBillBeforeEvict(clientId, LocalDate.parse(checkInDate), LocalDate.parse(checkOutTime));
        long days = LocalDate.parse(checkOutTime).getDayOfYear() -  LocalDate.parse(checkInDate).getDayOfYear();
        float roomPrice = clientService.getClientByName(clientName).get().getRoom().getPrice();
        float servicesPrice = billService.countTotalPriceForServices(billList);
        model.addAttribute("billsList", billList);
        model.addAttribute("totalDays", days);
        model.addAttribute("livingPrice", roomPrice);
        model.addAttribute("totalPrice", roomPrice*days + servicesPrice);
        model.addAttribute("evict", "evict");
        return "billsPage";
    }
}