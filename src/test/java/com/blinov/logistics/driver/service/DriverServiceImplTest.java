package com.blinov.logistics.driver.service;

import com.blinov.logistics.car.dao.CarRepo;
import com.blinov.logistics.car.dto.CarDto;
import com.blinov.logistics.car.model.CarEntity;
import com.blinov.logistics.car.model.CarType;
import com.blinov.logistics.driver.dao.DriverRepo;
import com.blinov.logistics.driver.dao.DrivingLicenseRepo;
import com.blinov.logistics.driver.dto.DriverDto;
import com.blinov.logistics.driver.dto.DrivingLicenseDto;
import com.blinov.logistics.driver.mapper.DriverDtoToDriverEntityMapper;
import com.blinov.logistics.driver.mapper.DriverEntityToDriverDtoMapper;
import com.blinov.logistics.driver.mapper.DrivingLicenseDtoToDrivingLicenseEntityMapper;
import com.blinov.logistics.driver.model.Category;
import com.blinov.logistics.driver.model.DriverEntity;
import com.blinov.logistics.driver.model.DrivingLicenseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

    @InjectMocks
    private DriverServiceImpl driverService;

    @Mock
    private DriverRepo driverRepo;
    @Mock
    private CarRepo carRepo;
    @Mock
    private DrivingLicenseRepo drivingLicenseRepo;
    @Mock
    private DriverDtoToDriverEntityMapper driverDtoToDriverEntityMapper;
    @Mock
    private DriverEntityToDriverDtoMapper driverEntityToDriverDtoMapper;
    @Mock
    private DrivingLicenseDtoToDrivingLicenseEntityMapper drivingLicenseDtoToDrivingLicenseEntityMapper;


    public DriverEntity getDriverEntity(){
        return DriverEntity.builder()
                .id(1)
                .name("Anton")
                .drivingLicense(DrivingLicenseEntity.builder()
                        .id(1)
                        .number(121)
                        .validityPeriod(LocalDate.parse("12-12-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                        .categories(Set.of(Category.C,Category.B))
                        .build())
                .carEntities(List.of(CarEntity.builder()
                        .gosNumber("A111")
                        .carType(CarType.C)
                        .model("GAZ")
                        .build()))
                .build();
    }
    public DriverDto getDriverDto(){
        return DriverDto.builder()
                .name("Anton")
                .drivingLicense(DrivingLicenseDto.builder()
                        .number(121)
                        .validityPeriod("12-12-2022")
                        .categories(Set.of("C","B"))
                        .build())
                .cars(List.of(CarDto.builder()
                        .gosNumber("A111")
                        .carType("Грузовой")
                        .model("GAZ")
                        .build()))
                .build();
    }

    @Test
    public void getDriver() {
        Mockito.when(driverRepo.getById(Mockito.anyInt())).thenReturn(getDriverEntity());
        Mockito.when(driverEntityToDriverDtoMapper.mapTo(Mockito.any(DriverEntity.class)))
                .thenReturn(getDriverDto());
        DriverDto driverDto = driverService.getDriver(1);
        Mockito.verify(driverRepo, Mockito.times(1)).getById(1);
        assertNotNull(driverDto);
        assertEquals("Anton",driverDto.getName());
        assertEquals(121,driverDto.getDrivingLicense().getNumber());
        assertEquals("12-12-2022",driverDto.getDrivingLicense().getValidityPeriod());
        assertEquals(2,driverDto.getDrivingLicense().getCategories().size());
        assertEquals(1,driverDto.getCars().size());
    }

}