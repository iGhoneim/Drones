package com.musala.drones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Drone cannot be overloaded with medications.")
public class DroneMedicationsOverloadException extends OperationException {
    public DroneMedicationsOverloadException(String message) {
        super(message);

    }
}
