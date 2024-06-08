package com.car.car.services;

import com.car.car.entities.Car;
import com.car.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> index() {
        return carRepository.findAll();
    }

    public Car show(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car store(Car car) {
        return carRepository.save(car);
    }

    public List<Car> byUserId(Long userId) {
        return carRepository.findByUserId(userId);
    }

}
