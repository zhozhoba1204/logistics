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
public interface DrivingLicenseDtoToDrivingLicenseEntityMapper {
    @Mapping(target = "number", source = "number")
    @Mapping(target = "validityPeriod", source = "validityPeriod",dateFormat = "dd-MM-yyyy")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "mapCategories")
    DrivingLicenseEntity mapTo(DrivingLicenseDto drivingLicenseDto);

    @Named("mapCategories")
    default Set<Category> mapCategories(Set<String> categories) {
        return categories.stream()
                .map(Category::getCategory)
                .collect(Collectors.toSet());
    }
}
