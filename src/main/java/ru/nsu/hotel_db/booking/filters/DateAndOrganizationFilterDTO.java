package ru.nsu.hotel_db.booking.filters;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class DateAndOrganizationFilterDTO {
    @NotBlank(message = "Organization's name cannot be blank!")
    private String name;
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate firstDate;
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate secondDate;
}
