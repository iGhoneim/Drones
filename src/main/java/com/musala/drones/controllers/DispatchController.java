package com.musala.drones.controllers;

import com.musala.drones.entities.Drone;
import com.musala.drones.services.DroneService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DispatchController {

    private @Resource DroneService droneService;

    @PutMapping("/drone")
    public Drone registerDrone(@Valid @RequestBody Drone drone) {
        return droneService.registerDrone(drone);
    }


    @GetMapping("/drones")
    public List<Drone> listDrones() {
        return droneService.listDrones();
    }

    @GetMapping("/drones/available")
    public List<Drone> availableDrones() {
        return droneService.availableDrones();
    }

    @GetMapping("/drone/{serialNumber}")
    public Drone droneInfo(@PathVariable("serialNumber") String serialNumber) {
        return droneService.droneInfo(serialNumber);
    }

    @GetMapping("/drone/{serialNumber}/battery")
    public String droneBattery(@PathVariable("serialNumber") String serialNumber) {
        return droneService.droneBattery(serialNumber);
    }
}
