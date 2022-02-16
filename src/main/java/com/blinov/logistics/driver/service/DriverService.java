package com.blinov.logistics.driver.service;

import com.blinov.logistics.driver.dto.DriverDto;
import java.util.List;

public interface DriverService {
    void createDriver(DriverDto driverDto);
    DriverDto getDriver(Integer id);
    void updateDriver(Integer id, DriverDto driverDto);
    void deleteDriver(Integer id);
    void addCarToDriver(Integer id, String gosNumber);
    void removeCarFromDriver(Integer id, String gosNumber);
    List<DriverDto> getAllDrivers();
}
