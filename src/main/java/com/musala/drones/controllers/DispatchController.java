package com.musala.drones.controllers;

import com.musala.drones.data.LoadRequest;
import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.facades.DispatchFacade;
import com.musala.drones.services.DroneService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class DispatchController {

    private @Resource DroneService droneService;
    private @Resource DispatchFacade dispatchFacade;

    @PutMapping("/drone")
    public Drone registerDrone(@Valid @RequestBody Drone drone) {
        return droneService.registerDrone(drone);
    }

    @GetMapping("/drones")
    public List<Drone> listAllDrones() {
        return droneService.listAllDrones();
    }

    @GetMapping("/drones/available")
    public List<Drone> listAvailableDrones() {
        return droneService.listAvailableDrones();
    }

    @GetMapping("/drone/{serialNumber}/battery")
    public String fetchDroneBattery(@PathVariable("serialNumber") String serialNumber) {
        return droneService.fetchDroneBattery(serialNumber);
    }

    @PostMapping("/drone/{serialNumber}/load")
    public Drone loadDroneWithMedications(@PathVariable("serialNumber") String droneSerialNumber, @RequestBody LoadRequest loadRequest) {
        return dispatchFacade.loadMedications(droneSerialNumber, loadRequest);
    }

    @GetMapping("/drone/{serialNumber}/view")
    public Collection<Medication> viewMedications(@PathVariable("serialNumber") String droneSerialNumber) {
        return dispatchFacade.viewMedications(droneSerialNumber);
    }

    @GetMapping("/drone/{serialNumber}/deliver")
    public void loadDroneWithMedications(@PathVariable("serialNumber") String droneSerialNumber) {
        dispatchFacade.deliverMedications(droneSerialNumber);
    }


}
