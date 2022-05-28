package ru.nsu.hotel_db.сlients;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.Organization;
import ru.nsu.hotel_db.Entitiy.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();

    Optional<Client> findTopByNameIgnoreCaseOrderByCheckInTimeDesc(String name);

    @Query(value = "SELECT * FROM CLIENT join ROOM R on R.ROOM_ID = CLIENT.ROOM_ID where CHECK_IN_TIME >= ?1 and CHECK_OUT_TIME <= ?2 and FLOOR = ?3 and CAPACITY = ?4",
            nativeQuery = true)
    List<Client> getClientsInRoomsByFilters(LocalDate checkInDate, LocalDate checkOutDate, Integer floor, Integer capacity);

    List<Client> findClientByCheckInTimeGreaterThanEqualAndAndCheckOutTimeLessThanEqual(LocalDate firstDate, LocalDate secondDate);

    List<Client> findClientByName(String name);

    List<Client> findClientByRoomRoomIdAndCheckInTimeGreaterThanEqualAndCheckOutTimeLessThanEqual(Long roomId, LocalDate checkInTime, LocalDate checkOutTime);

    @Query(value = "SELECT NAME from (SELECT HOTEL_ID, NAME, row_number() over (partition by HOTEL_ID order by total_visits desc) as row_number from (SELECT H.HOTEL_ID, CLIENT.NAME, count(CLIENT.NAME) as total_visits from CLIENT join ROOM R on R.ROOM_ID = CLIENT.ROOM_ID join HOTEL H on H.HOTEL_ID = R.HOTEL_ID GROUP BY H.HOTEL_ID, CLIENT.NAME order by total_visits desc))where row_number = 1",
            nativeQuery = true)
    List<String> findClientsWithMaxVisitsForEachHotel();
}
