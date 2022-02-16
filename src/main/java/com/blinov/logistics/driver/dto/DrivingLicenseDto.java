package com.blinov.logistics.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrivingLicenseDto {
    private String validityPeriod;
    private int number;
    private Set<String> categories;
}
