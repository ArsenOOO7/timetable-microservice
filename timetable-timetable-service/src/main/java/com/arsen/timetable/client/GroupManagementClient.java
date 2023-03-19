package com.arsen.timetable.client;


import com.arsen.timetable.dto.group.GroupLessonDto;
import com.arsen.timetable.dto.group.GroupLessonsRequestDto;
import com.arsen.timetable.dto.group.GroupLessonsResponseDto;
import com.arsen.timetable.dto.group.MultipleGroupLessonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "group-management")
public interface GroupManagementClient {

    @GetMapping("/api/group/management/lesson/{lesson}")
    MultipleGroupLessonDto readByLesson(@PathVariable long lesson);

    @PostMapping("/api/group/management/group/{groupId}")
    GroupLessonsResponseDto readByGroup(@PathVariable long groupId, @RequestBody GroupLessonsRequestDto requestDto);

    @PostMapping("/api/group/management")
    void create(@RequestBody GroupLessonDto lessonDto);

}
