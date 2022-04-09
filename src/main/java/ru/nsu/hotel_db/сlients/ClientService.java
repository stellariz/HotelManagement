package ru.nsu.hotel_db.сlients;

import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.Review;
import ru.nsu.hotel_db.Entitiy.Room;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    List<Review> getReviewsByClient(Long clientId);

    Client addNewClient(ClientDTO clientDTO, Room chosenRoom);
}
