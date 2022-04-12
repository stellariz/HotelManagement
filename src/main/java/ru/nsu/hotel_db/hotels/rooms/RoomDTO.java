package ru.nsu.hotel_db.hotels.rooms;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class RoomDTO {
    @NotNull(message = "Room's capacity cannot be blank!")
    @Positive(message = "Room's number should be positive!")
    private Integer roomNumber;
    @NotNull(message = "Room's capacity cannot be blank!")
    @Positive(message = "Room's capacity should be positive!")
    private Integer capacity;
    @NotNull(message = "Room's floor cannot be blank!")
    @Positive(message = "Room's floor should be positive!")
    private Integer floor;
    @NotNull(message = "Room's service price cannot be empty!")
    @Positive(message = "Room's service price should be positive!")
    private Float servicePrice;
}
