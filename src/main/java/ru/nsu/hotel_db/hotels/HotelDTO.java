package ru.nsu.hotel_db.hotels;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class HotelDTO {
    @NotBlank(message = "Hotel's name cannot be blank!")
    private String name;
    @NotNull(message = "Hotel's levels cannot be empty!")
    @Positive(message = "Hotel's levels cannot be less or equal to zero!")
    private Integer levels;
    @NotNull(message = "Hotel's class name cannot be empty!")
    @Min(1) @Max(5)
    private Integer hotelClass;
}
