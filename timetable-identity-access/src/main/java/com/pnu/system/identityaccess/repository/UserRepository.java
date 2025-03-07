package com.pnu.system.identityaccess.repository;

import com.pnu.system.identityaccess.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select user.groupIds from User user where user.id = ?1")
    List<String> getUserGroupIds(String id);

    @Modifying
    @Query("update User user set user.profilePhotoUrl = ?2 where user.id = ?1")
    void updateProfilePhoto(String id, String profilePhotoUrl);

}
