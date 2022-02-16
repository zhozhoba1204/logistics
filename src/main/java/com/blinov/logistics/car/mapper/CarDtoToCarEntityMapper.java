package com.blinov.logistics.car.mapper;

import com.blinov.logistics.car.dto.CarDto;
import com.blinov.logistics.car.model.CarEntity;
import com.blinov.logistics.car.model.CarType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CarDtoToCarEntityMapper {
    @Mapping(target = "carType", source = "carType", qualifiedByName = "mapType")
    CarEntity mapTo(CarDto carDto);

    @Named("mapType")
    default CarType mapType(String description) {
      return CarType.getCarType(description);
    }
}
