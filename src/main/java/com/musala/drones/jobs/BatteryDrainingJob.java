package com.musala.drones.jobs;

import com.musala.drones.repositories.DroneRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class BatteryDrainingJob {
    private @Resource DroneRepository droneRepository;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void chargeDrones() {
        droneRepository.drainNonIdleDrones();
    }
}