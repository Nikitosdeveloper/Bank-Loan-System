package com.busir.gardarian.bankloansystem.service.dto;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CreditPolicySetting {

    private AgeSetting ageSetting;
    private IncomeSetting incomeSetting;

    public Map<String, Integer> getAgeWeight(){
        return ageSetting != null ? ageSetting.getWeight() : null;
    }

    public Map<String, Integer> getIncomeWeights() {
        return incomeSetting != null ? incomeSetting.getWeight() : null;
    }

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    private static class AgeSetting{
        private Integer minAge;
        private Integer maxAge;
        private Map<String, Integer> weight;
        private Integer maxPoint;
    }

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    private static class IncomeSetting{
        private Integer minIncome;
        private Integer maxIncome;
        private Map<String, Integer> weight;
        private Integer maxPoint;
    }
}
