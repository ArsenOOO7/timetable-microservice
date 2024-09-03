package com.pnu.system.service;

import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.domain.Permission;
import com.pnu.system.domain.User;
import com.pnu.system.mapper.UserMapper;
import com.pnu.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractPersistenceService<User> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<Permission> getUserPermissions(String id) {
        return userRepository.findPermissionsByUserId(id);
    }

    @Override
    protected Class<User> getEntityType() {
        return User.class;
    }

    @Override
    protected JpaRepository<User, String> getRepository() {
        return userRepository;
    }
}
