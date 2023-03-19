package com.arsen.group.management.service;

import com.arsen.group.management.domain.GroupLesson;
import com.arsen.group.management.domain.GroupRead;
import com.arsen.group.management.dto.GroupLessonDto;
import com.arsen.group.management.dto.GroupLessonsRequestDto;
import com.arsen.group.management.dto.GroupLessonsResponseDto;
import com.arsen.group.management.dto.MultipleLessonGroupResponseDto;
import com.arsen.group.management.repository.GroupLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupLessonService {

    private final GroupLessonRepository groupLessonRepository;
    private final GroupReadService groupReadService;


    public MultipleLessonGroupResponseDto readLessonWithGroups(long lessonId){
        return new MultipleLessonGroupResponseDto(lessonId, groupLessonRepository.findGroupsByLessonId(lessonId));
    }

    public GroupLessonsResponseDto readGroupWithLessons(GroupLessonsRequestDto requestDto){
        groupReadService.readById(requestDto.getGroupId());

        return new GroupLessonsResponseDto(requestDto.getGroupId(),
                groupLessonRepository.findLessonsByGroupInRange(
                        requestDto.getGroupId(),
                        requestDto.getStartDate(),
                        requestDto.getEndDate()));
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
