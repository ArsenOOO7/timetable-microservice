package com.arsen.group.management.repository;

import com.arsen.group.management.domain.GroupLesson;
import com.arsen.group.management.dto.GroupResponseDto;
import com.arsen.group.management.dto.LessonGroupResponseDto;
import com.arsen.group.management.dto.MultipleLessonGroupResponseDto;
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

    @Query("select new com.arsen.group.management.dto.LessonGroupResponseDto(ls.lessonId, new com.arsen.group.management.dto.GroupResponseDto(gr.id, gr.fullName)) " +
            "from GroupLesson ls " +
            "join ls.group gr " +
            "where ls.lessonId in :ids")
    Set<LessonGroupResponseDto> findGroupsByLessons(@Param("ids") Set<Long> ids);

    @Query("select new com.arsen.group.management.dto.LessonGroupResponseDto(gl.lessonId, new com.arsen.group.management.dto.GroupResponseDto(gr.id, gr.fullName)) " +
            "from GroupLesson gl " +
            "join gl.group gr " +
            "left join gr.groups cg " +
            "where gr.id = :group or cg.id = :group and gl.lessonDate between :start and :end")
    Set<LessonGroupResponseDto> findLessonsByGroupInRange(@Param("group") long group, @Param("start") LocalDate start, @Param("end") LocalDate end);


}
