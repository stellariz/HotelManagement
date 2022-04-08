package ru.nsu.hotel_db.Entitiy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue
    private Long orgId;
    @Column(unique = true)
    private String name;
    private Float sale;
}