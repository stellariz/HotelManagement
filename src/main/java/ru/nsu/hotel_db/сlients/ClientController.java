package ru.nsu.hotel_db.сlients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nsu.hotel_db.booking.filters.DateAndOrganizationFilterDTO;
import ru.nsu.hotel_db.hotels.rooms.RoomService;
import ru.nsu.hotel_db.organizations.filters.DateDTOFilter;
import ru.nsu.hotel_db.сlients.filters.DateAndRoomDTOFilter;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
@SessionAttributes({"clientDTO"})
public class ClientController {
    private final ClientService clientService;
    private final RoomService roomService;

    @GetMapping
    public String getAllClients(@RequestParam Optional<String> filter, Model model) {
        if (filter.isEmpty()) {
            var clients = clientService.getAllClients();
            model.addAttribute("clientsList", clients);
        }
        model.addAttribute("topVisitors", clientService.getClientsWithMaxVisitsForAllHotels());
        return "clientsPage";
    }

    @PostMapping
    public String updateClients(@ModelAttribute("clientDTO") ClientDTO clientDTO, @RequestParam("roomId") Long roomId, Model model) {
        clientService.addNewClient(clientDTO, roomService.findRoomById(roomId).get());
        return "redirect:/clients";
    }

    @GetMapping("/addClient")
    public String getRegistrationForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        return "clientForm";
    }

    @PostMapping("/addClient")
    public String postNewClient(@ModelAttribute("clientDTO") @Valid ClientDTO clientDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "clientForm";
        }
        var rooms = roomService.getFreeRoomsOnDate(clientDTO.getCheckInTime(), clientDTO.getCheckOutTime());
        model.addAttribute("action", "buy");
        model.addAttribute("roomsList", rooms);
        model.addAttribute("freeRooms", rooms.size());
        return "roomsPage";
    }

    @GetMapping("/getClientsByDate")
    public String getDateForm(Model model){
        model.addAttribute("dateDTOFilter", new DateDTOFilter());
        model.addAttribute("group", "clients");
        return "dateForm";
    }

    @PostMapping("/getClientsByDate")
    public String applyDateFilter(@ModelAttribute("dateDTOFilter") @Valid DateDTOFilter dateDTOFilter, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "dateForm";
        }
        var clients = clientService.getClientsInPeriod(dateDTOFilter);
        redirectAttributes.addFlashAttribute("clientsList", clients);
        return "redirect:/clients?filter=date";
    }

    @GetMapping("/info/{clientName}")
    public String getClientInfoPage(@PathVariable("clientName") String clientName, Model model){
        model.addAttribute("clientName", clientName);
        var clientStory = clientService.getClientStory(clientName);
        model.addAttribute("clientsList", clientStory);
        model.addAttribute("visitedTimes", clientStory.size());
        return "clientInfoPage";
    }

    @GetMapping("/getClientsByDateAndRoom")
    public String getDateAndRoomForm(Model model){
        model.addAttribute("dateAndRoomDTOFilter", new DateAndRoomDTOFilter());
        return "dateAndRoomForm";
    }

    @PostMapping("/getClientsByDateAndRoom")
    public String applyDateAndRoomFilter(@ModelAttribute("dateAndRoomDTOFilter") @Valid DateAndRoomDTOFilter dateAndRoomDTOFilter, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "dateAndRoomForm";
        }
        redirectAttributes.addFlashAttribute("clientsList", clientService.getClientsByRoomPropsAndPeriod(dateAndRoomDTOFilter));
        redirectAttributes.addFlashAttribute("filter", "dateAndRoom");
        return "redirect:/clients?filter=dateAndRoom";
    }
}
