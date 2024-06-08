package com.user_service.user.feignclients;

import com.user_service.user.models.Car;
import com.user_service.user.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "moto", url = "http://localhost:8082/api/v1/motos")
public interface MotoFeignClient {

    @PostMapping()
    public Moto store(@RequestBody Moto moto);

    @GetMapping("user/{userId}")
    public List<Moto> getMotos(@PathVariable("userId") Long userId);

}
