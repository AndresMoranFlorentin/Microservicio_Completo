package com.example.bike_service.service;

import com.example.bike_service.entity.Bike;
import com.example.bike_service.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    BikeRepository carRepository;
    public List<Bike> getAll(){
        return carRepository.findAll();
    }
    public Bike getUserById(int id){
        return carRepository.findById(id).orElse(null);
    }
    public Bike save(Bike car){
        Bike carNew=carRepository.save(car);
        return carNew;
    }
    public List<Bike> findByUserId(int userId){
        return carRepository.findByUserId(userId);
    }
}
