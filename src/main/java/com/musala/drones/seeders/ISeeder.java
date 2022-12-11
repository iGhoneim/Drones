package com.musala.drones.seeders;

import com.google.gson.Gson;
import com.musala.drones.entities.Base;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Profile(value = {"!test", "!prod"})
@Component
@Slf4j
public abstract class ISeeder implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        log.info("Seeding {}", this.getClass().getSimpleName());
    }

    public <T extends Base> void seed(JpaRepository<T, UUID> repository, Resource resource, Class<T[]> clazz) {
        if (repository.count() != 0) return;
        try (final InputStream in = resource.getInputStream();
             final Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            var models = new Gson().fromJson(reader, clazz);
            if (Objects.isNull(models)) return;
            repository.saveAllAndFlush(Arrays.asList(models));
            log.info("{} {} seeded.", models.length, this.getClass().getSimpleName());
        } catch (IOException e) {
            log.warn("Cannot seed Drones!");
        }
    }
}

