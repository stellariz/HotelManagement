package ru.nsu.hotel_db.Entitiy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Bill  {
    @Id
    @GeneratedValue
    private Long billId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId")
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceId")
    private HotelsService hotelsService;
    private Boolean paidStatus;
}
