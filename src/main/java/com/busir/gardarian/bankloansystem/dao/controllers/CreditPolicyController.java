package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.CreditPolicyRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.CreditPolicyResponce;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.CreditPolicyUpdateRequest;
import com.busir.gardarian.bankloansystem.entity.CreditPolicies;
import com.busir.gardarian.bankloansystem.service.AccountService;
import com.busir.gardarian.bankloansystem.service.CreditPolicyService;
import com.busir.gardarian.bankloansystem.service.dto.CreditPolicyForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin/credit-policy")
public class CreditPolicyController {
    private final CreditPolicyService creditPolicyService;
    private final AccountService accountService;

    @GetMapping("all")
    public ResponseEntity<List<CreditPolicyResponce>> getAllCreditPolicy() {
        List<CreditPolicies> list = creditPolicyService.getAllCreditPolicies();

        List<CreditPolicyResponce> response = list
                .stream()
                .map(CreditPolicyResponce::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<String> activateCreditPolicy(@PathVariable Long id) {
        if(creditPolicyService.setActiveCreditPolicy(id))
            return ResponseEntity.ok("Activated");
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping("create")
    public ResponseEntity<String> createCreditPolicy(@RequestBody CreditPolicyRequest request,
                                                     Authentication authentication) {

        Long userId = accountService.validateAndGetUser(authentication.getName()).getId();

        CreditPolicyForm creditPolicyForm = request.create(userId);

        if (creditPolicyService.createCreditPolicy(creditPolicyForm))
            return ResponseEntity.ok("Success");
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("update")
    public ResponseEntity<String> updateCreditPolicy(@RequestBody CreditPolicyUpdateRequest request,
                                                     Authentication authentication) {
        Long userId = accountService.validateAndGetUser(authentication.getName()).getId();

        CreditPolicyForm creditPolicyForm = request.create(userId);

        if(creditPolicyService.updateCreditPolicy(creditPolicyForm, request.id()))
            return ResponseEntity.ok("Success");
        return ResponseEntity.internalServerError().build();
    }

}
