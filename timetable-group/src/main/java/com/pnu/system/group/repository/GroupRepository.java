package com.pnu.system.group.repository;

import com.pnu.system.group.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

    boolean existsByParentId(String id);

}
