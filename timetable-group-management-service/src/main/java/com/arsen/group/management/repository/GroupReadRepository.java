package com.arsen.group.management.repository;

import com.arsen.group.management.domain.GroupRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupReadRepository extends JpaRepository<GroupRead, Long> {

    @Query("select gr from GroupRead gr join fetch gr.groups where gr.id = :id")
    Optional<GroupRead> findGroupReadWithRelatedGroups(@Param("id") long id);

    Set<GroupRead> findAllByIdIn(Set<Long> ids);



}
