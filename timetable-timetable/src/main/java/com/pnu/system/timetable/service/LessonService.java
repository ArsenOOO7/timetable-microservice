package com.pnu.system.timetable.service;

import com.pnu.system.common.exception.InvalidParameterException;
import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.common.utils.UserUtils;
import com.pnu.system.timetable.api.dto.LessonCreateRequest;
import com.pnu.system.timetable.api.dto.LessonResponseDto;
import com.pnu.system.timetable.api.dto.LessonUpdateRequest;
import com.pnu.system.timetable.api.dto.board.LessonBoardResponseDto;
import com.pnu.system.timetable.api.dto.search.BaseLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.GroupLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.LocationLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.TeacherLessonSearchRequest;
import com.pnu.system.timetable.domain.Lesson;
import com.pnu.system.timetable.domain.TimetableUserSnapshot;
import com.pnu.system.timetable.mapper.LessonMapper;
import com.pnu.system.timetable.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService extends AbstractPersistenceService<Lesson> {

    private final LessonMapper mapper;
    private final LessonRepository repository;
    private final LessonTypeService lessonTypeService;
    private final TimetableUserSnapshotService userSnapshotService;
    private final TimetableGroupSnapshotService groupSnapshotService;
    private final TimetableSubjectSnapshotService subjectSnapshotService;
    private final TimetableLessonLocationSnapshotService lessonLocationSnapshotService;

    public LessonResponseDto create(LessonCreateRequest request) {
        Lesson lesson = mapper.asLesson(request);
        return populate(super.create(lesson));
    }

    public LessonResponseDto update(LessonUpdateRequest request) {
        Lesson lesson = mapper.asLesson(request);
        return populate(super.update(lesson));
    }

    public LessonResponseDto getById(String id) {
        return populate(getOne(id));
    }

    public List<LessonBoardResponseDto> getList(BaseLessonSearchRequest request) {
        TimetableUserSnapshot user = userSnapshotService.getById(UserUtils.getId());
        return switch (user.getType()) {
            case USER ->
                    mapper.asLessonBoard(repository.getByGroups(request, userSnapshotService.getGroupIdListByUserId(UserUtils.getId())));
            case TEACHER -> mapper.asLessonBoard(repository.getByTeacher(request, user.getId()));
            default -> throw new InvalidParameterException("Invalid user type: " + user.getType());
        };
    }

    public List<LessonBoardResponseDto> getListByGroup(GroupLessonSearchRequest request) {
        return mapper.asLessonBoard(repository.getByGroup(request));
    }

    public List<LessonBoardResponseDto> getListByTeacher(TeacherLessonSearchRequest request) {
        return mapper.asLessonBoard(repository.getByTeacher(request));
    }

    public List<LessonBoardResponseDto> getListByLocation(LocationLessonSearchRequest request) {
        return mapper.asLessonBoard(repository.getByLocation(request));
    }

    private LessonResponseDto populate(Lesson lesson) {
        LessonResponseDto responseDto = mapper.asLessonResponseDto(lesson);
        responseDto.setType(lessonTypeService.getOne(lesson.getTypeId()));
        responseDto.setTeacher(userSnapshotService.getById(lesson.getTeacherId()));
        responseDto.setSubject(subjectSnapshotService.getById(lesson.getSubjectId()));
        responseDto.setGroups(groupSnapshotService.getByIds(lesson.getGroupIds()));

        if (!lesson.isOnline()) {
            responseDto.setLocation(lessonLocationSnapshotService.getById(lesson.getLessonLocationId()));
        }

        return responseDto;
    }

    @Override
    protected Class<Lesson> getEntityType() {
        return Lesson.class;
    }

    @Override
    protected JpaRepository<Lesson, String> getRepository() {
        return repository;
    }
}
