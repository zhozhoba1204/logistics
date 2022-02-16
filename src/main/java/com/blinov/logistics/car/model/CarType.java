package com.blinov.logistics.car.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CarType {
    B("Легковой"),
    C("Грузовой"),
    D("Автобус");

    private String description;
    public static CarType getCarType(String description){
        return Arrays.stream(values())
                .filter(x -> x.getDescription().equals(description))
                .findFirst().orElseThrow();
    }
}
