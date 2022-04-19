package ru.nsu.hotel_db.сlients;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();
    Optional<Client> findClientByNameIgnoreCase(String name);

    @Query(value = "select NAME from ROOM join CLIENT C2 on ROOM.ROOM_ID = C2.ROOM_ID WHERE CHECK_OUT_TIME = ?1 and CHECK_OUT_TIME = ?2 and FLOOR = ?3 and CAPACITY = ?4",
    nativeQuery = true)
    List<Client> getClientsInRoomsByFilters(LocalDate checkInDate, LocalDate checkOutDate, Integer floor, Integer capacity);
}
