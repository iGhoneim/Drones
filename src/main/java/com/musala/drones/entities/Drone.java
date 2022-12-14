package com.musala.drones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
@Entity
@Table
public class Drone extends Base {
    @Size(max = 100, message = "Drone's serial number cannot exceed 100 characters")
    @NotBlank(message = "Drone's serial number is mandatory, and cannot be empty")
    @Column(name = Fields.serialNumber, unique = true)
    private String serialNumber;
    @NotNull(message = "Drone's model is mandatory, and cannot be empty")
    @Enumerated(EnumType.STRING)
    @Column(name = Fields.model)
    private Model model;
    @DecimalMin(value = "0.0", message = "Drone's weight limit min. value is 0gr")
    @DecimalMax(value = "500.0", message = "Drone's weight limit max. value is 500gr")
    @NotNull(message = "Drone's weight limit is mandatory, and cannot be empty")
    @Column(name = Fields.weightLimit)
    private Float weightLimit;
    @JsonIgnore
    @OneToMany(targetEntity = Medication.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Collection<Medication> medications;
    @NotNull(message = "Drone's state is mandatory, and cannot be empty")
    @Enumerated(EnumType.STRING)
    @Column(name = Fields.state)
    private State state = State.IDLE;
    @Min(value = 0, message = "Drone's battery capacity min. value is 0%")
    @Max(value = 100, message = "Drone's battery capacity max. values is 100%")
    @Column(name = Fields.batteryCapacity)
    private Integer batteryCapacity = 100;
}
