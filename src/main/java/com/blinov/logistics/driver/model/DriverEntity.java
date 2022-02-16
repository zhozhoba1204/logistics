package com.blinov.logistics.driver.model;

import com.blinov.logistics.car.model.CarEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Водитель
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "driver")
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Водительское удостоверение
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driving_license_id")
    private DrivingLicenseEntity drivingLicense;

    /**
     * Имя
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * Список автомобилей
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    private List<CarEntity> carEntities;

}
