package com.blinov.logistics.car.service;

import com.blinov.logistics.car.dao.CarRepo;
import com.blinov.logistics.car.dto.CarDto;
import com.blinov.logistics.car.mapper.CarDtoToCarEntityMapper;
import com.blinov.logistics.car.mapper.CarEntityToCarDtoMapper;
import com.blinov.logistics.car.model.CarEntity;
import com.blinov.logistics.car.model.CarType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервисный слой для работы с Car
 */
@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    @Autowired
    private final CarRepo carRepo;
    @Autowired
    private final CarDtoToCarEntityMapper carDtoToCarEntityMapper;
    @Autowired
    private final CarEntityToCarDtoMapper carEntityToCarDtoMapper;

    /**
     * Сохранить Car
     * @param carDto
     */
    @Override
    public CarEntity createCar(CarDto carDto) {
        return carRepo.save(carDtoToCarEntityMapper.mapTo(carDto));
    }

    /**
     * Получить Car по id
     * @param id
     * @return
     */
    @Override
    public CarDto getCar(String id) {
        return carEntityToCarDtoMapper.mapTo(carRepo.getById(id));
    }

    /**
     * Изменить Car
     * @param id, carDto
     */
    @Override
    public CarEntity updateCar(String id, CarDto carDto) {
        CarEntity car = carRepo.getById(id);
        if (carDto.getGosNumber() != null) {
            car.setGosNumber(carDto.getGosNumber());
        }
        if (carDto.getCarType() != null) {
            car.setCarType(CarType.getCarType(carDto.getCarType()));
            ;
        }
        car.setModel(carDto.getModel());
        return carRepo.save(car);
    }

    @Override
    public void deleteCar(String id) {
        carRepo.deleteById(id);
    }
}
