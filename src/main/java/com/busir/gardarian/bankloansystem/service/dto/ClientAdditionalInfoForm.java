package com.busir.gardarian.bankloansystem.service.dto;

import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientAdditionalInfoForm {
    private Long userId;
    private Integer age;
    private BigDecimal monthlyIncome;
    private BigDecimal otherIncome;

    public static ClientAdditionalInfo toEntity(ClientAdditionalInfoForm form) {
        ClientAdditionalInfo info = new ClientAdditionalInfo();
        info.setId(null);
        info.setUserId(form.getUserId());
        info.setAge(form.getAge());
        info.setMonthlyIncome(form.getMonthlyIncome());
        info.setOtherIncome(form.getOtherIncome());
        info.setTotalIncome(form.getMonthlyIncome().add(form.getOtherIncome()));

        return info;
    }
}
