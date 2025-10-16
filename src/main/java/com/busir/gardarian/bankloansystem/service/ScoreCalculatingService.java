package com.busir.gardarian.bankloansystem.service;

import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;
import com.busir.gardarian.bankloansystem.entity.CreditPolicies;
import com.busir.gardarian.bankloansystem.service.dto.CreditPolicySetting;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationForm;
import com.busir.gardarian.bankloansystem.service.interfaces.ClientAdditionalInfoRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ScoreCalculatingService {
    private final ClientAdditionalInfoRepositoryImpl clientAdditionalInfoRepository;

    public Integer calculateScoringScore(LoanApplicationForm loanApplicationForm, CreditPolicies creditPolicies) throws JsonProcessingException {
        CreditPolicySetting setting = parseCreditPolicySetting(creditPolicies);

        ClientAdditionalInfo info = clientAdditionalInfoRepository.getClientAdditionalInfoByUserId(loanApplicationForm.getUserId());

        Integer ageScore = calculateAgeScore(info.getAge(), setting);
        Integer income = calculateIncomeScore(info.getTotalIncome(), setting);

        Integer notNormalizedScore = ageScore + income;

        Integer minScore = calculateMinScore(setting);
        Integer maxScore = calculateMaxScore(setting);

        return normalizedScore(minScore, maxScore, notNormalizedScore);
    }

    private CreditPolicySetting parseCreditPolicySetting(CreditPolicies creditPolicy) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(creditPolicy.getScoringRules(), CreditPolicySetting.class);
    }

    private Integer calculateAgeScore(Integer age, CreditPolicySetting  setting) {
        if (age == null || setting == null) {
            return 0;
        }
        Map<String, Integer> weight = setting.getAgeWeight();
        for (Map.Entry<String, Integer> entry : weight.entrySet()) {
            if(isInAgeRange(age, entry.getKey())){
                return entry.getValue();
            }
        }
        return 0;
    }

    private Integer calculateIncomeScore(BigDecimal income, CreditPolicySetting  setting) {
        if (income == null || setting == null) {
            return 0;
        }

        Map<String, Integer> weight = setting.getIncomeWeights();
        for (Map.Entry<String, Integer> entry : weight.entrySet()) {
            if(isIncomeInRange(income, entry.getKey())){
                return entry.getValue();
            }
        }
        return 0;
    }


    private Boolean isIncomeInRange(BigDecimal income, String range) {
        if (range.contains("+")) {
            BigDecimal min = new BigDecimal(range.replace("+", ""));
            return income.compareTo(min) >= 0;
        } else if (range.contains("-")) {
            String[] parts = range.split("-");
            BigDecimal min = new BigDecimal(parts[0]);
            BigDecimal max = new BigDecimal(parts[1]);
            return income.compareTo(min) >= 0 && income.compareTo(max) <= 0;
        }
        return false;
    }

    private Boolean isInAgeRange(int age, String range){
            if (range.contains("+")) {
                int min = Integer.parseInt(range.replace("+", ""));
                return age >= min;
            } else if (range.contains("-")) {
                String[] parts = range.split("-");
                int min = Integer.parseInt(parts[0]);
                int max = Integer.parseInt(parts[1]);
                return age >= min && age <= max;
            }
        return false;
    }

    private Integer normalizedScore(Integer min, Integer max, Integer score) {
        Double dMin = Double.valueOf(min);
        Double dMax = Double.valueOf(max);
        Double dScore = Double.valueOf(score);

        double dNormalizedScore = (dScore - dMin) / (dMax - dMin) * 100;

        return Math.toIntExact(Math.round(dNormalizedScore));
    }

    private Integer calculateMinScore(CreditPolicySetting setting) {

        if(setting == null){
            return 0;
        }

        Map<String, Integer> incomeWeights = setting.getIncomeWeights();
        Map<String, Integer> ageWeights = setting.getAgeWeight();

            Integer minIncomeScore = null;
            Integer minAgeScore = null;

            for (Map.Entry<String, Integer> entry : incomeWeights.entrySet()) {
                if (minIncomeScore == null) {
                    minIncomeScore = entry.getValue();
                }else{
                    minIncomeScore = Math.min(minIncomeScore, entry.getValue());
                }
            }

        for (Map.Entry<String, Integer> entry : ageWeights.entrySet()) {
            if (minAgeScore == null) {
                minAgeScore = entry.getValue();
            }else{
                minAgeScore = Math.min(minAgeScore, entry.getValue());
            }
        }
        if (minIncomeScore == null || minAgeScore == null) {
            return 0;
        }
        return minAgeScore + minIncomeScore;
    }

    private Integer calculateMaxScore(CreditPolicySetting setting) {

        if(setting == null){
            return 0;
        }

        Map<String, Integer> incomeWeights = setting.getIncomeWeights();
        Map<String, Integer> ageWeights = setting.getAgeWeight();

        Integer maxIncomeScore = null;
        Integer maxAgeScore = null;

        for (Map.Entry<String, Integer> entry : incomeWeights.entrySet()) {
            if (maxIncomeScore == null) {
                maxIncomeScore = entry.getValue();
            }else{
                maxIncomeScore = Math.max(maxIncomeScore, entry.getValue());
            }
        }

        for (Map.Entry<String, Integer> entry : ageWeights.entrySet()) {
            if (maxAgeScore == null) {
                maxAgeScore = entry.getValue();
            }else{
                maxAgeScore = Math.max(maxAgeScore, entry.getValue());
            }
        }
        if (maxIncomeScore == null || maxAgeScore == null) {
            return 0;
        }
        return maxAgeScore + maxIncomeScore;
    }
}
