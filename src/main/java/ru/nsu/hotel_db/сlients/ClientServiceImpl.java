package ru.nsu.hotel_db.сlients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.Review;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByClient(Long clientId) {
        return reviewRepository.getReviewByClientClientId(clientId);
    }

    @Override
    public Client addNewClient(ClientDTO clientDTO) {
        Client client = new Client(null, clientDTO.getName(), null, clientDTO.getCheckInTime(), clientDTO.getCheckOutTime());
        return clientRepository.save(client);
    }
}
