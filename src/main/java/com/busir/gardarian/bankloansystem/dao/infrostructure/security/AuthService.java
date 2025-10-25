package com.busir.gardarian.bankloansystem.dao.infrostructure.security;

import com.busir.gardarian.bankloansystem.entity.User;
import com.busir.gardarian.bankloansystem.entity.enums.Role;
import com.busir.gardarian.bankloansystem.service.dto.UserProfileDto;
import com.busir.gardarian.bankloansystem.service.dto.UserRegistrationForm;
import com.busir.gardarian.bankloansystem.service.exception.EmailAlreadyExistException;
import com.busir.gardarian.bankloansystem.service.interfaces.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepositoryImpl userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileDto register(UserRegistrationForm form) {

        if (userRepository.findByEmail(form.getEmail()) != null) {
            throw new EmailAlreadyExistException("That's email already exist");
        }

        User user = new User(
                0L,
                form.getEmail(),
                passwordEncoder.encode(form.getPassword()),
                Role.CLIENT,
                form.getFullName(),
                form.getPhone(),
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()),
                true
        );

        user = userRepository.save(user);

        return UserProfileDto.fromUser(user);
    }
}
