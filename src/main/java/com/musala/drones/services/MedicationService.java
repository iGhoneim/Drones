package com.musala.drones.services;

import com.musala.drones.entities.Medication;
import com.musala.drones.repositories.MedicationRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {
    private @Resource MedicationRepository medicationRepository;

    public Optional<Medication> getMedicationByCode(String code) {
        return medicationRepository.findByCode(code);
    }

    public List<Medication> getMedicationsByCodes(Collection<String> codes) {
        return medicationRepository.findAllByCodeIn(codes);
    }
}
