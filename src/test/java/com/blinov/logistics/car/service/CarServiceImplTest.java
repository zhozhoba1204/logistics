package com.blinov.logistics.car.service;

import com.blinov.logistics.car.dao.CarRepo;
import com.blinov.logistics.car.dto.CarDto;
import com.blinov.logistics.car.mapper.CarDtoToCarEntityMapper;
import com.blinov.logistics.car.mapper.CarEntityToCarDtoMapper;
import com.blinov.logistics.car.model.CarEntity;
import com.blinov.logistics.car.model.CarType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;
    @Mock
    private CarRepo carRepo;
    @Mock
    private CarEntityToCarDtoMapper carEntityToCarDtoMapper;
    @Mock
    private CarDtoToCarEntityMapper carDtoToCarEntityMapper;

    public CarEntity getCarEntity(){
        return CarEntity.builder()
                .gosNumber("A111")
                .carType(CarType.C)
                .model("GAZ")
                .build();
    }
    public CarDto getCarDto(){
        return CarDto.builder()
                .gosNumber("A111")
                .carType("Грузовой")
                .model("GAZ")
                .build();
    }
    @Test
    void createCar() {
        Mockito.when(carRepo.save(Mockito.any())).thenReturn(getCarEntity());
        CarEntity car = carService.createCar(getCarDto());
        assertNotNull(car);
        assertEquals("A111",car.getGosNumber());
        assertEquals(CarType.C,car.getCarType());
        assertEquals("GAZ",car.getModel());
    }

    @Test
    void getCar() {
        Mockito.when(carRepo.getById(Mockito.anyString())).thenReturn(getCarEntity());
        Mockito.when(carEntityToCarDtoMapper.mapTo(Mockito.any(CarEntity.class)))
                .thenReturn(getCarDto());
        CarDto carDto = carService.getCar("1");
        Mockito.verify(carRepo, Mockito.times(1)).getById("1");
        assertNotNull(carDto);
        assertEquals("A111",carDto.getGosNumber());
        assertEquals("Грузовой",carDto.getCarType());
        assertEquals("GAZ",carDto.getModel());
    }

}