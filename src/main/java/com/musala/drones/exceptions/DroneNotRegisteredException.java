package com.musala.drones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "A drone is not registered.")
public class DroneNotRegisteredException extends OperationException {
    public DroneNotRegisteredException(String message) {
        super(message);
    }
}
