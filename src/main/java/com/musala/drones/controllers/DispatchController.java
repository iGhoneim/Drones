package com.musala.drones.controllers;

import com.musala.drones.entities.Drone;
import com.musala.drones.services.DroneService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DispatchController {

    private @Resource DroneService droneService;

    @PutMapping("/drone")
    public Drone registerDrone(@Valid @RequestBody Drone drone) {
        return droneService.register(drone);
    }


}
