package ru.nsu.hotel_db.organizations;

import ru.nsu.hotel_db.Entitiy.Organization;
import ru.nsu.hotel_db.organizations.userExceptions.OrganizationNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    List<Organization> getAllOrganizations();
    Optional<Organization> getOrganizationByName(String organization);
    Organization addNewOrganization(OrganizationDTO organizationDTO);
}
