package ru.nsu.hotel_db.hotels.rooms.filters;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class FreeDateDTO {
    @FutureOrPresent
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate freeDate;
}
