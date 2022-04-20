package ru.nsu.hotel_db.сlients;

import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.Room;
import ru.nsu.hotel_db.organizations.filters.DateDTOFilter;
import ru.nsu.hotel_db.сlients.filters.DateAndRoomDTOFilter;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAllClients();

    Client addNewClient(ClientDTO clientDTO, Room chosenRoom);

    Optional<Client> getClientByName(String name);

    List<Client> getClientsInPeriod(DateDTOFilter dateDTOFilter);

    List<Client> getClientStory(String clientName);

    List<Client> getClientsInRoomAndPeriod(Long roomId, DateDTOFilter dateDTOFilter);

    List<Client> getClientsByRoomPropsAndPeriod(DateAndRoomDTOFilter dateAndRoomDTOFilter);
}
