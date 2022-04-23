package ru.nsu.hotel_db.bills;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.hotel_db.Entitiy.Bill;
import ru.nsu.hotel_db.Entitiy.Client;
import ru.nsu.hotel_db.Entitiy.HotelsService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService{
    private final BillRepository billRepository;
    @Override
    public Bill registerNewBill(BuyingServiceClientDTO buyingServiceClientDTO, HotelsService hotelsService, Client client) {
        Bill bill = new Bill(null, client, hotelsService, null, false);
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> getClientBills(Client client) {
        return billRepository.findBillByClient(client);
    }

    @Override
    public List<Bill> getCurrentVisitorBillsFromRoom(Long roomId) {
        return billRepository.findCurrentVisitorBillsFromRoom(roomId);
    }
}
