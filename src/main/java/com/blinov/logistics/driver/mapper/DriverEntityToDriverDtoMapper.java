package com.blinov.logistics.driver.mapper;

import com.blinov.logistics.car.dto.CarDto;
import com.blinov.logistics.car.mapper.CarEntityToCarDtoMapper;
import com.blinov.logistics.car.model.CarEntity;
import com.blinov.logistics.driver.dto.DriverDto;
import com.blinov.logistics.driver.dto.DrivingLicenseDto;
import com.blinov.logistics.driver.model.Category;
import com.blinov.logistics.driver.model.DriverEntity;
import com.blinov.logistics.driver.model.DrivingLicenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class DriverEntityToDriverDtoMapper {
    @Autowired
    private CarEntityToCarDtoMapper carEntityToCarDtoMapper;

    @Autowired
    private DrivingLicenseEntityToDrivingLicenseDtoMapper drivingLicenseEntityToDrivingLicenseDtoMapper;
    @Mapping(target = "drivingLicense", qualifiedByName = "mapDrivingLicense")
    @Mapping(target = "cars",source = "carEntities", qualifiedByName = "mapCars")
    public abstract DriverDto mapTo(DriverEntity driverEntity);


    @Named("mapCars")
    protected List<CarDto> mapCars(List<CarEntity> cars){
        return cars.stream()
                .map(carEntityToCarDtoMapper::mapTo)
                .collect(Collectors.toList());
    }
    @Named("mapDrivingLicense")
    protected DrivingLicenseDto mapLicense(DrivingLicenseEntity drivingLicense){
        return drivingLicenseEntityToDrivingLicenseDtoMapper.mapTo(drivingLicense);
    }

}
