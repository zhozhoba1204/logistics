package com.blinov.logistics.driver.dto;

import com.blinov.logistics.car.dto.CarDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDto {
    private String name;
    private DrivingLicenseDto drivingLicense;
    private List<CarDto> cars;
}
