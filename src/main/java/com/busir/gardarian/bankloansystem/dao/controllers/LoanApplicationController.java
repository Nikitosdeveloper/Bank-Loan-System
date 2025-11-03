package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.LoanApplicationStatusResponce;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.LoanApplicationSubmitRequest;
import com.busir.gardarian.bankloansystem.dao.repositoriy.mapping.LoanApplicationMapper;
import com.busir.gardarian.bankloansystem.service.AccountService;
import com.busir.gardarian.bankloansystem.service.LoanApplicationService;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationForm;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/loan-application")
@RequiredArgsConstructor
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;
    private final AccountService accountService;

    @PostMapping("submit")
    public ResponseEntity<Long> submit(@RequestBody @Valid LoanApplicationSubmitRequest loanApplicationSubmitRequest,
                                       Authentication authentication) {
        Long userId = accountService.validateAndGetUser(authentication.getName()).getId();

        LoanApplicationForm form = LoanApplicationSubmitRequest.createLoanApplicationForm(loanApplicationSubmitRequest, userId);

        Long id = loanApplicationService.submitLoanApplication(form);

        return ResponseEntity.ok(id);
    }

    @GetMapping("status/{id}")
    public ResponseEntity<LoanApplicationStatusResponce> getStatus(@PathVariable Long id) {
        LoanApplicationStatusResponce responce = LoanApplicationStatusResponce
                .from(loanApplicationService.getLoanApplicationStatus(id));

        return ResponseEntity.ok(responce);
    }

    @GetMapping("history")
    public ResponseEntity<List<LoanApplicationStatusResponce>> getHistory(Authentication authentication) {
        Long userId = accountService.validateAndGetUser(authentication.getName()).getId();

        List<LoanApplicationResult> statuses = loanApplicationService.getHistoryLoanApplication(userId);

        List<LoanApplicationStatusResponce> responses = statuses.stream()
                .map(LoanApplicationStatusResponce::from)
                .toList();

        return ResponseEntity.ok(responses);
    }
}
