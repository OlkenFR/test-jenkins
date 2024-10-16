package com.test_jenkins.jenkins.controllers;

import com.test_jenkins.jenkins.models.Car;
import com.test_jenkins.jenkins.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // GET all cars
    @GetMapping
    public Iterable<Car> getAllCars() {
        return carService.getAllCars();
    }

    // GET one car by id
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
            .map(car -> ResponseEntity.ok().body(car))
            .orElse(ResponseEntity.notFound().build());
    }

    // POST a new car
    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    // PUT update a car
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    // DELETE a car
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
