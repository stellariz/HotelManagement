package ru.nsu.hotel_db.organizations;

import ru.nsu.hotel_db.Entitiy.Organization;
import ru.nsu.hotel_db.organizations.filters.DateDTOFilter;
import ru.nsu.hotel_db.organizations.filters.RoomsInPeriodDTOFilter;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    List<Organization> getAllOrganizations();

    List<Organization> getOrganizationsByBookingDate(DateDTOFilter dateDTOFilter);

    Optional<Organization> getOrganizationByName(String organization);

    Organization addNewOrganization(OrganizationDTO organizationDTO);

    List<Organization> getOrganizationsWithRequiredBookingNumberPerDate(RoomsInPeriodDTOFilter roomsInPeriodDTOFilter);
}
