package com.musala.drones.jobs;

import com.musala.drones.entities.Drone;
import com.musala.drones.repositories.DroneRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BatteryAuditJob {
    private @Resource DroneRepository droneRepository;

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void chargeDrones() {
        log.info(droneRepository.findAll().stream()
                .sorted(Comparator.comparing(Drone::getSerialNumber))
                .map(drone -> String.format("Drone %s [%s]: Battery %d%%", drone.getSerialNumber(), drone.getState(), drone.getBatteryCapacity()))
                .collect(Collectors.joining(System.lineSeparator(), System.lineSeparator(), System.lineSeparator())));
    }
}