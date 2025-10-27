package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.LoanPurposeResponce;
import com.busir.gardarian.bankloansystem.entity.LoanPurpose;
import com.busir.gardarian.bankloansystem.service.LoanPurposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class LoanApplicationPurposeController {
    private final LoanPurposeService loanPurposeService;

    @GetMapping("public/loan-application-purpose")
    public ResponseEntity<List<LoanPurposeResponce>> getAllLoanPurpose(){
        List<LoanPurpose> loanPurposes = loanPurposeService.getAll();

        List<LoanPurposeResponce> responses = loanPurposes.stream()
                .map(LoanPurposeResponce::from)
                .toList();

        return ResponseEntity.ok(responses);
    }
}
