package com.blinov.logistics.driver.mapper;

import com.blinov.logistics.driver.dto.DrivingLicenseDto;
import com.blinov.logistics.driver.model.Category;
import com.blinov.logistics.driver.model.DrivingLicenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DrivingLicenseEntityToDrivingLicenseDtoMapper {
    @Mapping(target = "number", source = "number")
    @Mapping(target = "validityPeriod", source = "validityPeriod", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "mapCategories")
    DrivingLicenseDto mapTo(DrivingLicenseEntity drivingLicense);

    @Named("mapCategories")
    default Set<String> mapCategories(Set<Category> categories) {
        return categories.stream()
                .map(Category::name)
                .collect(Collectors.toSet());
    }
}
