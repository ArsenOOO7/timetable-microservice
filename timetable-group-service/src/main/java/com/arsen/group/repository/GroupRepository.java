package com.arsen.group.repository;

import com.arsen.group.domain.Group;
import com.arsen.group.dto.GroupDto;
import com.arsen.group.dto.GroupResultSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT new com.arsen.group.dto.GroupResultSearchDto(" +
            "groups.id as id, " +
            "concat(groups.cypher, " +
            "CASE WHEN groups.master THEN 'м' ELSE '' END, " +
            "CASE WHEN groups.college THEN 'к' ELSE '' END, " +
            "'-', " +
            "groups.academicYear, " +
            "groups.number) as groupName" +
            ") " +
            "FROM Group groups where groups.collective = false " +
            "and " +
            "lower(concat(groups.cypher, " +
            "CASE WHEN groups.master THEN 'м' ELSE '' END, " +
            "CASE WHEN groups.college THEN 'к' ELSE '' END, " +
            "'-', " +
            "groups.academicYear, " +
            "groups.number)) like lower(concat('%', :query, '%'))")
    List<GroupResultSearchDto> findQueryGroup(@Param("query") String query);



    @Query("select new com.arsen.group.dto.GroupDto(g.id, g.cypher, g.academicYear, g.number, g.master, g.college, g.collective) from Group g " +
            "where g.id = :id")
    Optional<GroupDto> readByIdDto(@Param("id") long id);


    Set<Group> findAllByIdIn(Set<Long> ids);

    @Query("select g from Group g join fetch g.groups where g.id = :id")
    Optional<Group> getGroupWithGroups(@Param("id") long id);
}
