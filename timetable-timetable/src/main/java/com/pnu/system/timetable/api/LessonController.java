package com.pnu.system.timetable.api;

import com.pnu.system.timetable.api.dto.LessonCreateRequest;
import com.pnu.system.timetable.api.dto.LessonResponseDto;
import com.pnu.system.timetable.api.dto.LessonUpdateRequest;
import com.pnu.system.timetable.api.dto.board.LessonBoardResponseDto;
import com.pnu.system.timetable.api.dto.search.BaseLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.GroupLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.LocationLessonSearchRequest;
import com.pnu.system.timetable.api.dto.search.TeacherLessonSearchRequest;
import com.pnu.system.timetable.api.validator.LessonCreateValidator;
import com.pnu.system.timetable.api.validator.LessonUpdateValidator;
import com.pnu.system.timetable.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService service;
    private final LessonCreateValidator lessonCreateValidator;
    private final LessonUpdateValidator lessonUpdateValidator;

    @InitBinder("lessonCreateRequest")
    public void initLessonCreateRequestBinder(WebDataBinder binder) {
        binder.addValidators(lessonCreateValidator);
    }

    @InitBinder("lessonUpdateRequest")
    public void initLessonUpdateRequestBinder(WebDataBinder binder) {
        binder.addValidators(lessonUpdateValidator);
    }

    @PostMapping
    public LessonResponseDto create(@Valid @RequestBody LessonCreateRequest lessonCreateRequest) {
        return service.create(lessonCreateRequest);
    }

    @PutMapping
    public LessonResponseDto update(@Valid @RequestBody LessonUpdateRequest lessonUpdateRequest) {
        return service.update(lessonUpdateRequest);
    }

    @GetMapping("/{id}")
    public LessonResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping("/list")
    public List<LessonBoardResponseDto> getList(@Valid @RequestBody BaseLessonSearchRequest request) {
        return service.getList(request);
    }

    @PostMapping("/list/group")
    public List<LessonBoardResponseDto> getListByGroup(@Valid @RequestBody GroupLessonSearchRequest request) {
        return service.getListByGroup(request);
    }

    @PostMapping("/list/location")
    public List<LessonBoardResponseDto> getListByLocation(@Valid @RequestBody LocationLessonSearchRequest request) {
        return service.getListByLocation(request);
    }

    @PostMapping("/list/teacher")
    public List<LessonBoardResponseDto> getListByTeacher(@Valid @RequestBody TeacherLessonSearchRequest request) {
        return service.getListByTeacher(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
