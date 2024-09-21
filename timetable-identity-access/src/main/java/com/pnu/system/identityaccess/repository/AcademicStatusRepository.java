package com.pnu.system.identityaccess.repository;

import com.pnu.system.identityaccess.domain.AcademicStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicStatusRepository extends JpaRepository<AcademicStatus, String> {

}
