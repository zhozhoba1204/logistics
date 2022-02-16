package com.blinov.logistics.car.model;

import com.blinov.logistics.car.dao.CarTypeConverter;
import com.blinov.logistics.driver.model.DriverEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Convert;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car")
public class CarEntity {
    @Id
    @Column(name = "gos_number")
    private String gosNumber;

    /**
     * Тип автомобиля
     */
    @Column(name = "car_type", nullable = false)
    @Convert(converter = CarTypeConverter.class)
    private CarType carType;

    /**
     * Модель автомобиля
     */
    @Column(name = "model", length = 50, nullable = false)
    private String model;

    /**
     * Водитель
     */
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;
}
