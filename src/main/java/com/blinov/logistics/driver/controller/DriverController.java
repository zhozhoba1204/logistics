package com.blinov.logistics.driver.controller;

import com.blinov.logistics.driver.dto.DriverDto;
import com.blinov.logistics.driver.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер для работы с Driver
 */
@RestController
@AllArgsConstructor
public class DriverController {
    @Autowired
    private final DriverService driverService;
    /**
     * Добавить driver
     * @param driverDto
     * @return
     */
    @PostMapping("/createDriver")
    public Map<String, String> createDriver(@RequestBody DriverDto driverDto) {
        driverService.createDriver(driverDto);
        Map<String, String> result = new HashMap<>();
        result.put("result","success");
        return result;
    }
    /**
     * Получить driver
     * @param id
     * @return
     */
    @GetMapping("/getDriver/{id}")
    public DriverDto getById(@PathVariable(name = "id") Integer id) {
        return driverService.getDriver(id);
    }
    /**
     * Изменить driver
     * @param driverDto
     * @return
     */
    @PostMapping("/updateDriver/{id}")
    public Map<String, String> updateDriver(@PathVariable(name = "id") Integer id, @RequestBody DriverDto driverDto) {
        driverService.updateDriver(id, driverDto);
        Map<String, String> result = new HashMap<>();
        result.put("result","success");
        return result;
    }
    /**
     * Удалить driver
     * @param id
     * @return
     */
    @GetMapping("deleteDriver/{id}")
    public Map<String, String> deleteDriver(@PathVariable(name = "id") Integer id) {
        driverService.deleteDriver(id);
        Map<String, String> result = new HashMap<>();
        result.put("result","success");
        return result;
    }
    /**
     * Привязать Car к Driver
     * @param id, gosNumber
     * @return
     */
    @PostMapping("/addCarToDriver/{id}/{gosNumber}")
    public Map<String, String> addCar(@PathVariable(name = "id") Integer id,
                       @PathVariable(name = "gosNumber") String gosNumber) {
        driverService.addCarToDriver(id, gosNumber);
        Map<String, String> result = new HashMap<>();
        result.put("result","success");
        return result;
    }
    /**
     * Отвязать Car от Driver
     * @param id, gosNumber
     * @return
     */
    @GetMapping("removeCarFromDriver/{id}/{gosNumber}")
    public Map<String, String> removeCar(@PathVariable(name = "id") Integer id,
                                         @PathVariable(name = "gosNumber") String gosNumber) {
        driverService.removeCarFromDriver(id, gosNumber);
        Map<String, String> result = new HashMap<>();
        result.put("result","success");
        return result;
    }
    /**
     * Вывести всех водителей
     * @param
     * @return
     */
    @GetMapping("/all")
    public List<DriverDto> getAllDrivers() {
        return driverService.getAllDrivers();
    }

}
