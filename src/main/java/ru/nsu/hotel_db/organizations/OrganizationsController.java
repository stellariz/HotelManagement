package ru.nsu.hotel_db.organizations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nsu.hotel_db.organizations.filters.DateDTOFilter;
import ru.nsu.hotel_db.organizations.filters.RoomsInPeriodDTOFilter;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/organizations")
public class OrganizationsController {
    private final OrganizationService organizationService;

    @GetMapping
    public String getOrganizations(@RequestParam Optional<String> filter, Model model) {
        if (filter.isEmpty()) {
            var organizations = organizationService.getAllOrganizations();
            model.addAttribute("organizationsList", organizations);
        }
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

    @GetMapping("/getOrganizationsBookingByDate")
    public String getDateForm(Model model) {
        model.addAttribute("dateDTOFilter", new DateDTOFilter());
        model.addAttribute("group", "organizations");
        return "dateForm";
    }

    @PostMapping("/getOrganizationsBookingByDate")
    public String applyDateFilter(@ModelAttribute("dateDTOFilter") @Valid DateDTOFilter dateDTOFilter, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "dateForm";
        }
        var organizations = organizationService.getOrganizationsByBookingDate(dateDTOFilter);
        redirectAttributes.addFlashAttribute("organizationsList", organizations);
        return "redirect:/organizations?filter=date";
    }

    @GetMapping("/getOrganizationsByTotalRoomInPeriod")
    public String getRoomsInPeriodFilter(Model model) {
        model.addAttribute("roomsInPeriodDTOFiler", new RoomsInPeriodDTOFilter());
        return "roomsInPeriodForm";
    }

    @PostMapping("/getOrganizationsByTotalRoomInPeriod")
    public String applyRoomsInPeriodFilter(@ModelAttribute("roomsInPeriodDTOFilter") RoomsInPeriodDTOFilter roomsInPeriodDTOFilter, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            return "roomsInPeriodForm";
        }
        var organizations = organizationService.getOrganizationsWithRequiredBookingNumberPerDate(roomsInPeriodDTOFilter);
        redirectAttributes.addFlashAttribute("listSize", organizations.size());
        redirectAttributes.addFlashAttribute("organizationsList", organizations);
        redirectAttributes.addFlashAttribute("filter", "roomsInPeriod");
        return "redirect:/organizations/getOrganizationsByTotalRoomInPeriod";
    }
}
