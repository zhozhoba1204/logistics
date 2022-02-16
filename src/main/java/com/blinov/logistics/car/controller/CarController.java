package com.blinov.logistics.car.controller;

import com.blinov.logistics.car.dto.CarDto;
import com.blinov.logistics.car.model.CarEntity;
import com.blinov.logistics.car.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер для работы с Car
 */
@RestController
@AllArgsConstructor
public class CarController {
    @Autowired
    private final CarService carService;

    /**
     * Добавить Car
     * @param carDto
     * @return
     */
    @PostMapping("/createCar")
    public CarEntity createCar(@RequestBody CarDto carDto) {
        return carService.createCar(carDto);
    }
    /**
     * Получить Car по id
     * @param id
     * @return
     */
    @GetMapping("/getCar/{id}")
    public CarDto getById(@PathVariable(name = "id") String id) {
        return carService.getCar(id);
    }

    @PostMapping("/updateCar/{id}")
    public CarEntity updateCar(@PathVariable(name = "id") String id, @RequestBody CarDto carDto) {
        return carService.updateCar(id, carDto);
    }

    /**
     * Удалить Car по номеру
     * @param id
     * @return
     */
    @GetMapping("deleteCar/{id}")
    public Map<String, String> deleteCar(@PathVariable(name = "id") String id) {
        carService.deleteCar(id);
        Map<String, String> result = new HashMap<>();
        result.put("result","success");
        return result;
    }
}
