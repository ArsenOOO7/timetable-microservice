package com.pnu.system.identityaccess.repository;

import com.pnu.system.identityaccess.domain.UserWithTeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWithTeacherProfileRepository extends JpaRepository<UserWithTeacherProfile, String> {
}
