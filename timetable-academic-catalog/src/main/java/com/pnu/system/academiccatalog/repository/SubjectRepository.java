package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {

}
