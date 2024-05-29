package com.tutorial.user_service.service;

import com.tutorial.user_service.entity.User;
import com.tutorial.user_service.feigntClients.Bike_feign_client;
import com.tutorial.user_service.feigntClients.Car_feign_client;
import com.tutorial.user_service.model.Bike;
import com.tutorial.user_service.model.Car;
import com.tutorial.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Car_feign_client carFeignClient;
    @Autowired
    Bike_feign_client bikeFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        User userNew = userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/find-user-id/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/find-user-id/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            result.put("Mensaje", "no existe el usuario introducido");
            return result;
        }
        else {
            result.put("User", user);
            List<Car> cars = carFeignClient.findByUserId(userId);
            if (cars.isEmpty()) {
                result.put("Cars", "ese usuario no tiene autos");
            } else {
                result.put("Cars", cars);
                List<Bike> bikes = bikeFeignClient.findByUserId(userId);
                if (bikes.isEmpty()) {
                    result.put("Bikes", "ese usuario no tiene bicicletas");
                } else {
                    result.put("Bikes", bikes);
                    return result;
                }
            }
            return result;
        }
    }
    public List<Car> getCarsPorIdUsuario(int userId) {
        List cars=carFeignClient.findByUserId(userId);
        return cars;
    }
}
