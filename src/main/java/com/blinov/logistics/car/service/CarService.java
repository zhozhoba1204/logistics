package com.blinov.logistics.car.service;

import com.blinov.logistics.car.dto.CarDto;
import com.blinov.logistics.car.model.CarEntity;

public interface CarService {
    CarEntity createCar(CarDto carDto);
    CarDto getCar(String id);
    CarEntity updateCar(String id, CarDto carDto);
    void deleteCar(String id);
}
