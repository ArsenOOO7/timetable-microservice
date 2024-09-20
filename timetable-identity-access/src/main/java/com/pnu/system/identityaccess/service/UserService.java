package com.pnu.system.identityaccess.service;

import com.pnu.system.common.constant.UserType;
import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.identityaccess.api.dto.UserCreateRequest;
import com.pnu.system.identityaccess.api.dto.UserPreviewDto;
import com.pnu.system.identityaccess.api.dto.UserResponseDto;
import com.pnu.system.identityaccess.api.dto.UserUpdateRequest;
import com.pnu.system.identityaccess.domain.Role;
import com.pnu.system.identityaccess.domain.TeacherProfile;
import com.pnu.system.identityaccess.domain.User;
import com.pnu.system.identityaccess.mapper.UserMapper;
import com.pnu.system.identityaccess.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractPersistenceService<User> {

    private final UserMapper mapper;
    private final RoleService roleService;
    private final UserRepository repository;
    private final TeacherProfileService teacherProfileService;

    public UserResponseDto create(UserCreateRequest userCreateRequest) {
        User user = mapper.asUser(userCreateRequest);
        assignRoles2User(user, userCreateRequest.getRoleIds());
        User created = super.create(user);
        createTeacherProfile(created);
        return mapper.asUserResponseDto(created);
    }

    public UserResponseDto update(UserUpdateRequest userUpdateRequest) {
        User existent = getOne(userUpdateRequest.getId());
        User user = mapper.asUser(userUpdateRequest);

        user.setPassword(existent.getPassword());

        assignRoles2User(user, userUpdateRequest.getRoleIds());
        User updated = super.update(user);
        createTeacherProfile(updated);
        return mapper.asUserResponseDto(updated);
    }

    public UserResponseDto getById(String id) {
        return mapper.asUserResponseDto(getOne(id));
    }

    public List<UserPreviewDto> getPreviewUsers() {
        return repository.getPreviewUsers();
    }

    public Optional<User> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    private void assignRoles2User(User user, List<String> roleIds) {
        List<Role> roles = roleService.getAll(roleIds);
        user.setRoles(roles);
    }

    private void createTeacherProfile(User user) {
        if (!UserType.TEACHER.equals(user.getType())) {
            return;
        }

        if (teacherProfileService.existsById(user.getId())) {
            return;
        }

        TeacherProfile teacherProfile = new TeacherProfile();
        teacherProfile.setUser(user);
        teacherProfileService.create(teacherProfile);
    }

    @Override
    public void delete(String id) {
        teacherProfileService.delete(id);
        super.delete(id);
    }

    @Override
    protected Class<User> getEntityType() {
        return User.class;
    }

    @Override
    protected JpaRepository<User, String> getRepository() {
        return repository;
    }
}
