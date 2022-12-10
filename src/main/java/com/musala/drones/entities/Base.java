package com.musala.drones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.drones.listeners.BidirectionalListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
@MappedSuperclass
@EntityListeners(BidirectionalListener.class)
public abstract class Base implements Serializable {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Fields.id, updatable = false, nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (other instanceof final Base base)
            return this.id != null && this.id.equals(base.id);
        return false;
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : this.getClass().hashCode();
    }
}

