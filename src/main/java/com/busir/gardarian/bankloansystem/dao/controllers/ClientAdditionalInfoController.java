package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.ClientAdditionalInfoRequest;
import com.busir.gardarian.bankloansystem.service.AccountService;
import com.busir.gardarian.bankloansystem.service.dto.ClientAdditionalInfoForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/client/additional-info")
public class ClientAdditionalInfoController {
    private final AccountService accountService;

    @PostMapping("add")
    public ResponseEntity<String> addClientAdditionalInfo(@RequestBody @Valid ClientAdditionalInfoRequest request, Authentication authentication) {
        Long userId = accountService.validateAndGetUser(authentication.getName()).getId();

        ClientAdditionalInfoForm form = ClientAdditionalInfoRequest.toForm(request, userId);

        Long id = accountService.addAdditionalInfo(form);

        return ResponseEntity.ok("Successful additional info");
    }
}
