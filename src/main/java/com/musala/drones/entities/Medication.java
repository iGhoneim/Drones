package com.musala.drones.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class Medication extends Base {
    @Column(name = Fields.name)
    private String name;
    @Column(name = Fields.weight)
    private Float weight;
    @Column(name = Fields.code)
    private String code;
    @Column(name = Fields.image)
    private String image;
}
