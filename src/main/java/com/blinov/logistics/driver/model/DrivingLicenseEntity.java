package com.blinov.logistics.driver.model;

import com.blinov.logistics.driver.dao.LicenseCategoryConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Convert;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Set;

/**
 * Водительское удостоверение
 */
@Entity
@Table(name = "driving_license")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DrivingLicenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Номер удостоверения
     */
    private int number;

    /**
     * Срок действия удостоверения
     */
    private LocalDate validityPeriod;

    /**
     * Список открытых категорий
     */
    @Convert(converter = LicenseCategoryConverter.class)
    private Set<Category> categories;

    @OneToOne(mappedBy = "drivingLicense")
    private DriverEntity driver;
}
