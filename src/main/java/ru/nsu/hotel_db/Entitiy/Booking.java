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
public class Booking {
    @Id
    @GeneratedValue
    private Long bookingId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgId")
    private Organization organization;
    private LocalDate bookingStartDate;
    private LocalDate bookingEndDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId")
    private Room room;
}
