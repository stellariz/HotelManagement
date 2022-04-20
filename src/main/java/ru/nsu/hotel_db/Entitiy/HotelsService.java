package ru.nsu.hotel_db.Entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelsService {
    @Id
    @GeneratedValue
    private Long serviceId;
    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;
    @Column(unique = true)
    private String name;
    private Float price;
    private Integer rating;
}
