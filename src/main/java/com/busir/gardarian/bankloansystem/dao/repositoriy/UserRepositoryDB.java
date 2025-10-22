package com.busir.gardarian.bankloansystem.dao.repositoriy;

import com.busir.gardarian.bankloansystem.dao.repositoriy.interfaces.UserRepositoryJPA;
import com.busir.gardarian.bankloansystem.dao.repositoriy.mapping.UserMapper;
import com.busir.gardarian.bankloansystem.dao.repositoriy.models.UserDB;
import com.busir.gardarian.bankloansystem.entity.User;
import com.busir.gardarian.bankloansystem.entity.enums.Role;
import com.busir.gardarian.bankloansystem.service.interfaces.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("devDAO")
@Primary
public class UserRepositoryDB implements UserRepositoryImpl {
    private final UserRepositoryJPA repository;
    private final UserMapper userMapper;

    @Override
    public List<User> findAll() {
        List<UserDB> users = repository.findAll();
        return userMapper.toEntity(users);
    }

    @Override
    public List<User> findByRole(Role role) {
        List<UserDB> users = repository.findByRole(role);
        return userMapper.toEntity(users);
    }

    @Override
    public User findById(Long id) {
        UserDB user = repository.findById(id).orElse(null);
        return userMapper.toEntity(user);
    }

    @Override
    public User findByUsername(String username) {
        UserDB userDB = repository.findByFullName(username).orElse(null);
        return userMapper.toEntity(userDB);
    }

    @Override
    public User findByEmail(String email) {
        UserDB userDB = repository.findByEmail(email).orElse(null);
        return userMapper.toEntity(userDB);
    }

    @Override
    public User save(User user) {
        UserDB userDB = userMapper.toDB(user);
        return userMapper.toEntity(repository.save(userDB));
    }

    @Override
    public Boolean delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
