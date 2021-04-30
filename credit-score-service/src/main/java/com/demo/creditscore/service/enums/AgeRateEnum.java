package com.demo.creditscore.service.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AgeRateEnum {
    HIGH(300),
    MEDIUM(200),
    LOW(100);

    private final int rate;

    public static int getAgeRate(int age) {
        if (age > 17 && age <= 35) {
            return HIGH.rate;
        } else if (age > 35 && age <= 50) {
            return MEDIUM.rate;
        } else
            return LOW.rate;
    }
}
