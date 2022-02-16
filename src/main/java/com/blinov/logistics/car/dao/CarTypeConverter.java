package com.blinov.logistics.car.dao;

import com.blinov.logistics.car.model.CarType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CarTypeConverter implements AttributeConverter<CarType, String> {
    @Override
    public String convertToDatabaseColumn(CarType carType) {
        return carType.getDescription();
    }

    @Override
    public CarType convertToEntityAttribute(String carType) {
        return CarType.getCarType(carType);
    }
}
