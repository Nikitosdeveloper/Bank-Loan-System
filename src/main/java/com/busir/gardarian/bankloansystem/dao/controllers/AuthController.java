package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.AuthRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.RegisterRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.AuthResponce;
import com.busir.gardarian.bankloansystem.dao.infrostructure.security.services.JwtService;
import com.busir.gardarian.bankloansystem.dao.infrostructure.security.services.MyUserDetailsService;
import com.busir.gardarian.bankloansystem.service.AccountService;
import com.busir.gardarian.bankloansystem.service.dto.UserProfileDto;
import com.busir.gardarian.bankloansystem.service.exception.IncorrectPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountService accountService;
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
        UserProfileDto userDto = accountService.register(RegisterRequest.toForm(registerRequest));

        String token = generateToken(registerRequest.getEmail());

        return ResponseEntity.ok(new AuthResponce(token));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponce> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            accountService.validateAndGetUser(request.getEmail());

            String token = generateToken(request.getEmail());

            return ResponseEntity.ok(new AuthResponce(token));
        }catch (BadCredentialsException e) {
            throw new IncorrectPasswordException("Invalid email or password");
        }
    }

    private String generateToken(String email) {
        UserDetails user = userDetailsService.loadUserByUsername(email);

        return jwtService.generateToken(user);
    }
}
