package com.user_service.user.services;

import com.user_service.user.entities.User;
import com.user_service.user.feignclients.CarFeignClient;
import com.user_service.user.feignclients.MotoFeignClient;
import com.user_service.user.models.Car;
import com.user_service.user.models.Moto;
import com.user_service.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private CarFeignClient carFeignClient;

    public Car storeCar(Long userId, Car car) {
        car.setUserId(userId);
        return carFeignClient.store(car);
    }

    @Autowired
    private MotoFeignClient motoFeignClient;

    public Moto storeMoto(Long userId, Moto moto) {
        moto.setUserId(userId);
        return motoFeignClient.store(moto);
    }

    public Map<String, Object> getUserAndVehicles(Long userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            result.put("Message", "User not found");
            return result;
        }
        result.put("User", user);

        List<Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty())
            result.put("Cars", "The user has not cars");
        else
            result.put("Cars", cars);

        List<Moto> motos = motoFeignClient.getMotos(userId);
        if (motos.isEmpty())
            result.put("Motos", "The user has not motos");
        else
            result.put("Motos", motos);

        return result;
    }

    @Autowired
    private UserRepository userRepository;

    public List<User>index() {
        return userRepository.findAll();
    }

    public User show(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User store(User user) {
        return userRepository.save(user);
    }

}
