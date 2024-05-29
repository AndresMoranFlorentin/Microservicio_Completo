package com.tutorial.car_service.service;

import com.tutorial.car_service.entity.Car;
import com.tutorial.car_service.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;
    public List<Car> getAll(){
        return carRepository.findAll();
    }
    public Car getUserById(int id){
        return carRepository.findById(id).orElse(null);
    }
    public Car save(@RequestBody Car car){
        Car carNew=carRepository.save(car);
        return carNew;
    }
    public List<Car> findByUserId(int userId){
        return carRepository.findByUserId(userId);
    }
}
