package ru.nsu.hotel_db.organizations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Organization;
import ru.nsu.hotel_db.organizations.filters.DateDTOFilter;
import ru.nsu.hotel_db.organizations.filters.RoomsInPeriodDTOFilter;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public List<Organization> getOrganizationsByBookingDate(DateDTOFilter dateDTOFilter) {
        return organizationRepository.findOrganizationByBookingDate(dateDTOFilter.getFirstDate(), dateDTOFilter.getSecondDate());
    }

    @Override
    public Optional<Organization> getOrganizationByName(String organizationName) {
        return organizationRepository.findOrganizationByName(organizationName);
    }

    @Override
    public Organization addNewOrganization(OrganizationDTO organizationDTO) {
        Organization organization = new Organization(null, organizationDTO.getName(), organizationDTO.getSale());
        return organizationRepository.save(organization);
    }

    @Override
    public List<Organization> getOrganizationsWithRequiredBookingNumberPerDate(RoomsInPeriodDTOFilter roomsInPeriodDTOFilter) {
        return organizationRepository.findOrganizationByBookingNumberPerDate(roomsInPeriodDTOFilter.getFirstDate(), roomsInPeriodDTOFilter.getSecondDate(), roomsInPeriodDTOFilter.getTotalRooms());
    }
}
