package com.busir.gardarian.bankloansystem.dao.repositoriy.mapping;

import com.busir.gardarian.bankloansystem.dao.repositoriy.models.UserDB;
import com.busir.gardarian.bankloansystem.entity.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserMapper {
    public List<User> toEntity(List<UserDB> userDBList) {
        return userDBList
                .stream()
                .map(this::toEntity)
                .toList();
    }

    public List<UserDB> toDB(List<User> userList) {
        return userList
                .stream()
                .map(this::toDB)
                .toList();
    }

    public User toEntity(UserDB userDB) {
        if(userDB == null)
            return null;

        User user = new User();

        user.setId(Long.valueOf(userDB.getId()));
        user.setEmail(userDB.getEmail());
        user.setPasswordHash(userDB.getPasswordHash());
        user.setRole(userDB.getRole());
        user.setFullName(userDB.getFullName());
        user.setPhone(userDB.getPhone());
        user.setCreated(Timestamp.valueOf(userDB.getCreatedAt()));
        user.setUpdated(Timestamp.valueOf(userDB.getUpdatedAt()));
        user.setIsActive(userDB.getIsActive());

        return user;
    }

    public UserDB toDB(User user) {
        if(user == null)
            return null;

        UserDB userDB = new UserDB();

        if(user.getId() != null)
            userDB.setId(Math.toIntExact(user.getId()));

        userDB.setEmail(user.getEmail());
        userDB.setPasswordHash(user.getPasswordHash());
        userDB.setRole(user.getRole());
        userDB.setFullName(user.getFullName());
        userDB.setPhone(user.getPhone());
        userDB.setCreatedAt(user.getCreated().toLocalDateTime());
        userDB.setUpdatedAt(user.getUpdated().toLocalDateTime());
        userDB.setIsActive(user.getIsActive());

        return userDB;
    }
}
