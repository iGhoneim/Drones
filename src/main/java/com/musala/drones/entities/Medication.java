package com.musala.drones.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^[\\w-]+$", message = "Medication's name allows only only letters, numbers, dashes, and underscores")
    @NotBlank(message = "Medication's name is mandatory, and cannot be empty")
    @Column(name = Fields.name)
    private String name;
    @DecimalMin(value = "0.0", message = "Medication's weight min. value is 0gr")
    @DecimalMax(value = "500.0", message = "Medication's weight max. value is 500gr")
    @NotNull(message = "Medication's weight is mandatory, and cannot be empty")
    @Column(name = Fields.weight)
    private Float weight;
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Medication's code allows only only upper case letters, numbers, and underscores")
    @NotBlank(message = "Medication's code is mandatory, and cannot be empty")
    @Column(name = Fields.code, unique = true)
    private String code;
    @Column(name = Fields.image)
    private String image;
}
