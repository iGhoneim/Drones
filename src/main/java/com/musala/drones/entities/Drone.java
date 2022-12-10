package com.musala.drones.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
@Entity
@Table
public class Drone extends Base {
    @Column(name = Fields.serialNumber)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = Fields.model)
    private Model model;
    @Column(name = Fields.weightLimit)
    private Float weightLimit;
    @Column(name = Fields.batteryCapacity)
    private Float batteryCapacity;
    @Enumerated(EnumType.STRING)
    @Column(name = Fields.state)
    private State state;
}
