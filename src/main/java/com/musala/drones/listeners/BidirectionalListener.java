package com.musala.drones.listeners;

import com.musala.drones.entities.Base;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Collection;

/**
 * @implSpec <a href="https://medium.com/@Ghoneim/generic-hibernate-spring-data-jpa-bidirectional-onetomany-manytoone-cascading-consistency-using-f1b2dcae3e2a">Generic Hibernate/Spring Data JPA bidirectional @OneToMany/@ManyToOne cascading & consistency using EntityListener</a>
 */
@Component
public class BidirectionalListener {
    @PrePersist
    @PreUpdate
    private void execute(@NonNull Base parent) {
        ReflectionUtils.doWithFields(parent.getClass(), parentField -> {
            ReflectionUtils.makeAccessible(parentField);
            if (ReflectionUtils.getField(parentField, parent) instanceof final Collection<?> parentValues)
                parentValues.stream().filter(parentValue -> parentValue instanceof Base)
                        .map(Base.class::cast)
                        .forEach(child -> ReflectionUtils.doWithFields(child.getClass(), childField -> {
                            ReflectionUtils.makeAccessible(childField);
                            if (childField.getType().equals(parentField.getDeclaringClass()))
                                ReflectionUtils.setField(childField, child, parent);
                        }, childField -> childField.isAnnotationPresent(ManyToOne.class)));
        }, parentField -> parentField.isAnnotationPresent(OneToMany.class));
    }
}
