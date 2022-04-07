package ru.nsu.hotel_db.сlients;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Client;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();
}
