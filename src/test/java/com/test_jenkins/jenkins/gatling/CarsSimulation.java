package com.test_jenkins.jenkins.gatling;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class CarsSimulation extends Simulation {
    HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:9090")
                    .acceptHeader("application/json");

    ScenarioBuilder getScenario = scenario("Get all cars")
            .exec(http("Get all cars")
                    .get("/cars")
                    .check(status().is(200)));

    ScenarioBuilder postScenario = scenario("Add a new car")
            .exec(http("Add a new car")
                    .post("/cars")
                    .header("Content-Type", "application/json")
                    .asJson()
                    .body(StringBody(
                            "{\"model\":\"Chiron\",\"brand\":\"Bugatti\", \"color\":\"Black\", \"type\":\"Hypercar\",\"price\":3500000.00, \"manufactureYear\":2021}"
                    ))
                    .check(status().is(200)));

    ScenarioBuilder putScenario = scenario("Update a car")
            .exec(http("Update a car")
                    .put("/cars/1")
                    .header("Content-Type", "application/json")
                    .asJson()
                    .body(StringBody(
                            "{\"model\":\"Model 3\",\"brand\":\"Tesla\", \"color\":\"Black\", \"type\":\"Sedan\",\"price\":40990.00, \"manufactureYear\":2023}"
                    ))
                    .check(status().is(200)));

    ScenarioBuilder deleteScenario = scenario("Delete a car")
            .exec(http("Delete a car")
                    .delete("/cars/1")
                    .check(status().is(204)));

    public CarsSimulation() {
        setUp(
                getScenario.injectOpen(constantUsersPerSec(10).during(60)),
                postScenario.injectOpen(constantUsersPerSec(10).during(60)),
                putScenario.injectOpen(constantUsersPerSec(10).during(60)),
                deleteScenario.injectOpen(constantUsersPerSec(10).during(60))
        ).protocols(httpProtocol);
    }
}
