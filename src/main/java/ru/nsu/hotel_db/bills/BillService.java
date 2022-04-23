package ru.nsu.hotel_db.bills;

import ru.nsu.hotel_db.Entitiy.Bill;
import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.HotelsService;

import java.util.List;

public interface BillService {
    Bill registerNewBill(BuyingServiceClientDTO buyingServiceClientDTO, HotelsService hotelsService, Client client);

    List<Bill> getAllBills();

    List<Bill> getClientBills(Client client);

    List<Bill> getCurrentVisitorBillsFromRoom(Long roomId);
}
