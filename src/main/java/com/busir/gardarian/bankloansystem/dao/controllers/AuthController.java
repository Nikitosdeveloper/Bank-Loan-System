package com.busir.gardarian.bankloansystem.dao.controllers;

import com.busir.gardarian.bankloansystem.dao.controllers.dto.AuthRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.RefreshRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.RegisterRequest;
import com.busir.gardarian.bankloansystem.dao.controllers.dto.AuthResponce;
import com.busir.gardarian.bankloansystem.dao.infrostructure.security.dto.JwtAuthenticationDto;
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

    @PostMapping("register")
    public ResponseEntity<AuthResponce> register(@RequestBody RegisterRequest registerRequest) {
        UserProfileDto userDto = accountService.register(RegisterRequest.toForm(registerRequest));

        JwtAuthenticationDto dto = generateToken(registerRequest.getEmail());

        return ResponseEntity.ok(AuthResponce.from(dto));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponce> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            accountService.validateAndGetUser(request.getEmail());

            JwtAuthenticationDto dto = generateToken(request.getEmail());

            return ResponseEntity.ok(AuthResponce.from(dto));
        }catch (BadCredentialsException e) {
            throw new IncorrectPasswordException("Invalid email or password");
        }
    }

    @PostMapping("refresh")
    public JwtAuthenticationDto refresh(@RequestBody RefreshRequest request) {
        return refreshToken(request.getRefreshToken());
    }

    private JwtAuthenticationDto refreshToken(String token) {
        UserDetails user = userDetailsService.loadUserByUsername(jwtService.extractUsername(token));

        return jwtService.refreshAuthToken(user, token);
    }

    private JwtAuthenticationDto generateToken(String email) {
        UserDetails user = userDetailsService.loadUserByUsername(email);

        return jwtService.generateAuthToken(user);
    }
}
