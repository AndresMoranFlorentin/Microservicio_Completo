package com.tutorial.car_service.controller;

import com.tutorial.car_service.entity.Car;
import com.tutorial.car_service.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;
    @GetMapping("/all")
    public ResponseEntity<List<Car>>getAll(){
        List<Car> cars=carService.getAll();
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/find-user-id/{userId}")
    public ResponseEntity<List<Car>>findByUserId(@PathVariable("userId") int userId){
        List<Car> cars=carService.findByUserId(userId);
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car>getById(@PathVariable("id")int id){
        Car  car=carService.getUserById(id);
        if(car==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }
    @PostMapping("/")
    public ResponseEntity<Car>save(@RequestBody Car car){
        Car carNew=carService.save(car);
        return ResponseEntity.ok(carNew);
    }
}
