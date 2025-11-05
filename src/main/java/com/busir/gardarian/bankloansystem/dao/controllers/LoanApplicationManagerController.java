package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.DecisionRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.LoanApplicationInfo;
import com.busir.gardarian.bankloansystem.entity.LoanApplication;
import com.busir.gardarian.bankloansystem.service.AccountService;
import com.busir.gardarian.bankloansystem.service.LoanApplicationManagerService;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationDecision;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/manager/loan-application")
public class LoanApplicationManagerController {
    private final LoanApplicationManagerService loanApplicationManagerService;
    private final AccountService accountService;

    @GetMapping("free")
    public ResponseEntity<List<LoanApplicationInfo>> getAllFreeLoanApplications() {
        List<LoanApplication> list = loanApplicationManagerService.getFreeLoanApplications();

        List<LoanApplicationInfo> result = list.stream()
                .map(LoanApplicationInfo::from)
                .toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping("decision")
    public ResponseEntity<String> decision(@RequestBody @Valid DecisionRequest request,
                                           Authentication authentication) {
        Long managerId = accountService.validateAndGetUser(authentication.getName()).getId();

        LoanApplicationDecision decision = request.createLoanApplicationDecision(managerId);

        loanApplicationManagerService.decision(decision);

        return ResponseEntity.ok("Success");
    }
}
