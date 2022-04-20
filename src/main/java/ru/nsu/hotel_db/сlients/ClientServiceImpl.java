package ru.nsu.hotel_db.сlients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.Hotel;
import ru.nsu.hotel_db.Entitiy.Room;
import ru.nsu.hotel_db.bills.BillRepository;
import ru.nsu.hotel_db.organizations.filters.DateDTOFilter;
import ru.nsu.hotel_db.сlients.filters.DateAndRoomDTOFilter;
import ru.nsu.hotel_db.сlients.reviews.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ReviewRepository reviewRepository;
    private final BillRepository billRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client addNewClient(ClientDTO clientDTO, Room room) {
        Client client = new Client(null, clientDTO.getName(), room, clientDTO.getCheckInTime(), clientDTO.getCheckOutTime());
        room.setPopularity(room.getPopularity() + 1);
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> getClientByName(String name) {
        return clientRepository.findClientByNameIgnoreCase(name);
    }

    @Override
    public List<Client> getClientsInPeriod(DateDTOFilter dateDTOFilter) {
        return clientRepository.findClientByCheckInTimeGreaterThanEqualAndAndCheckOutTimeLessThanEqual(dateDTOFilter.getFirstDate(), dateDTOFilter.getSecondDate());
    }

    @Override
    public List<Client> getClientStory(String clientName) {
        return clientRepository.findClientByName(clientName);
    }

    @Override
    public List<Client> getClientsInRoomAndPeriod(Long roomId, DateDTOFilter dateDTOFilter) {
        return clientRepository.findClientByRoomRoomIdAndCheckInTimeGreaterThanEqualAndCheckOutTimeLessThanEqual(roomId, dateDTOFilter.getFirstDate(), dateDTOFilter.getSecondDate());
    }

    @Override
    public List<Client> getClientsByRoomPropsAndPeriod(DateAndRoomDTOFilter dateAndRoomDTOFilter) {
        return clientRepository.getClientsInRoomsByFilters(dateAndRoomDTOFilter.getFirstDate(), dateAndRoomDTOFilter.getSecondDate(), dateAndRoomDTOFilter.getFloor(), dateAndRoomDTOFilter.getCapacity());
    }
}
