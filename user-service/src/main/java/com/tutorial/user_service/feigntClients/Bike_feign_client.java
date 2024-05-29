package com.tutorial.user_service.feigntClients;

import com.tutorial.user_service.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bike-service",url = "http://localhost:8003")
public interface Bike_feign_client {
    @PostMapping("/bike/")
    public Bike save(@RequestBody Bike bike);
    @GetMapping("/bike/find-user-id/{userId}")
    public List<Bike>findByUserId(@PathVariable("userId") int userId);
}
