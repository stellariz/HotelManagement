package ru.nsu.hotel_db.сlients;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();
    Optional<Client> findClientByNameIgnoreCase(String name);
}
