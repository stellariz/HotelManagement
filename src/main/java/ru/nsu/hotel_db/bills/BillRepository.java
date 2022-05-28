package ru.nsu.hotel_db.bills;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.hotel_db.Entitiy.Bill;
import ru.nsu.hotel_db.Entitiy.Client;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BillRepository extends CrudRepository<Bill, Long> {
    Optional<Bill> findBillByBillId(Long billId);

    List<Bill> findAll();

    List<Bill> findBillByClient(Client client);

    @Query(value = "select * from BILL join (select CLIENT_ID, CHECK_IN_TIME from CLIENT join ROOM R on R.ROOM_ID = CLIENT.ROOM_ID where R.ROOM_ID = ?1 and CHECK_OUT_TIME > SYSDATE) current_visitor on BILL.CLIENT_ID = current_visitor.CLIENT_ID where BILL_DATE > CHECK_IN_TIME", nativeQuery = true)
    List<Bill> findCurrentVisitorBillsFromRoom(Long roomId);

    List<Bill> findBillByClientClientIdAndBillDateBetween(Long clientId, LocalDate checkInTime, LocalDate checkoutTime);
}
