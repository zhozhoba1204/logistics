package com.blinov.logistics.driver.model;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum Category {
    B,
    C,
    D;

    public static Category getCategory(String description){
        return Arrays.stream(values())
                .filter(x -> x.name().equals(description))
                .findFirst().orElseThrow();
    }
}
