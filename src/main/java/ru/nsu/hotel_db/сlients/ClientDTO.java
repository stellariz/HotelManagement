package ru.nsu.hotel_db.сlients;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ClientDTO {
    @NotBlank(message = "Name cannot be empty!")
    private String name;
    @NotNull(message = "Check-in date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Check in date cannot be in the past!")
    private LocalDate checkInTime;
    @NotNull(message = "Check-in date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Check-out date cannot be in the past!")
    private LocalDate checkOutTime;
}
