package com.pnu.system.timetable.mapper;

import com.pnu.system.timetable.api.dto.LessonCreateRequest;
import com.pnu.system.timetable.api.dto.LessonResponseDto;
import com.pnu.system.timetable.api.dto.LessonUpdateRequest;
import com.pnu.system.timetable.api.dto.board.LessonBoardLessonDto;
import com.pnu.system.timetable.api.dto.board.LessonBoardResponseDto;
import com.pnu.system.timetable.domain.Lesson;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    Lesson asLesson(LessonCreateRequest request);

    Lesson asLesson(LessonUpdateRequest request);

    LessonResponseDto asLessonResponseDto(Lesson lesson);

    LessonBoardResponseDto asLessonBoardResponseDto(LocalDate date, List<LessonBoardLessonDto> lessons);

    default List<LessonBoardResponseDto> asLessonBoard(Map<LocalDate, List<LessonBoardLessonDto>> lessonMap) {
        return lessonMap
                .entrySet()
                .stream()
                .map(entry -> asLessonBoardResponseDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

}
