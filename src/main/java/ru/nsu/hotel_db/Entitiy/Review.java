package ru.nsu.hotel_db.Entitiy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long reviewId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId")
    private Client client;
    private String review;
    private Integer rating;
    private LocalDate reviewDate;
}
