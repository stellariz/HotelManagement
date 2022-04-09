package ru.nsu.hotel_db.Entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue
    private Long roomId;
    private Integer roomNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelId")
    private Hotel hotel;
    private Integer capacity;
    private Integer popularity;
    private Integer floor;
    private Float servicePrice;
}
