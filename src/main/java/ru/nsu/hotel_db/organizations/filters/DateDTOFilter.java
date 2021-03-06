package ru.nsu.hotel_db.organizations.filters;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class DateDTOFilter {
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstDate;
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate secondDate;
}
