package com.musala.drones.services;

import com.musala.drones.entities.Drone;
import com.musala.drones.repositories.DroneRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DroneService {
    private @Resource DroneRepository droneRepository;

    public Drone register(Drone drone) {
        return droneRepository.saveAndFlush(drone);
    }
}
