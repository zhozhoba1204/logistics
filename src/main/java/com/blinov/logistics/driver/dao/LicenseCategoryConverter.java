package com.blinov.logistics.driver.dao;

import com.blinov.logistics.driver.model.Category;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class LicenseCategoryConverter implements AttributeConverter<Set<Category>, String> {
    @Override
    public String convertToDatabaseColumn(Set<Category> category) {
        return category.stream()
                .map(Category::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<Category> convertToEntityAttribute(String categories) {
        return Arrays.stream(categories.split(","))
                .map(Category::getCategory)
                .collect(Collectors.toSet());
    }
}
