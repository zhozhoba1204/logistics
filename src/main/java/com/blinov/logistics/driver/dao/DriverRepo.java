package com.blinov.logistics.driver.dao;

import com.blinov.logistics.driver.dto.DriverDto;
import com.blinov.logistics.driver.model.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface DriverRepo extends JpaRepository<DriverEntity, Integer> {

}
