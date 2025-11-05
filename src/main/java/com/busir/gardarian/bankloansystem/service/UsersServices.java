package com.busir.gardarian.bankloansystem.service;

import com.busir.gardarian.bankloansystem.entity.ClientAdditionalInfo;
import com.busir.gardarian.bankloansystem.entity.User;
import com.busir.gardarian.bankloansystem.entity.enums.Role;
import com.busir.gardarian.bankloansystem.service.dto.ManagerRegistrationForm;
import com.busir.gardarian.bankloansystem.service.dto.UserInfo;
import com.busir.gardarian.bankloansystem.service.exception.UserNotFoundException;
import com.busir.gardarian.bankloansystem.service.interfaces.ClientAdditionalInfoRepositoryImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.PasswordHasherImpl;
import com.busir.gardarian.bankloansystem.service.interfaces.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersServices {
    private final PasswordHasherImpl passwordHasher;
    private final UserRepositoryImpl userRepository;
    private final ClientAdditionalInfoRepositoryImpl clientAdditionalInfoRepository;

    public Boolean createManager(ManagerRegistrationForm form) {
        User user = new User();

        user.setEmail(form.getEmail());
        user.setPasswordHash(passwordHasher.hashPassword(extractUsername(form.getEmail())));
        user.setRole(Role.MANAGER);
        user.setFullName(form.getFullName());
        user.setCreated(Timestamp.from(Instant.now()));
        user.setUpdated(Timestamp.from(Instant.now()));
        user.setIsActive(true);

        userRepository.save(user);

        return true;
    }

    public List<UserInfo> getAllClients(){
        List<User> clients = userRepository.findByRole(Role.CLIENT);

        return clients
                .stream()
                .map(UserInfo::new)
                .toList();
    }

    public List<UserInfo> getAllManagers(){
        List<User> managers = userRepository.findByRole(Role.MANAGER);

        return managers
                .stream()
                .map(UserInfo::new)
                .toList();

    }
    public ClientAdditionalInfo getInfoAboutClient(Long id) {
        return clientAdditionalInfoRepository.getClientAdditionalInfoByUserId(id);
    }

    public boolean deactivateUser(Long id) {
        User user = userRepository.findById(id);

        if(user == null) {
            throw new  UserNotFoundException("User with id " + id + " not found");
        }

        user.setIsActive(false);

        userRepository.save(user);

        return true;
    }

    public boolean activateUser(Long id){
        User user = userRepository.findById(id);

        if(user == null) {
            throw new  UserNotFoundException("User with id " + id + " not found");
        }

        user.setIsActive(true);

        userRepository.save(user);

        return true;
    }

    private String extractUsername(String email) {
        return email.split("@")[0];
    }
}
