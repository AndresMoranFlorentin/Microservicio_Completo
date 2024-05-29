package com.example.bike_service.controller;

import com.example.bike_service.entity.Bike;
import com.example.bike_service.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    BikeService bikeService;
    @GetMapping("/all")
    public ResponseEntity<List<Bike>>getAll(){
        List<Bike> bikes=bikeService.getAll();
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }
    @GetMapping("/find-user-id/{userId}")
    public ResponseEntity<List<Bike>>findByUserId(@PathVariable("userId") int userId){
        List<Bike> bikes=bikeService.findByUserId(userId);
        return ResponseEntity.ok(bikes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bike>getById(@PathVariable("id")int id){
        Bike bike=bikeService.getUserById(id);
        if(bike==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }
    @PostMapping("/")
    public ResponseEntity<Bike>save(@RequestBody Bike bike){
        Bike bikeNew=bikeService.save(bike);
        return ResponseEntity.ok(bikeNew);
    }
}
