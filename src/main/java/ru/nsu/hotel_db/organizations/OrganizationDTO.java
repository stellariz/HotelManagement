package ru.nsu.hotel_db.organizations;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class OrganizationDTO {
    @NotBlank(message = "Organization's name cannot be blank!")
    String name;
    @NotNull(message = "Sale cannot be empty!")
    @Positive(message = "Sale cannot be less or equal to zero!")
    @Max(100)
    Float sale;
}
