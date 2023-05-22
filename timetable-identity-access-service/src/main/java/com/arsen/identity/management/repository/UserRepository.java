package com.arsen.identity.management.repository;

import com.arsen.identity.management.domain.User;
import com.arsen.identity.management.dto.user.UserResponseDto;
import com.arsen.identity.management.dto.user.UserTokenResponseDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select new com.arsen.identity.management.dto.user.UserResponseDto(u.id, u.email) from User u where u.id = :id")
    Optional<UserResponseDto> findUserById(@Param("id") long id);

    @Query("select new com.arsen.identity.management.dto.user.UserTokenResponseDto(u.email, u.role) from User u where u.id = :id")
    UserTokenResponseDto findUserByTokenId(@Param("id") long id);

    User findByEmail(String email);

}
