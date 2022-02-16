package com.blinov.logistics.driver.dao;

import com.blinov.logistics.driver.model.DrivingLicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrivingLicenseRepo extends JpaRepository<DrivingLicenseEntity, Integer> {
    DrivingLicenseEntity findByNumber(int number);
}
