package com.busir.gardarian.bankloansystem.service;

import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;
import com.busir.gardarian.bankloansystem.entity.User;
import com.busir.gardarian.bankloansystem.entity.enums.Role;
import com.busir.gardarian.bankloansystem.service.dto.ClientAdditionalInfoForm;
import com.busir.gardarian.bankloansystem.service.dto.UserProfileDto;
import com.busir.gardarian.bankloansystem.service.dto.UserRegistrationForm;
import com.busir.gardarian.bankloansystem.service.exception.EmailAlreadyExistException;
import com.busir.gardarian.bankloansystem.service.exception.IncorrectPasswordException;
import com.busir.gardarian.bankloansystem.service.exception.UserIsNotActive;
import com.busir.gardarian.bankloansystem.service.exception.UserNotFoundException;
import com.busir.gardarian.bankloansystem.service.interfaces.ClientAdditionalInfoRepositoryImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.PasswordHasherImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class AccountService {

    private final UserRepositoryImpl userRepository;
    private final ClientAdditionalInfoRepositoryImpl clientAdditionalInfoRepository;
    private final PasswordHasherImpl passwordHasher;

    public UserProfileDto validateAndGetUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }else if (!user.getIsActive()){
            throw new UserIsNotActive("User is not active");
        }
        return UserProfileDto.fromUser(user);
    }

    public UserProfileDto register(UserRegistrationForm form) {

        if (userRepository.findByEmail(form.getEmail()) != null) {
            throw new EmailAlreadyExistException("That's email already exist");
        }

        User user = new User(
                0L,
                form.getEmail(),
                passwordHasher.hashPassword(form.getPassword()),
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

    public Long addAdditionalInfo(ClientAdditionalInfoForm form) {
        User user = userRepository.findById(form.getUserId());
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        ClientAdditionalInfo clientAdditionalInfo = ClientAdditionalInfoForm.toEntity(form);

        clientAdditionalInfo = clientAdditionalInfoRepository.saveClientAdditionalInfo(clientAdditionalInfo);

        return clientAdditionalInfo.getId();
    }

    @Autowired
    public AccountService(UserRepositoryImpl userRepository, ClientAdditionalInfoRepositoryImpl clientAdditionalInfoRepository, PasswordHasherImpl passwordHasher) {
        this.userRepository = userRepository;
        this.clientAdditionalInfoRepository = clientAdditionalInfoRepository;
        this.passwordHasher = passwordHasher;
    }
}
