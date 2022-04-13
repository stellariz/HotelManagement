package ru.nsu.hotel_db.bills;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BuyingServiceClientDTO {
    @NotBlank(message = "Client's name cannot be blank!")
    private String name;
}
