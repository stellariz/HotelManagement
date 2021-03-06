package ru.nsu.hotel_db.organizations;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Organization;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
    List<Organization> findAll();

    Optional<Organization> findOrganizationByName(String organizationName);

    @Query(value = "select * from ORGANIZATION JOIN BOOKING B on ORGANIZATION.ORG_ID = B.ORG_ID where BOOKING_START_DATE >= ?1 and BOOKING_END_DATE <= ?2",
            nativeQuery = true)
    List<Organization> findOrganizationByBookingDate(LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT * from ORGANIZATION where ORG_ID in (select ORG_ID FROM (select ORGANIZATION.ORG_ID, count(BOOKING_ID) from ORGANIZATION join BOOKING B on ORGANIZATION.ORG_ID = B.ORG_ID where B.BOOKING_START_DATE >= ?1 and B.BOOKING_END_DATE <= ?2 group by ORGANIZATION.ORG_ID HAVING COUNT(BOOKING_ID) >= ?3))",
            nativeQuery = true)
    List<Organization> findOrganizationByBookingNumberPerDate(LocalDate bookingStartDate, LocalDate bookingEndDate, Integer requiredNumber);
}
