package com.tutorial.user_service.controller;

import com.tutorial.user_service.entity.User;
import com.tutorial.user_service.model.Bike;
import com.tutorial.user_service.model.Car;
import com.tutorial.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/all")
    public ResponseEntity<List<User>>getAll(){
        List<User> users=userService.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>>getCar(@PathVariable("userId")int userId){
        User user= userService.getUserById(userId);
        if(user==null) {
            return ResponseEntity.noContent().build();
        }
        List<Car> cars=userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>>getBike(@PathVariable("userId")int userId){
        User user= userService.getUserById(userId);
        if(user==null) {
            return ResponseEntity.noContent().build();
        }
        List<Bike> bikes=userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }
    @GetMapping("/cars-id-user/{userId}")
    public ResponseEntity<List<Car>>getCarPorIdUsuario(@PathVariable("userId")int userId){
        User user= userService.getUserById(userId);
        if(user==null) {
            return ResponseEntity.noContent().build();
        }
        List<Car> cars=userService.getCarsPorIdUsuario(userId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User>getById(@PathVariable("id")int id){
        User  user=userService.getUserById(id);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping("/getAll/{userId}")
     public ResponseEntity<Map<String,Object>>getAllVehiculos(@PathVariable("userId") int userId){
        Map<String,Object>resultados=userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(resultados);
    }
    @PostMapping("/")
    public ResponseEntity<User>save(@RequestBody User user){
        User userNew=userService.save(user);
        return ResponseEntity.ok(userNew);
    }
    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car>saveCar(@PathVariable("userId")int userId,@RequestBody Car car){
        Car newCar=userService.saveCar(userId,car);
        return ResponseEntity.ok(newCar);
    }
    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike>saveBike(@PathVariable("userId")int userId,@RequestBody Bike bike){
        Bike newBike=userService.saveBike(userId,bike);
        return ResponseEntity.ok(newBike);
    }
}
