package com.busir.gardarian.bankloansystem.dao.controllers.dto;

import com.busir.gardarian.bankloansystem.service.dto.ClientAdditionalInfoForm;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientAdditionalInfoRequest {
    @Min(18)
    private Integer age;
    @Positive
    private BigDecimal monthlyIncome;
    @Positive
    private BigDecimal otherIncome;

    public static ClientAdditionalInfoForm toForm(ClientAdditionalInfoRequest request, Long userId) {
        ClientAdditionalInfoForm form = new ClientAdditionalInfoForm();
        form.setUserId(userId);
        form.setAge(request.getAge());
        form.setMonthlyIncome(request.getMonthlyIncome());
        form.setOtherIncome(request.getOtherIncome());
        return form;
    }
}
