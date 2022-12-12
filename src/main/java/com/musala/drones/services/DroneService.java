package com.musala.drones.services;

import com.musala.drones.data.BatteryResponse;
import com.musala.drones.entities.Drone;
import com.musala.drones.entities.State;
import com.musala.drones.exceptions.OperationException;
import com.musala.drones.repositories.DroneRepository;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneService {
    private @Resource DroneRepository droneRepository;

    public Drone registerDrone(Drone drone) {
        return droneRepository.saveAndFlush(drone);
    }

    public Optional<Drone> getDroneBySerialNumber(String serialNumber) {
        return droneRepository.findBySerialNumber(serialNumber);
    }

    public Optional<Drone> getAvailableDroneBySerialNumber(String serialNumber) {
        return droneRepository.findBySerialNumberAndStateAndBatteryCapacityGreaterThanEqual(serialNumber, State.IDLE, 25);
    }

    public BatteryResponse fetchDroneBattery(String serialNumber) {
        return getDroneBySerialNumber(serialNumber)
                .map(Drone::getBatteryCapacity)
                .map(battery -> StringUtils.appendIfMissing(String.valueOf(battery), "%"))
                .map(BatteryResponse::new)
                .orElseThrow(() -> new OperationException("Cannot fetch drone's battery"));
    }

    public List<Drone> listAllDrones() {
        return droneRepository.findAll();
    }

    public List<Drone> listAvailableDrones() {
        return droneRepository.findAllByStateAndBatteryCapacityGreaterThanEqual(State.IDLE, 25);
    }

    public Optional<Drone> listDroneMedications(String serialNumber) {
        return droneRepository.findMedicationsByDroneSerialNumber(serialNumber);
    }


}
