package com.musala.drones.jobs;

import com.musala.drones.entities.State;
import com.musala.drones.repositories.DroneRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class DroneDispatchJob {
    private @Resource DroneRepository droneRepository;

    @Transactional
    @Scheduled(fixedDelay = 15, timeUnit = TimeUnit.SECONDS)
    public void chargeDrones() {
        droneRepository.findAllByState(State.RETURNING)
                .forEach(drone -> drone.setState(State.IDLE));

        droneRepository.findAllByState(State.DELIVERED)
                .forEach(drone -> drone.setState(State.RETURNING));

        droneRepository.findAllByState(State.DELIVERING)
                .forEach(drone -> drone.setState(State.DELIVERED));
    }
}