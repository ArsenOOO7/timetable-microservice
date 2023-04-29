package com.arsen.group.management.service;

import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.group.management.domain.GroupLesson;
import com.arsen.group.management.domain.GroupRead;
import com.arsen.group.management.dto.GroupLessonDto;
import com.arsen.group.management.dto.GroupLessonsRequestDto;
import com.arsen.group.management.dto.LessonGroupResponseDto;
import com.arsen.group.management.dto.MultipleLessonGroupResponseDto;
import com.arsen.group.management.repository.GroupLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class GroupLessonService {

    private final GroupLessonRepository groupLessonRepository;
    private final GroupService groupService;


    public MultipleLessonGroupResponseDto readLessonWithGroups(long lessonId){
        return new MultipleLessonGroupResponseDto(lessonId, groupLessonRepository.findGroupsByLessonId(lessonId));
    }

    public List<MultipleLessonGroupResponseDto> readLessonWithGroups(Set<Long> ids){
        Set<LessonGroupResponseDto> lessons = groupLessonRepository.findGroupsByLessons(ids);
        return  lessons.stream()
                .collect(groupingBy(LessonGroupResponseDto::getLessonId, mapping(LessonGroupResponseDto::getGroupLessonDto, toSet())))
                .entrySet()
                .stream()
                .map((entry) -> new MultipleLessonGroupResponseDto(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(MultipleLessonGroupResponseDto::getLessonId))
                .collect(Collectors.toList());
    }


    public List<MultipleLessonGroupResponseDto> readGroupWithLessonsMultiple(GroupLessonsRequestDto requestDto){
        Set<LessonGroupResponseDto> lessons = groupLessonRepository.findLessonsByGroupInRange(requestDto.getGroupId(),
                requestDto.getStartDate(), requestDto.getEndDate());


        return lessons.stream()
                .collect(groupingBy(LessonGroupResponseDto::getLessonId, mapping(LessonGroupResponseDto::getGroupLessonDto, toSet())))
                .entrySet()
                .stream()
                .map((entry) -> new MultipleLessonGroupResponseDto(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(MultipleLessonGroupResponseDto::getLessonId))
                .collect(Collectors.toList());
    }


    public void create(List<GroupLessonDto> groupLessons){

        if(groupLessons == null || groupLessons.isEmpty()){
            throw new EntityNullReferenceException("Group lessons cannot be null!!");
        }

        Set<GroupRead> groups = groupService.readGroups(groupLessons.stream().map(GroupLessonDto::getGroupId).collect(toSet()));
        GroupLessonDto groupLessonDto = groupLessons.get(0);

        List<GroupLesson> lessons = groups.stream().map(group -> new GroupLesson(groupLessonDto.getLessonId(), groupLessonDto.getLessonDate(), group)).toList();
        groupLessonRepository.saveAll(lessons);

    }

    public void delete(long lesson, long group){
        groupLessonRepository.deleteByLessonIdAndGroupId(lesson, group);
    }
    public void delete(long lesson){
        groupLessonRepository.deleteByLessonId(lesson);
    }

}
