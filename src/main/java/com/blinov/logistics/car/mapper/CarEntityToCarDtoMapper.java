package com.blinov.logistics.car.mapper;

import com.blinov.logistics.car.dto.CarDto;
import com.blinov.logistics.car.model.CarEntity;
import com.blinov.logistics.car.model.CarType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CarEntityToCarDtoMapper {
    @Mapping(target = "carType", source = "carType", qualifiedByName = "mapType")
    CarDto mapTo(CarEntity carEntity);

    @Named("mapType")
    default String mapType(CarType type){
        return type.getDescription();
    }
}
