package com.busir.gardarian.bankloansystem.dao.repositoriy.models;

import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationFinalDecision;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Enumerated(EnumType.STRING)
    private LoanApplicationStatus status;

    @Column(name = "scoring_score")
    private Integer scoringScore;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @ColumnDefault("'pending'")
    @Column(name = "final_decision", length = 20)
    @Enumerated(EnumType.STRING)
    private LoanApplicationFinalDecision finalDecision;

    @Column(name = "\"decision_comment \"", length = Integer.MAX_VALUE)
    private String decisionComment;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "application")
    private Set<DocumentDB> documents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "application")
    private Set<PaymentScheduleDB> paymentSchedules = new LinkedHashSet<>();

}