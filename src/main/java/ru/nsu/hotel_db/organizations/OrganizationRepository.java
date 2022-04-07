package ru.nsu.hotel_db.organizations;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
    List<Organization> findAll();
    Optional<Organization> findOrganizationByName(String organizationName);
}
