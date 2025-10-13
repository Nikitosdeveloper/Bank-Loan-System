package com.busir.gardarian.bankloansystem.service.interfaces;

import com.busir.gardarian.bankloansystem.entity.User;

public interface UserRepositoryImpl {
    public User findById(Long id);
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User save(User user);
    public Boolean delete(Long id);
}
