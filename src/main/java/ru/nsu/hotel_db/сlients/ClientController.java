package ru.nsu.hotel_db.сlients;

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
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

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
    public String getRegistrationForm(Model model) {
        model.addAttribute("client", new ClientDTO());
        return "clientForm";
    }

    @PostMapping("/addClient")
    public String postNewClient(@ModelAttribute("client") @Valid ClientDTO clientDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clientForm";
        }
        clientService.addNewClient(clientDTO);
        return "redirect:/clients";
    }
}
