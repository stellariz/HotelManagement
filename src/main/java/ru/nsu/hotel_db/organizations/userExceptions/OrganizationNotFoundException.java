package ru.nsu.hotel_db.organizations.userExceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrganizationNotFoundException extends Exception {
    public OrganizationNotFoundException(String message){
        super(message);
    }
}
