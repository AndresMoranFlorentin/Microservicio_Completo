package com.tutorial.user_service.feigntClients;

import com.tutorial.user_service.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service",url = "http://localhost:8002")
//@RequestMapping("/car")
public interface Car_feign_client {
    @PostMapping("/car/")
    public Car save(@RequestBody Car car);

    @GetMapping("/car/find-user-id/{userId}")
    public List<Car> findByUserId(@PathVariable("userId") int userId);
}
