package com.musala.drones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "One of more medications are not registered.")
public class MedicationNotRegisteredException extends OperationException {
    public MedicationNotRegisteredException(String message) {
        super(message);

    }
}
