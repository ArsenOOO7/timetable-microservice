package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.domain.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends JpaRepository<Chair, String> {

}
