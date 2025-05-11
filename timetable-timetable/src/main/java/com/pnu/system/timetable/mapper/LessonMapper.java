package com.pnu.system.timetable.mapper;

import com.pnu.system.timetable.api.dto.LessonCreateRequest;
import com.pnu.system.timetable.api.dto.LessonResponseDto;
import com.pnu.system.timetable.api.dto.LessonUpdateRequest;
import com.pnu.system.timetable.api.dto.board.LessonBoardLessonDto;
import com.pnu.system.timetable.api.dto.board.LessonBoardResponseDto;
import com.pnu.system.timetable.domain.Lesson;
import com.pnu.system.timetable.domain.LessonSearch;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    Lesson asLesson(LessonCreateRequest request);

    Lesson asLesson(LessonUpdateRequest request);

    LessonResponseDto asLessonResponseDto(Lesson lesson);

    LessonBoardResponseDto asLessonBoardResponseDto(LocalDate date, List<LessonBoardLessonDto> lessons);

    default List<LessonBoardResponseDto> asLessonBoard(List<LessonSearch> lessons) {
        return lessons.stream()
                .map(this::asLessonBoardLessonDto)
                .collect(Collectors.groupingBy(LessonBoardLessonDto::getDate))
                .entrySet().stream().map(entry -> asLessonBoardResponseDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    LessonBoardLessonDto asLessonBoardLessonDto(LessonSearch lessons);

}
