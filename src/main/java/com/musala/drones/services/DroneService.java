package com.musala.drones.services;

import com.musala.drones.entities.Drone;
import com.musala.drones.exceptions.OperationException;
import com.musala.drones.repositories.DroneRepository;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DroneService {
    private @Resource DroneRepository droneRepository;

    public Drone register(Drone drone) {
        return droneRepository.saveAndFlush(drone);
    }

    public Drone info(String serialNumber) {
        return droneRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new OperationException("Cannot fetch drone's info"));
    }

    public String battery(String serialNumber) {
        return droneRepository.findBySerialNumber(serialNumber)
                .map(Drone::getBatteryCapacity)
                .map(battery -> StringUtils.appendIfMissing(String.valueOf(battery), "%"))
                .orElseThrow(() -> new OperationException("Cannot fetch drone's battery"));
    }


}
