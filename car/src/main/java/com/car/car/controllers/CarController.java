package com.car.car.controllers;

import com.car.car.entities.Car;
import com.car.car.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<Car>> index() {
       List<Car> cars = carService.index();
       if (cars.isEmpty())
           return ResponseEntity.noContent().build();
       return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Car> show(@PathVariable("id") Long id) {
        Car car = carService.show(id);
        if (car == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(car);
    }

    @GetMapping("/user/{userId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Car>>showByUserId(@PathVariable Long userId) {
        List<Car> cars = carService.byUserId(userId);
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Car> store(@RequestBody Car car) {
        Car newCar = carService.store(car);
        URI location = URI.create("/cars/" + newCar.getId());
        return ResponseEntity.created(location).build(); // Devuelve en los headers una llave llamada Location cuyo valor es la url para acceder al recurso creado
    }

}
