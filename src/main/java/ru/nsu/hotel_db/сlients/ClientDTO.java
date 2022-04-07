package ru.nsu.hotel_db.сlients;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ClientDTO {
    @NotBlank(message = "Name cannot be empty!")
    private String name;
    @NotNull(message = "Check-in date cannot be empty!")
    private LocalDate checkInTime;
    @NotNull(message = "Check-in date cannot be empty!")
    private LocalDate checkOutTime;
    private String organization;
}
