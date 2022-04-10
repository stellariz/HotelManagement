package ru.nsu.hotel_db.organizations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.hotel_db.booking.BookingDTO;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/organizations")
public class OrganizationsController {
    private final OrganizationService organizationService;

    @GetMapping
    public String getAllOrganizations(Model model) {
        var organizations = organizationService.getAllOrganizations();
        model.addAttribute("organizationsList", organizations);
        return "organizationsPage";
    }

    @GetMapping("/addOrganization")
    public String getOrganizationForm(Model model) {
        model.addAttribute("organizationDTO", new OrganizationDTO());
        return "organizationForm";
    }

    @PostMapping("/addOrganization")
    public String addNewOrganization(@ModelAttribute("organizationDTO") @Valid OrganizationDTO organizationDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "organizationForm";
        }
        if (organizationService.getOrganizationByName(organizationDTO.getName()).isPresent()) {
            model.addAttribute("errorMessage", "We have already contract with this organization!");
            return "organizationForm";
        }
        organizationService.addNewOrganization(organizationDTO);
        return "redirect:/organizations";
    }
}
