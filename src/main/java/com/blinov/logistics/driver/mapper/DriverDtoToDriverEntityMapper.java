package com.blinov.logistics.driver.mapper;

import com.blinov.logistics.car.dto.CarDto;
import com.blinov.logistics.car.mapper.CarDtoToCarEntityMapper;
import com.blinov.logistics.car.model.CarEntity;
import com.blinov.logistics.driver.dto.DriverDto;
import com.blinov.logistics.driver.dto.DrivingLicenseDto;
import com.blinov.logistics.driver.model.DriverEntity;
import com.blinov.logistics.driver.model.DrivingLicenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class DriverDtoToDriverEntityMapper {

    @Autowired
    private CarDtoToCarEntityMapper carDtoToCarEntityMapper;

    @Autowired
    private DrivingLicenseDtoToDrivingLicenseEntityMapper drivingLicenseDtoToDrivingLicenseEntityMapper;

    @Mapping(target = "drivingLicense", qualifiedByName = "mapDrivingLicense")
    @Mapping(target = "carEntities", source = "cars", qualifiedByName = "mapCars")
    public abstract DriverEntity mapTo(DriverDto driverDto);

    @Named("mapDrivingLicense")
    protected DrivingLicenseEntity mapLicense(DrivingLicenseDto drivingLicenseDto){
        return drivingLicenseDtoToDrivingLicenseEntityMapper.mapTo(drivingLicenseDto);
    }

    @Named("mapCars")
    protected List<CarEntity> mapCars(List<CarDto> cars) {
        return cars.stream()
                .map(carDtoToCarEntityMapper::mapTo)
                .collect(Collectors.toList());
    }
}
