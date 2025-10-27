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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountService accountService;
    private final MyUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("register")
    public ResponseEntity<AuthResponce> register(@RequestBody @Valid RegisterRequest registerRequest) {
        logger.info("REGISTER attempt for email: {}", registerRequest.getEmail());

        try {
            UserProfileDto userDto = accountService.register(RegisterRequest.toForm(registerRequest));

            logger.debug("User registered successfully: {}", registerRequest.getEmail());

            JwtAuthenticationDto dto = generateToken(registerRequest.getEmail());

            logger.info("REGISTER successful for email: {}, user ID: {}",
                    registerRequest.getEmail(), userDto.getId());

            return ResponseEntity.ok(AuthResponce.from(dto));
        } catch (Exception e) {
            logger.error("REGISTER failed for email: {}, error: {}",
                    registerRequest.getEmail(), e.getMessage());
            throw e;
        }
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponce> login(@RequestBody @Valid AuthRequest request) {
        logger.info("LOGIN attempt for email: {}", request.getEmail());
        try {
            logger.debug("Attempting authentication for: {}", request.getEmail());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            accountService.validateAndGetUser(request.getEmail());
            logger.debug("User validation successful: {}", request.getEmail());

            JwtAuthenticationDto dto = generateToken(request.getEmail());
            logger.info("LOGIN successful for email: {}", request.getEmail());

            return ResponseEntity.ok(AuthResponce.from(dto));
        }catch (BadCredentialsException | UsernameNotFoundException e) {
            logger.error("LOGIN failed - invalid credentials for email: {}", request.getEmail());
            throw new IncorrectPasswordException("Invalid email or password");
        } catch (Exception e) {
            logger.error("LOGIN error for email: {}, error: {}",
                    request.getEmail(), e.getMessage(), e);
            throw e;
        }
    }

    @PostMapping("refresh")
    public JwtAuthenticationDto refresh(@RequestBody @Valid RefreshRequest request) {
        logger.debug("REFRESH token request received");
        try {
            JwtAuthenticationDto result = refreshToken(request.getRefreshToken());
            logger.info("REFRESH token successful");
            return result;
        }catch (Exception e) {
            logger.error("REFRESH token failed: {}", e.getMessage());
            throw e;
        }

    }

    private JwtAuthenticationDto refreshToken(String token) {
        logger.debug("Refreshing token for user");
        String username = jwtService.extractUsername(token);
        logger.debug("Extracted username from token: {}", username);

        UserDetails user = userDetailsService.loadUserByUsername(username);

        return jwtService.refreshAuthToken(user, token);
    }

    private JwtAuthenticationDto generateToken(String email) {
        logger.debug("Generating token for: {}", email);
        UserDetails user = userDetailsService.loadUserByUsername(email);

        return jwtService.generateAuthToken(user);
    }
}
