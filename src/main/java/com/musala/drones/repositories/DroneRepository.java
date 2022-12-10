package com.musala.drones.repositories;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DroneRepository extends JpaRepository<Drone, UUID> {
    Optional<Drone> findBySerialNumber(String serialNumber);

    List<Drone> findAllByStateAndBatteryCapacityGreaterThanEqual(State state, Float batteryCapacity);
}
