package ru.nsu.hotel_db.сlients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nsu.hotel_db.hotels.RoomService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final RoomService roomService;

    @GetMapping
    public String getAllClients(Model model) {
        var clients = clientService.getAllClients();
        model.addAttribute("clientsList", clients);
        return "clientsPage";
    }

    @GetMapping("/reviews/{id}")
    public String getReviewsForClient(@PathVariable("id") Long id, Model model) {
        var reviews = clientService.getReviewsByClient(id);
        model.addAttribute("reviewsList", reviews);
        return "reviewsPage";
    }

    @GetMapping("/addClient")
    public String getRegistrationForm(@RequestParam("roomId") Long roomId, Model model) {
        model.addAttribute("client", new ClientDTO());
        model.addAttribute("roomId", roomId);
        return "clientForm";
    }

    @PostMapping("/addClient")
    public String postNewClient(@ModelAttribute("client") @Valid ClientDTO clientDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clientForm";
        }
        clientService.addNewClient(clientDTO, roomService.findRoomById(clientDTO.getRoomId()).get());
        return "redirect:/clients";
    }
}
