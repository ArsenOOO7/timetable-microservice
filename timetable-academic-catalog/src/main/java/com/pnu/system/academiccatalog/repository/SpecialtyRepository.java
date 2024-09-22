package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, String> {
}
