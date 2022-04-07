package ru.nsu.hotel_db.сlients;

import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.Review;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    List<Review> getReviewsByClient(Long clientId);
    Client addNewClient(ClientDTO clientDTO, String organizationName);
    boolean verifyClientOrganization(String organizationName);
}
