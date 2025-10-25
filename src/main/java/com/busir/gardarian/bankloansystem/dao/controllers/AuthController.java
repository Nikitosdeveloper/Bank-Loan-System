package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.AuthRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.RegisterRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.AuthResponce;
import com.busir.gardarian.bankloansystem.dao.infrostructure.security.AuthService;
import com.busir.gardarian.bankloansystem.dao.infrostructure.security.JwtService;
import com.busir.gardarian.bankloansystem.dao.infrostructure.security.MyUserDetailsService;
import com.busir.gardarian.bankloansystem.service.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final MyUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/all")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("all");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/magnus")
    public ResponseEntity<String> test1(){
        return ResponseEntity.ok("auth");
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponce> register(@RequestBody RegisterRequest registerRequest) {
        UserProfileDto userDto = authService.register(RegisterRequest.toForm(registerRequest));
        UserDetails user = userDetailsService.loadUserByUsername(userDto.getEmail());
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponce(token));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponce> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponce(token));
    }
}
