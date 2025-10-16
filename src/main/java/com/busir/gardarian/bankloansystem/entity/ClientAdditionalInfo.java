package com.busir.gardarian.bankloansystem.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ClientAdditionalInfo {
    private Long id;
    private Long userId;
    private Integer age;
    private BigDecimal monthlyIncome;
    private BigDecimal otherIncome;
    private BigDecimal totalIncome;
}
