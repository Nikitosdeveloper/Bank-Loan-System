package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.LoanApplicationStatusResponce;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.LoanApplicationSubmitRequest;
import com.busir.gardarian.bankloansystem.dao.repositoriy.mapping.LoanApplicationMapper;
import com.busir.gardarian.bankloansystem.service.AccountService;
import com.busir.gardarian.bankloansystem.service.LoanApplicationService;
import com.busir.gardarian.bankloansystem.service.dto.LoanApplicationForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

        loanApplicationService.submitLoanApplication(form);

        return ResponseEntity.ok(userId);
    }

    @GetMapping("status/{id}")
    public ResponseEntity<LoanApplicationStatusResponce> getStatus(@PathVariable Long id) {
        LoanApplicationStatusResponce responce = LoanApplicationStatusResponce
                .from(loanApplicationService.getLoanApplicationStatus(id));

        return ResponseEntity.ok(responce);
    }
}
