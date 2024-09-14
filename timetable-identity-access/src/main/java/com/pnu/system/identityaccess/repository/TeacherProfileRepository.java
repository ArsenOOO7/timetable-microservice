package com.pnu.system.identityaccess.repository;

import com.pnu.system.identityaccess.domain.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherProfileRepository extends JpaRepository<TeacherProfile, String> {

}
