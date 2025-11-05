package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;

import java.math.BigDecimal;

public record ClientAdditionalInfoResponce(
         Long userId,
         Integer age,
         BigDecimal monthlyIncome,
         BigDecimal otherIncome,
         BigDecimal totalIncome
) {
    public static ClientAdditionalInfoResponce from(ClientAdditionalInfo clientAdditionalInfo) {
        return new ClientAdditionalInfoResponce(
                clientAdditionalInfo.getUserId(),
                clientAdditionalInfo.getAge(),
                clientAdditionalInfo.getMonthlyIncome(),
                clientAdditionalInfo.getOtherIncome(),
                clientAdditionalInfo.getTotalIncome()
        );
    }
}
