package ru.nsu.hotel_db.сlients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.hotel_db.hotels.rooms.RoomService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
@SessionAttributes({"clientDTO"})
public class ClientController {
    private final ClientService clientService;
    private final RoomService roomService;

    @GetMapping
    public String getAllClients(Model model) {
        var clients = clientService.getAllClients();
        model.addAttribute("clientsList", clients);
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

    @GetMapping("/story")
    public String getStoryForm(Model model) {
        return "clientForm";
    }
}
