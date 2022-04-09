package ru.nsu.hotel_db.booking;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class BookingDTO {
    @NotNull(message = "Hotel's class name cannot be empty!")
    @Min(1) @Max(5)
    private Integer hotelClass;
    @NotNull(message = "Room's floor cannot be blank!")
    @Positive(message = "Room's floor should be positive!")
    private Integer roomFloor;
    @NotNull(message = "Room's capacity cannot be blank!")
    @Positive(message = "Room's capacity should be positive!")
    private Integer roomCapacity;
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Booking date cannot be in the past!")
    private LocalDate startBooking;
    @NotNull(message = "Date cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Booking date cannot be in the past!")
    private LocalDate endBooking;
}
