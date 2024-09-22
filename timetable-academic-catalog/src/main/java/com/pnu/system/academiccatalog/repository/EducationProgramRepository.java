package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.EducationalProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationProgramRepository extends JpaRepository<EducationalProgram, String> {
}
