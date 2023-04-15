package com.arsen.timetable.client;


import com.arsen.timetable.dto.group.GroupDto;
import com.arsen.timetable.dto.group.GroupLessonDto;
import com.arsen.timetable.dto.group.GroupLessonsRequestDto;
import com.arsen.timetable.dto.group.MultipleGroupLessonDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(name = "group-management")
public interface GroupManagementClient {

    @GetMapping("/api/group/management/lesson/{lesson}")
    @CircuitBreaker(name = "group-management-service", fallbackMethod = "getDefaultGroupLesson")
    MultipleGroupLessonDto readByLesson(@PathVariable long lesson);

    @PostMapping("/api/group/management/group/{groupId}")
    @CircuitBreaker(name = "group-management-service", fallbackMethod = "getDefaultLessonsByGroup")
    List<MultipleGroupLessonDto> readByGroup(@PathVariable long groupId, @RequestBody GroupLessonsRequestDto requestDto);

    @PostMapping("/api/group/management/lesson")
    @CircuitBreaker(name = "group-management-service", fallbackMethod = "getDefaultGroupsByLessons")
    List<MultipleGroupLessonDto> readByLessons(@RequestBody Set<Long> ids);

    @PostMapping("/api/group/management")
    void create(@RequestBody GroupLessonDto lessonDto);

    default MultipleGroupLessonDto getDefaultGroupLesson(long lesson, Throwable throwable){
        return new MultipleGroupLessonDto(lesson, Set.of(new GroupDto(0, "UNKNOWN")));
    }

    default List<MultipleGroupLessonDto> getDefaultLessonsByGroup(Throwable throwable){
        return List.of();
    }

    default List<MultipleGroupLessonDto> getDefaultGroupsByLessons(Set<Long> ids, Throwable throwable){
        return ids
                .stream()
                .map(id -> getDefaultGroupLesson(id, throwable))
                .toList();
    }
}
