package ru.nsu.hotel_db.organizations.filters;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class RoomsInPeriodDTOFilter {
    @NotNull(message = "Room's capacity cannot be blank!")
    @Positive(message = "Room's capacity should be positive!")
    private Integer totalRooms;
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstDate;
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate secondDate;
}
