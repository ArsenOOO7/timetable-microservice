package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.common.utils.UserUtils;
import com.pnu.system.laboratorycontrol.api.dto.CourseCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.CourseDto;
import com.pnu.system.laboratorycontrol.api.dto.CourseUpdateRequest;
import com.pnu.system.laboratorycontrol.domain.Course;
import com.pnu.system.laboratorycontrol.domain.CourseSearch;
import com.pnu.system.laboratorycontrol.domain.LaboratoryControlSubjectSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(componentModel = "spring", imports = {UserUtils.class, Set.class})
public interface CourseMapper {

    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "authorIds", expression = "java(Set.of(UserUtils.getId()))")
    Course asCourse(CourseCreateRequest request);

    void applyCourseUpdateRequest(@MappingTarget Course course, CourseUpdateRequest request);

    @Mapping(target = "id", source = "course.id")
    CourseDto asCourseDto(Course course, LaboratoryControlSubjectSnapshot subject);

    CourseDto asCourseDto(CourseSearch course);

}
