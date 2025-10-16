package com.busir.gardarian.bankloansystem.service.stub;

import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import com.busir.gardarian.bankloansystem.entity.enums.LoanApplicationStatus;
import com.busir.gardarian.bankloansystem.service.interfaces.LoanApplicationRepositoryImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("dev")
public class StubLoanApplicationRepository implements LoanApplicationRepositoryImpl {

    private final ConcurrentHashMap<Long, LoanApplication> stubData = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public StubLoanApplicationRepository() {
        initializeStubData();
    }

    private void initializeStubData() {
        LoanApplication app1 = new LoanApplication();
        app1.setId(1L);
        app1.setUserId(101L);
        app1.setPurposeId(1L);
        app1.setLoanAmount(new BigDecimal("50000.00"));
        app1.setLoanTerm(24);
        app1.setScoring_score(85);
        app1.setInterestRate(new BigDecimal("12.5"));
        app1.setStatus(LoanApplicationStatus.APPROVED);
        app1.setCreatedAt(Timestamp.from(Instant.now().minusSeconds(86400)));
        app1.setUpdatedAt(Timestamp.from(Instant.now()));
        stubData.put(1L, app1);

        LoanApplication app2 = new LoanApplication();
        app2.setId(2L);
        app2.setUserId(102L);
        app2.setPurposeId(2L);
        app2.setLoanAmount(new BigDecimal("150000.00"));
        app2.setLoanTerm(60);
        app2.setScoring_score(45);
        app2.setInterestRate(new BigDecimal("18.5"));
        app2.setStatus(LoanApplicationStatus.REJECTED);
        app2.setCreatedAt(Timestamp.from(Instant.now().minusSeconds(43200)));
        app2.setUpdatedAt(Timestamp.from(Instant.now()));
        stubData.put(2L, app2);

        LoanApplication app3 = new LoanApplication();
        app3.setId(3L);
        app3.setUserId(103L);
        app3.setPurposeId(1L);
        app3.setLoanAmount(new BigDecimal("75000.00"));
        app3.setLoanTerm(36);
        app3.setScoring_score(72);
        app3.setInterestRate(new BigDecimal("15.0"));
        app3.setStatus(LoanApplicationStatus.UNDER_REVIEW);
        app3.setManagerId(201L);
        app3.setCreatedAt(Timestamp.from(Instant.now().minusSeconds(7200)));
        app3.setUpdatedAt(Timestamp.from(Instant.now()));
        stubData.put(3L, app3);

        LoanApplication app4 = new LoanApplication();
        app4.setId(4L);
        app4.setUserId(104L);
        app4.setPurposeId(3L);
        app4.setLoanAmount(new BigDecimal("25000.00"));
        app4.setLoanTerm(12);
        app4.setScoring_score(null);
        app4.setInterestRate(null);
        app4.setStatus(LoanApplicationStatus.PENDING);
        app4.setCreatedAt(Timestamp.from(Instant.now().minusSeconds(1800)));
        app4.setUpdatedAt(Timestamp.from(Instant.now()));
        stubData.put(4L, app4);

        idCounter.set(5L);
    }

    @Override
    public List<LoanApplication> getAll() {
        return List.copyOf(stubData.values());
    }

    @Override
    public List<LoanApplication> getById(Long id) {
        LoanApplication app = stubData.get(id);
        return app != null ? List.of(app) : List.of();
    }

    @Override
    public List<LoanApplication> getByManagerId(Long managerId) {
        return stubData.values().stream()
                .filter(app -> managerId.equals(app.getManagerId()))
                .filter(app -> app.getStatus() != LoanApplicationStatus.REJECTED)
                .toList();
    }

    @Override
    public List<LoanApplication> getFreeLoanApplications() {
        return stubData.values().stream()
                .filter(app -> app.getManagerId() == null)
                .filter(app -> app.getStatus() == LoanApplicationStatus.PENDING ||
                        app.getStatus() == LoanApplicationStatus.UNDER_REVIEW)
                .toList();
    }

    @Override
    public LoanApplication getByUserId(Long userId) {
        return stubData.values().stream()
                .filter(app -> userId.equals(app.getUserId()))
                .filter(app -> app.getStatus() != LoanApplicationStatus.REJECTED)
                .max((a1, a2) -> a2.getCreatedAt().compareTo(a1.getCreatedAt()))
                .orElse(null);
    }

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        if (loanApplication.getId() == null) {
            Long newId = idCounter.getAndIncrement();
            loanApplication.setId(newId);
            loanApplication.setCreatedAt(Timestamp.from(Instant.now()));
        }
        loanApplication.setUpdatedAt(Timestamp.from(Instant.now()));
        stubData.put(loanApplication.getId(), loanApplication);
        return loanApplication;
    }

    @Override
    public Boolean delete(Long id) {
        LoanApplication removed = stubData.remove(id);
        return removed != null;
    }

    public void clearData() {
        stubData.clear();
        initializeStubData();
    }

    public int getTotalApplications() {
        return stubData.size();
    }

    public List<LoanApplication> getApplicationsByStatus(LoanApplicationStatus status) {
        return stubData.values().stream()
                .filter(app -> status.equals(app.getStatus()))
                .toList();
    }
}