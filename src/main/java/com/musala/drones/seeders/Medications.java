package com.musala.drones.seeders;

import com.musala.drones.entities.Medication;
import com.musala.drones.repositories.MedicationRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!TEST")
@Component
@Slf4j
public class Medications extends ISeeder {
    public @Value("classpath:Seeds/Medications.json") org.springframework.core.io.Resource medicationResource;
    private @Resource MedicationRepository medicationRepository;

    @Override
    public void run(ApplicationArguments args) {
        super.run(args);
        super.seed(medicationRepository, medicationResource, Medication[].class);
    }
}
