package com.arsen.timetable.service;

import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.timetable.client.GroupManagementClient;
import com.arsen.timetable.domain.Lesson;
import com.arsen.timetable.dto.LessonCreatedBrieflyResponseDto;
import com.arsen.timetable.dto.LessonDto;
import com.arsen.timetable.dto.LessonSearchDto;
import com.arsen.timetable.dto.TimetableResponseDto;
import com.arsen.timetable.dto.group.GroupLessonDto;
import com.arsen.timetable.dto.group.GroupLessonsRequestDto;
import com.arsen.timetable.repository.TimetableRepository;
import com.arsen.timetable.service.readonly.ClassroomReadService;
import com.arsen.timetable.service.readonly.SubjectReadService;
import com.arsen.timetable.service.readonly.TeacherReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final TimetableRepository timetableRepository;

    private final ClassroomReadService classroomReadService;
    private final SubjectReadService subjectReadService;
    private final TeacherReadService teacherReadService;

    private final GroupManagementClient groupManagementClient;


    public Lesson readById(long id){
        return timetableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson with id " + id + " not found!"));
    }

    public List<TimetableResponseDto> readByTeacher(LessonSearchDto searchDto){
        teacherReadService.readById(searchDto.getTeacherId());

        List<TimetableResponseDto> timetableResponse = timetableRepository
                .findTimetableByTeacher(searchDto.getTeacherId(), searchDto.getStartDate(), searchDto.getEndDate());

        for (TimetableResponseDto timetableResponseDto : timetableResponse) {
            timetableResponseDto.setGroups(groupManagementClient.readByLesson(timetableResponseDto.getId()).getGroups());
        }

        return timetableResponse;
    }

    public List<TimetableResponseDto> readByGroup(LessonSearchDto searchDto){
        Set<Long> ids = groupManagementClient.readByGroup(searchDto.getGroupId(),
                new GroupLessonsRequestDto(searchDto.getGroupId(), searchDto.getStartDate(), searchDto.getEndDate())).getLessonIds();

        List<TimetableResponseDto> timetableResponses = timetableRepository.findTimetableByGroup(ids);
        for (TimetableResponseDto timetableResponseDto : timetableResponses) {
            timetableResponseDto.setGroups(groupManagementClient.readByLesson(timetableResponseDto.getId()).getGroups());
        }

        return timetableResponses;
    }

    public LessonCreatedBrieflyResponseDto create(LessonDto lessonDto){

        if(lessonDto == null){
            throw new NullPointerException("Lesson cannot be null!");
        }

        Lesson lesson = new Lesson();

        lesson.setSubject(subjectReadService.readById(lessonDto.getSubjectId()));
        lesson.setClassroom(classroomReadService.readById(lessonDto.getClassroomId()));
        classroomReadService.checkBusyClassroom(lessonDto.getClassroomId(), lessonDto.getLessonDate(), lessonDto.getLessonNumber());

        lesson.setTeacher(teacherReadService.readById(lessonDto.getTeacherId()));
        teacherReadService.checkBusyTeacher(lessonDto.getTeacherId(), lessonDto.getLessonDate(), lessonDto.getLessonNumber());

        lesson.setLessonType(lessonDto.getLessonType());
        lesson.setLessonNumber(lessonDto.getLessonNumber());
        lesson.setLessonDate(lessonDto.getLessonDate());
        lesson.setOnline(lessonDto.isOnline());

        lesson = timetableRepository.save(lesson);

        GroupLessonDto groupLesson = new GroupLessonDto();
        groupLesson.setLessonId(lesson.getId());
        groupLesson.setLessonDate(lesson.getLessonDate());
        for (Long groupId : lessonDto.getGroupIds()) {
            groupLesson.setGroupId(groupId);
            groupManagementClient.create(groupLesson);
        }

        return new LessonCreatedBrieflyResponseDto(lesson.getId(), lesson.getLessonDate());
    }


    public void update(LessonDto lessonDto) {

        if (lessonDto == null) {
            throw new NullPointerException("Lesson cannot be null!");
        }

        Lesson lesson = readById(lessonDto.getId());
        lesson.setLessonType(lessonDto.getLessonType());
        lesson.setLessonNumber(lessonDto.getLessonNumber());
        lesson.setLessonDate(lessonDto.getLessonDate());
        lesson.setOnline(lessonDto.isOnline());

        if(lesson.getSubject().getId() != lessonDto.getSubjectId()){
            lesson.setSubject(subjectReadService.readById(lessonDto.getSubjectId()));
        }

        if(lesson.getClassroom().getId() != lessonDto.getClassroomId()){
            lesson.setClassroom(classroomReadService.readById(lessonDto.getClassroomId()));
            classroomReadService.checkBusyClassroom(lessonDto.getClassroomId(), lessonDto.getLessonDate(), lessonDto.getLessonNumber());
        }

        if(lesson.getTeacher().getId() != lessonDto.getTeacherId()){
            lesson.setTeacher(teacherReadService.readById(lessonDto.getTeacherId()));
            teacherReadService.checkBusyTeacher(lessonDto.getTeacherId(), lessonDto.getLessonDate(), lessonDto.getLessonNumber());
        }

        timetableRepository.save(lesson);

    }


    public void delete(long id){
        timetableRepository.deleteById(id);
    }

}