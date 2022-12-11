package com.musala.drones.facades;

import com.musala.drones.data.LoadRequest;
import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.entities.State;
import com.musala.drones.exceptions.DroneMedicationsOverloadException;
import com.musala.drones.exceptions.DroneNotRegisteredException;
import com.musala.drones.exceptions.MedicationNotRegisteredException;
import com.musala.drones.services.DroneService;
import com.musala.drones.services.MedicationService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DispatchFacade {
    private @Resource DroneService droneService;
    private @Resource MedicationService medicationService;

    @Transactional
    public Drone loadMedications(String droneSerialNumber, LoadRequest loadRequest) {
        var medicationsCodes = loadRequest.getMedications();
        synchronized (droneSerialNumber) {
            var drone = droneService.getAvailableDroneBySerialNumber(droneSerialNumber)
                    .orElseThrow(() -> new DroneNotRegisteredException(String.format("Cannot find registered or available drone with serial number [%s]", droneSerialNumber)));

            var medications = medicationService.getMedicationsByCodes(medicationsCodes);
            boolean canFetchMedications = medicationsCodes.size() == medications.size();
            if (!canFetchMedications) {
                medicationsCodes.removeAll(medications.stream().map(Medication::getCode).collect(Collectors.toSet()));
                throw new MedicationNotRegisteredException(String.format("Cannot find registered medications with codes [%s]", String.join(", ", medicationsCodes)));

            }
            boolean canLoadMedications = medications.stream().mapToDouble(Medication::getWeight).sum() <= drone.getWeightLimit();
            if (!canLoadMedications) {
                throw new DroneMedicationsOverloadException(String.format("Drone %s can only be loaded with %s", drone.getSerialNumber(), drone.getWeightLimit()));
            }
            drone.setState(State.LOADING);
            drone.setMedications(medications);
            drone.setState(State.LOADED);
            return drone;
        }

    }

    @Transactional
    public void deliverMedications(String droneSerialNumber) {
        synchronized (droneSerialNumber) {
            var drone = droneService.getDroneBySerialNumber(droneSerialNumber)
                    .orElseThrow(() -> new DroneNotRegisteredException(String.format("Cannot find a drone with serial number [%s]", droneSerialNumber)));
            if (drone.getState().equals(State.LOADED))
                drone.setState(State.DELIVERING);
        }
    }

    public Collection<Medication> viewMedications(String droneSerialNumber) {
        var drone = droneService.listDroneMedications(droneSerialNumber)
                .orElseThrow(() -> new DroneNotRegisteredException(String.format("Cannot find a drone with serial number [%s]", droneSerialNumber)));
        return drone.getMedications();
    }
}
