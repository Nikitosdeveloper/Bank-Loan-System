package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.CreateManagerRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.UserResponce;
import com.busir.gardarian.bankloansystem.service.UsersServices;
import com.busir.gardarian.bankloansystem.service.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class UserController {
    private final UsersServices usersServices;

    @GetMapping("clients/all")
    public ResponseEntity<List<UserResponce>> getClients(){
        List<UserInfo> list = usersServices.getAllClients();

        List<UserResponce> responce = list
                .stream()
                .map(UserResponce::from)
                .toList();

        return ResponseEntity.ok(responce);
    }

    @GetMapping("managers/all")
    public ResponseEntity<List<UserResponce>> getManagers(){
        List<UserInfo> list = usersServices.getAllManagers();

        List<UserResponce> responce = list
                .stream()
                .map(UserResponce::from)
                .toList();

        return ResponseEntity.ok(responce);
    }

    @PatchMapping("client/{id}/deactivate")
    public ResponseEntity<String> deactivateClient(@PathVariable Long id){
        if(usersServices.deactivateUser(id))
            return ResponseEntity.ok("success");
        return ResponseEntity.internalServerError().body("fail");
    }

    @PatchMapping("client/{id}/activate")
    public ResponseEntity<String> activateClient(@PathVariable Long id){
        if(usersServices.activateUser(id))
            return ResponseEntity.ok("success");
        return ResponseEntity.internalServerError().body("fail");
    }

    @PostMapping("manager/create")
    public ResponseEntity<String> createManager(@RequestBody CreateManagerRequest request){
        if(usersServices.createManager(request.createManagerRegistrationForm()))
            return ResponseEntity.ok("success");
        return ResponseEntity.internalServerError().body("fail");
    }
}
