package ru.nsu.hotel_db.Entitiy;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill  {
    @Id
    @GeneratedValue
    private Long billId;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "serviceId")
    private HotelsService hotelsService;
    private LocalDate billDate;
    private Boolean paidStatus;
}
