package ru.nsu.hotel_db.сlients;

import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.Review;
import ru.nsu.hotel_db.Entitiy.Room;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAllClients();

    Client addNewClient(ClientDTO clientDTO, Room chosenRoom);

    Optional<Client> getClientByName(String name);
}
