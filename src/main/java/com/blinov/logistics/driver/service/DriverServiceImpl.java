package com.blinov.logistics.driver.service;

import com.blinov.logistics.car.dao.CarRepo;
import com.blinov.logistics.car.model.CarEntity;
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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервисный слой для работы с Driver
 */
@Transactional
@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
    @Autowired
    private final DriverRepo driverRepo;
    @Autowired
    private final CarRepo carRepo;
    @Autowired
    private final DriverDtoToDriverEntityMapper driverDtoToDriverEntityMapper;
    @Autowired
    private final DriverEntityToDriverDtoMapper driverEntityToDriverDtoMapper;
    @Autowired
    private final DrivingLicenseDtoToDrivingLicenseEntityMapper drivingLicenseDtoToDrivingLicenseEntityMapper;
    @Autowired
    private final DrivingLicenseRepo drivingLicenseRepo;

    /**
     * Сохранить Driver
     * @param driverDto
     */
    @Override
    public void createDriver(DriverDto driverDto) {
        DriverEntity driverEntity = driverDtoToDriverEntityMapper.mapTo(driverDto);
        DrivingLicenseEntity drivingLicenseEntity = drivingLicenseDtoToDrivingLicenseEntityMapper.mapTo(driverDto.getDrivingLicense());
        DrivingLicenseEntity drivingLicense = drivingLicenseRepo.save(drivingLicenseEntity);
        driverEntity.setDrivingLicense(drivingLicense);
        DriverEntity driver = driverRepo.save(driverEntity);
        List<CarEntity> carEntities = driverEntity.getCarEntities();
        driverEntity.setDrivingLicense(drivingLicense);
        carEntities.forEach(x -> x.setDriver(driver));
        carEntities.forEach(carRepo::save);
    }
    /**
     * Получить Driver
     * @param id
     */
    @Override
    public DriverDto getDriver(Integer id) {
        return driverEntityToDriverDtoMapper.mapTo(driverRepo.getById(id));
    }

    /**
     * Изменить Driver
     * @param driverId, updateDriverDto
     */
    @Override
    public void updateDriver(Integer driverId, DriverDto updateDriverDto) {
        DriverEntity driverEntity = driverRepo.getById(driverId);
        DriverDto driverDto = driverEntityToDriverDtoMapper.mapTo(driverEntity);
        if (updateDriverDto.getName() != null) {
            driverEntity.setName(updateDriverDto.getName());
        }
        if (updateDriverDto.getDrivingLicense() != null) {
            DrivingLicenseDto drivingLicenseDto = driverDto.getDrivingLicense();
            DrivingLicenseEntity drivingLicenseEntity = drivingLicenseRepo.findByNumber(drivingLicenseDto.getNumber());
            drivingLicenseEntity.setNumber(updateDriverDto.getDrivingLicense().getNumber());
            drivingLicenseEntity.setCategories(updateDriverDto.getDrivingLicense().getCategories().stream()
                    .map(Category::getCategory)
                    .collect(Collectors.toSet()));
            drivingLicenseEntity.setValidityPeriod(LocalDate.parse(updateDriverDto.getDrivingLicense().getValidityPeriod(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            DrivingLicenseEntity drivingLicense = drivingLicenseRepo.save(drivingLicenseEntity);
            driverEntity.setDrivingLicense(drivingLicense);
        }
        driverRepo.save(driverEntity);
    }

    /**
     * Удалить Driver
     * @param id
     */
    @Override
    public void deleteDriver(Integer id) {
        driverRepo.deleteById(id);
    }

    /**
     * Привязать авто к водителю
     * @param id, updateDriverDto
     */
    @Override
    public void addCarToDriver(Integer id, String gosNumber) {
        DriverEntity driverEntity = driverRepo.getById(id);
        List<CarEntity> cars = driverEntity.getCarEntities();
        CarEntity carEntity = carRepo.getById(gosNumber);
        boolean b = cars.size() > 2;
        Set<String> category = driverEntity.getDrivingLicense().getCategories().stream()
                .map(Category::name)
                .collect(Collectors.toSet());
        String carT = carEntity.getCarType().name();
        boolean b1 = category.contains(carT);
        boolean b2 = (driverEntity.getDrivingLicense().getValidityPeriod().isAfter(LocalDate.now()));
        if (b) {
            throw new RuntimeException("За водителем не может быть закреплено более 3 автомобилей!");
        }
        if (!b1) {
            throw new RuntimeException("У водителя не открыта категория для управления данным видом транспорта!");
        }
        if (!b2) {
            throw new RuntimeException("Закончился срок действия водительского удостоверения!");
        }
        driverRepo.save(driverEntity);
        carEntity.setDriver(driverEntity);
        carRepo.save(carEntity);
    }

    /**
     * Отвязать авто от водителя
     * @param id, gosNumber
     */
    @Override
    public void removeCarFromDriver(Integer id, String gosNumber) {
        DriverEntity driver = driverRepo.getById(id);
        CarEntity car = carRepo.getById(gosNumber);
        List<CarEntity> cars = driver.getCarEntities();
        if (CollectionUtils.isEmpty(cars)) {
            throw new RuntimeException("За водителем не закреплены автомобили!!!");
        }
        cars.remove(car);
        car.setDriver(null);
        carRepo.save(car);
        driverRepo.save(driver);
    }

    /**
     * Показать всех водителей
     * @return
     */
    @Override
    public List<DriverDto> getAllDrivers() {
        return driverRepo.findAll().stream()
                .map(driverEntityToDriverDtoMapper::mapTo)
                .collect(Collectors.toList());
    }
}
