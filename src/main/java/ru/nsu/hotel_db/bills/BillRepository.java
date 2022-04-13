package ru.nsu.hotel_db.bills;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Bill;
import ru.nsu.hotel_db.Entitiy.Client;

import java.util.List;
import java.util.Optional;

public interface BillRepository extends CrudRepository<Bill, Long> {
    Optional<Bill> findBillByBillId(Long billId);
    List<Bill> findAll();
    List<Bill> findBillByClient(Client client);
}
