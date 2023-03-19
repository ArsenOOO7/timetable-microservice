package com.arsen.group.management.repository;

import com.arsen.group.management.domain.GroupLesson;
import com.arsen.group.management.dto.GroupResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface GroupLessonRepository extends JpaRepository<GroupLesson, Long> {

    void deleteByLessonIdAndGroupId(long lessonId, long groupId);

    @Query("SELECT new com.arsen.group.management.dto.GroupResponseDto(g.id, g.fullName) FROM GroupRead g JOIN g.lessons l WHERE l.lessonId = :lessonId")
    Set<GroupResponseDto> findGroupsByLessonId(@Param("lessonId") long lessonId);

    @Query("select gl.lessonId from GroupLesson gl where gl.group.id = :group and gl.lessonDate between :start and :end")
    Set<Long> findLessonsByGroupInRange(@Param("group") long group, @Param("start") LocalDate start, @Param("end") LocalDate end);

}
