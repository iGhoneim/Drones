package com.musala.drones.seeders;

import com.musala.drones.entities.Drone;
import com.musala.drones.repositories.DroneRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!TEST")
@Component
@Slf4j
public class Drones extends ISeeder {
    public @Value("classpath:Seeds/Drones.json") org.springframework.core.io.Resource dronesResource;
    private @Resource DroneRepository droneRepository;

    @Override
    public void run(ApplicationArguments args) {
        super.run(args);
        super.seed(droneRepository, dronesResource, Drone[].class);
    }
}
