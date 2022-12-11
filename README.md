# Musala Soft

## Drones™ <sub>built with ♥ by Ghoneim</sub>

---

### What to expect?

By following the next steps you would have a running Spring Boot application, which is able to respond to requests
from `./Rest/Dispatch.rest`, and maintain its data in containerized Postgres `./Pods/PostgreSQL`

###### Limitations "intentionally for Simplicity"

* A very simple `Basic` authentication using InMemory UserDetailsService, a JWS token could be implemented or SSO via
  Keycloak.
* Basic `ControllerAdvices` along with few `CustomExceptions`.
* `Seeders` read from `classpath` Json files; A robust generator using `EasyRandom` would be better, and would
  facilitate JUnit.
* `classpath` Json files should be filtered based on profiles (Ex. Production) using `maven-resources-plugin`
* `application-prod.yml` and `log4j2.xml` should be externalized.
* Username & Passwords should be sourced either provided by environment variables, or from `Spring Cloud Config`
  service,
  or at least encrypted in `application.properties` using `Jasypt`
* Logs could be better viewed on `Zipkin`
* .. etc.
* `Medication` images are implemented as `Strings` which could be filled with `Base64` encoded images, or URL. In case
  of URLs, a `CDN` could be used instead of serving static resources.
* Multiple `Medications` can be loaded to one `Drone`. However, A `Medication` can only be loaded to one `Drone`
* `Liquibase` or `Flyway` should be used for database tracking and migrations.

---

## Build & Deployment

### _on_ Development

* ###### Requirements:
    * Any JDK >= 17

* ###### Build:
    * Spin up a Postgres container by running `docker-compose up --build` in `./Pods/PostgreSQL`.
    * Build project by running `mvn clean package` command in the `Drones` directory.
    * Launch project by running `mvn spring-boot:run` command in the `Drones` directory.

* ###### Notes:
    * Development configurations could be changed via `application-dev.yml`.
    * Development profile is active by default.

---

### _on_ Production

* ###### Requirements:
    * JDK
        * In case of JIT; Any JDK >= 17
        * In case of AOT; GraalVM 22.3

* ###### Build:
    * Configure your preferable DBMS via `application-prod.yml`
    * Build project by one of the following methods:
        * Either by create JIT jar by running `mvn -Pprod clean package` command in the `Drones` directory.
        * Or by creating AOT native image by running `mvn -Pnative native:compile` command in the `Drones` directory.
    * Deploy the jar or the binary created by the previous step.
* ###### Notes:
    * Seeders only works in development mode.
    * If `./Pods/PostgreSQL` is used, a volume must be added to persist data.

---

### API Testing

* `./Rest/Dispatch.rest` contains multiple examples, which covers task requirements.

---

### Ports

| SERVICE  | PORT                           |
|----------|--------------------------------|
| Drones   | 8080 (Dev), **dynamic** (Prod) |
| Postgres | 5432                           |

