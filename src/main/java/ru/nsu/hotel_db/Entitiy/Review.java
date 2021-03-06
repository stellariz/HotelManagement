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
public class Review {
    @Id
    @GeneratedValue
    private Long reviewId;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
    private String review;
    private Integer rating;
    private LocalDate reviewDate;
}
