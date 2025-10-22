package com.busir.gardarian.bankloansystem.dao.repositoriy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "loan_applications", schema = "public")
public class LoanApplicationDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_applications_id_gen")
    @SequenceGenerator(name = "loan_applications_id_gen", sequenceName = "loan_applications_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private UserDB user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private UserDB manager;

    @Column(name = "loan_amount", nullable = false)
    private BigDecimal loanAmount;

    @Column(name = "loan_term", nullable = false)
    private Integer loanTerm;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "purpose_id")
    private LoanPurposeDB purpose;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ColumnDefault("'pending'")
    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "scoring_score")
    private Integer scoringScore;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @ColumnDefault("'pending'")
    @Column(name = "final_decision", length = 20)
    private String finalDecision;

    @Column(name = "\"decision_comment \"", length = Integer.MAX_VALUE)
    private String decisionComment;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private LocalTime createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @OneToMany(mappedBy = "application")
    private Set<DocumentDB> documents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "application")
    private Set<PaymentScheduleDB> paymentSchedules = new LinkedHashSet<>();

}