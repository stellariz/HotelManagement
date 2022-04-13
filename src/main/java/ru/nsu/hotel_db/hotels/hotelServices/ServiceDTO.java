package ru.nsu.hotel_db.hotels.hotelServices;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ServiceDTO {
    @NotBlank(message = "Name of service cannot be blank!")
    private String name;
    @Positive(message = "Price should be greater zero!")
    @NotNull(message = "Price cannot be empty!")
    private Float price;
    private Float rating;
}
