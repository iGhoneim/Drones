package com.musala.drones.repositories;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.State;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DroneRepository extends JpaRepository<Drone, UUID> {
    Optional<Drone> findBySerialNumber(String serialNumber);

    Optional<Drone> findBySerialNumberAndStateAndBatteryCapacityGreaterThanEqual(String serialNumber, State state, Float batteryCapacity);

    List<Drone> findAllByStateAndBatteryCapacityGreaterThanEqual(State state, Float batteryCapacity);

    @Modifying
    @Transactional
    @Query("UPDATE Drone drone set drone.batteryCapacity = drone.batteryCapacity + 1 where drone.state in('IDLE', 'LOADING', 'LOADED') and drone.batteryCapacity < 100")
    void chargeIdleDrones();

    @Modifying
    @Transactional
    @Query("UPDATE Drone drone set drone.batteryCapacity = drone.batteryCapacity - 1 where drone.state in ('DELIVERING', 'DELIVERED', 'RETURNING') and drone.batteryCapacity > 0")
    void drainNonIdleDrones();

    @Modifying
    @Transactional
    List<Drone> findAllByState(State state);

    @Query(value = "SELECT drone FROM Drone drone JOIN FETCH drone.medications where drone.serialNumber = :serialNumber")
    Optional<Drone> findMedicationsByDroneSerialNumber(String serialNumber);
}
