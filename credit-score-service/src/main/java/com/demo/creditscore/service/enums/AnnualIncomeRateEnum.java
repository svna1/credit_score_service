package com.demo.creditscore.service.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AnnualIncomeRateEnum {
    MIN(99),
    LOW(250),
    MEDIUM(400),
    UPPER_MEDIUM(500),
    HIGH(600),
    MAX(699);

    private final int rate;

    public static Integer getAnnualIncomeRate(Integer annualIncome) {
        if (annualIncome < 700) {
            return MIN.rate;
        } else if (annualIncome < 3000) {
            return LOW.rate;
        } else if (annualIncome < 5000) {
            return MEDIUM.rate;
        } else if (annualIncome < 7000) {
            return UPPER_MEDIUM.rate;
        } else if (annualIncome < 10000) {
            return HIGH.rate;
        } else {
            return MAX.rate;
        }
    }

}
