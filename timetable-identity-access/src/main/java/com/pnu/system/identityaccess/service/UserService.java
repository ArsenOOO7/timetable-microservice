package com.pnu.system.identityaccess.service;

import com.pnu.system.common.constant.UserType;
import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.file.storage.constant.Folders;
import com.pnu.system.file.storage.service.FileStorageService;
import com.pnu.system.file.storage.utils.StorageResourcePathUtil;
import com.pnu.system.file.storage.utils.StorageResourceValidator;
import com.pnu.system.identityaccess.api.dto.UserCreateRequest;
import com.pnu.system.identityaccess.api.dto.UserResponseDto;
import com.pnu.system.identityaccess.api.dto.UserUpdateRequest;
import com.pnu.system.identityaccess.domain.Role;
import com.pnu.system.identityaccess.domain.TeacherProfile;
import com.pnu.system.identityaccess.domain.User;
import com.pnu.system.identityaccess.event.model.UserCreateEvent;
import com.pnu.system.identityaccess.event.model.UserDeleteEvent;
import com.pnu.system.identityaccess.event.model.UserUpdateEvent;
import com.pnu.system.identityaccess.mapper.UserMapper;
import com.pnu.system.identityaccess.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractPersistenceService<User> {

    private final UserMapper mapper;
    private final RoleService roleService;
    private final UserRepository repository;
    private final ApplicationEventPublisher eventPublisher;
    private final TeacherProfileService teacherProfileService;
    private final FileStorageService storageService;

    public UserResponseDto create(UserCreateRequest request) {
        User user = mapper.asUser(request);
        assignRoles2User(user, request.getRoleIds());
        User created = super.create(user);
        createTeacherProfile(created);
        eventPublisher.publishEvent(new UserCreateEvent(user));
        return mapper.asUserResponseDto(created);
    }

    public UserResponseDto update(UserUpdateRequest request) {
        User existent = getOne(request.getId());
        mapper.applyUserUpdateRequest(existent, request);

        assignRoles2User(existent, request.getRoleIds());
        User updated = super.update(existent);

        createTeacherProfile(updated);

        eventPublisher.publishEvent(new UserUpdateEvent(existent));
        return mapper.asUserResponseDto(updated);
    }

    public UserResponseDto getById(String id) {
        return mapper.asUserResponseDto(getOne(id));
    }

    public Optional<User> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public List<String> getUserGroupIds(String id) {
        return repository.getUserGroupIds(id);
    }

    public String saveProfilePhoto(String id, MultipartFile file) {
        StorageResourceValidator.validateImageFile(file);
        User user = getOne(id);
        if (StringUtils.isNotBlank(user.getProfilePhotoUrl())) {
            storageService.deleteFile(user.getProfilePhotoUrl());
        }

        String uri = StorageResourcePathUtil.buildUuidUri(file.getOriginalFilename(), user.getId(), Folders.PROFILE_PHOTOS_FOLDER);
        storageService.upload(file, uri, true);
        String url = storageService.getUrl(uri);
        repository.updateProfilePhoto(id, url);
        return url;
    }

    public void deleteProfilePhoto(String id) {
        User user = getOne(id);
        if (StringUtils.isBlank(user.getProfilePhotoUrl())) {
            return;
        }
        storageService.deleteFile(user.getProfilePhotoUrl());
        repository.updateProfilePhoto(id, null);
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
        eventPublisher.publishEvent(new UserDeleteEvent(id));
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
