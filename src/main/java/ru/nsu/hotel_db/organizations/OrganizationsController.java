package ru.nsu.hotel_db.organizations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
