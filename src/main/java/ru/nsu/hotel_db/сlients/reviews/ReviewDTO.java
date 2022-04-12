package ru.nsu.hotel_db.сlients.reviews;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class ReviewDTO {
    @NotBlank(message = "Client's name cannot be blank!")
    private String clientName;
    @NotBlank(message = "Client's name cannot be blank!")
    private String review;
    @Min(1) @Max(5)
    private Integer rating;
    private LocalDate localDate;
}
