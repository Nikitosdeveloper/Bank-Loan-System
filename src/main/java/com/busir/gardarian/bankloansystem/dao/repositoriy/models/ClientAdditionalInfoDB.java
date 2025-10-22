package com.busir.gardarian.bankloansystem.dao.repositoriy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "client_additional_info", schema = "public")
public class ClientAdditionalInfoDB {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_d", nullable = false)
    private UserDB userD;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "monthly_income", nullable = false)
    private BigDecimal monthlyIncome;

    @ColumnDefault("0")
    @Column(name = "other_income")
    private BigDecimal otherIncome;

    @Column(name = "total_income", nullable = false)
    private BigDecimal totalIncome;

}