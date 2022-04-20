package ru.nsu.hotel_db.Entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue
    private Long clientId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;
    private LocalDate checkInTime;
    private LocalDate checkOutTime;
}
