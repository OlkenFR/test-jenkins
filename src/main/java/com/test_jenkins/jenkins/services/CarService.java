package com.test_jenkins.jenkins.services;

import com.test_jenkins.jenkins.models.Car;
import com.test_jenkins.jenkins.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car newCar) {
        return carRepository.findById(id).map(car -> {
            car.setModel(newCar.getModel());
            car.setBrand(newCar.getBrand());
            car.setColor(newCar.getColor());
            car.setType(newCar.getType());
            car.setPrice(newCar.getPrice());
            car.setManufactureYear(newCar.getManufactureYear());
            return carRepository.save(car);
        }).orElseGet(() -> {
            newCar.setId(id);
            return carRepository.save(newCar);
        });
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
