package com.arsen.identity.management.service;

import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.identity.management.domain.Role;
import com.arsen.identity.management.domain.User;
import com.arsen.identity.management.dto.user.UserCreateDto;
import com.arsen.identity.management.dto.user.UserPasswordChangeDto;
import com.arsen.identity.management.dto.user.UserResponseDto;
import com.arsen.identity.management.dto.user.UserTokenResponseDto;
import com.arsen.identity.management.exception.InvalidCredentialsException;
import com.arsen.identity.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User readById(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with " + id + " not found!"));
    }

    public User readByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserResponseDto readById(long id, boolean dto){
        return userRepository.findUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with " + id + " not found!"));
    }

    public UserTokenResponseDto readByTokenId(long id){
        return userRepository.findUserByTokenId(id);
    }

    public UserResponseDto createUser(UserCreateDto userDto){

        if(userDto == null){
            throw new EntityNullReferenceException("User cannot be null!");
        }

        User user = new User().toBuilder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.UNIT_MANAGER)
                .build();


        userRepository.save(user);

        return new UserResponseDto(user.getId(), user.getEmail());
    }


    public void updatePassword(long id, UserPasswordChangeDto passwordChangeDto){

        User user = readById(id);

        if(!passwordEncoder.matches(passwordChangeDto.getOldPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Incorrect old password!");
        }

        user.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
        userRepository.save(user);

    }



    public void delete(long id){
        userRepository.deleteById(id);
    }

}
