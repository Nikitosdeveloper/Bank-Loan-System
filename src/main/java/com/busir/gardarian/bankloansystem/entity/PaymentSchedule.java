package com.busir.gardarian.bankloansystem.entity;

import com.busir.gardarian.bankloansystem.entity.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSchedule {
    private Long id;
    private Long applicationId;
    private Date paymentDate;

    @Setter(AccessLevel.NONE)
    private BigDecimal paymentAmount;

    private BigDecimal principalAmount;
    private BigDecimal interestAmount;
    private BigDecimal remainingBalance;
    private PaymentStatus paymentStatus;

    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
        this.paymentAmount = this.principalAmount.add(interestAmount);
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
        this.paymentAmount = this.interestAmount.add(remainingBalance);
    }

    public PaymentSchedule(Long id, Long applicationId, Date paymentDate, BigDecimal principalAmount, BigDecimal interestAmount, BigDecimal remainingBalance, PaymentStatus paymentStatus) {
        this.id = id;
        this.applicationId = applicationId;
        this.paymentDate = paymentDate;
        this.principalAmount = principalAmount;
        this.interestAmount = interestAmount;
        this.remainingBalance = remainingBalance;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = this.principalAmount.add(interestAmount);
    }
}
