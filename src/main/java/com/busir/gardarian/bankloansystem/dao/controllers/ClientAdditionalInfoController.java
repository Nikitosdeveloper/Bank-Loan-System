package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.ClientAdditionalInfoRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.ClientAdditionalInfoResponce;
import com.busir.gardarian.bankloansystem.service.AccountService;
import com.busir.gardarian.bankloansystem.service.UsersServices;
import com.busir.gardarian.bankloansystem.service.dto.ClientAdditionalInfoForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ClientAdditionalInfoController {
    private final AccountService accountService;
    private final UsersServices usersServices;

    @PostMapping("/client/additional-info/add")
    public ResponseEntity<String> addClientAdditionalInfo(@RequestBody @Valid ClientAdditionalInfoRequest request, Authentication authentication) {
        Long userId = accountService.validateAndGetUser(authentication.getName()).getId();

        ClientAdditionalInfoForm form = ClientAdditionalInfoRequest.toForm(request, userId);

        Long id = accountService.addAdditionalInfo(form);

        return ResponseEntity.ok("Successful additional info");
    }

    @GetMapping("admin/client/additional-info/{id}")
    public ResponseEntity<ClientAdditionalInfoResponce> getClientAdditionalInfo(@PathVariable Long id) {
        ClientAdditionalInfoResponce responce = ClientAdditionalInfoResponce.from(usersServices.getInfoAboutClient(id));

        return ResponseEntity.ok(responce);
    }
}
