package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

}
