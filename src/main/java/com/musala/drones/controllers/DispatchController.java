package com.musala.drones.controllers;

import com.musala.drones.entities.Drone;
import com.musala.drones.services.DroneService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class DispatchController {

    private @Resource DroneService droneService;

    @PutMapping("/drone")
    public Drone registerDrone(@Valid @RequestBody Drone drone) {
        return droneService.register(drone);
    }

    @GetMapping("/drone/{serialNumber}")
    public Drone droneInfo(@PathVariable("serialNumber") String serialNumber) {
        return droneService.info(serialNumber);
    }

    @GetMapping("/drone/{serialNumber}/battery")
    public String droneBattery(@PathVariable("serialNumber") String serialNumber) {
        return droneService.battery(serialNumber);
    }


}
