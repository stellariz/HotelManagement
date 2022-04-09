package ru.nsu.hotel_db.сlients;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Bill;

import java.util.Optional;

public interface BillRepository extends CrudRepository<Bill, Long> {
    Optional<Bill> findBillByBillId(Long billId);
}
