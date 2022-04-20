package ru.nsu.hotel_db.сlients.filters;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class DateAndRoomDTOFilter {
    @NotNull(message = "Room's capacity cannot be blank!")
    @Positive(message = "Room's capacity should be positive!")
    private Integer capacity;
    @NotNull(message = "Room's floor cannot be blank!")
    @Positive(message = "Room's floor should be positive!")
    private Integer floor;
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate firstDate;
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate secondDate;
}
