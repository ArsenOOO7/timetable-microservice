package com.arsen.group.management.service;

import com.arsen.group.management.domain.GroupLesson;
import com.arsen.group.management.domain.GroupRead;
import com.arsen.group.management.dto.*;
import com.arsen.group.management.repository.GroupLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class GroupLessonService {

    private final GroupLessonRepository groupLessonRepository;
    private final GroupReadService groupReadService;


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


    public void create(GroupLessonDto groupLessonDto){

        if(groupLessonDto == null){
            throw new NullPointerException("Group lesson cannot be null!!");
        }

        GroupRead groupRead = groupReadService.readById(groupLessonDto.getGroupId());
        GroupLesson groupLesson = new GroupLesson();
        groupLesson.setLessonId(groupLessonDto.getLessonId());
        groupLesson.setLessonDate(groupLessonDto.getLessonDate());
        groupLesson.setGroup(groupRead);

        groupLessonRepository.save(groupLesson);

    }


    public void delete(long lesson, long group){
        groupLessonRepository.deleteByLessonIdAndGroupId(lesson, group);
    }

}
