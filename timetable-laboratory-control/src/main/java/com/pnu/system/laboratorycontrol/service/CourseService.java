package com.pnu.system.laboratorycontrol.service;

import com.pnu.system.common.dto.UserDto;
import com.pnu.system.common.service.AbstractPersistenceService;
import com.pnu.system.common.utils.JpaUtils;
import com.pnu.system.laboratorycontrol.api.dto.CourseCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.CourseDto;
import com.pnu.system.laboratorycontrol.api.dto.CourseGroupDto;
import com.pnu.system.laboratorycontrol.api.dto.CourseUpdateRequest;
import com.pnu.system.laboratorycontrol.api.validation.CourseValidation;
import com.pnu.system.laboratorycontrol.constant.CourseStatus;
import com.pnu.system.laboratorycontrol.domain.Course;
import com.pnu.system.laboratorycontrol.domain.LaboratoryControlSubjectSnapshot;
import com.pnu.system.laboratorycontrol.mapper.CourseMapper;
import com.pnu.system.laboratorycontrol.mapper.LaboratoryControlGroupSnapshotMapper;
import com.pnu.system.laboratorycontrol.mapper.LaboratoryControlUserSnapshotMapper;
import com.pnu.system.laboratorycontrol.repository.CourseRepository;
import com.pnu.system.laboratorycontrol.repository.LaboratoryControlSubjectSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService extends AbstractPersistenceService<Course> {

    private final CourseMapper mapper;
    private final CourseValidation validation;
    private final CourseRepository repository;
    private final LaboratoryControlUserSnapshotMapper userMapper;
    private final LaboratoryControlGroupSnapshotMapper groupMapper;
    private final LaboratoryControlSubjectSnapshotRepository subjectRepository;

    public CourseDto create(CourseCreateRequest request) {
        Course course = mapper.asCourse(request);
        return populateSubject(super.create(course));
    }

    public CourseDto update(CourseUpdateRequest request) {
        Course course = getOne(request.getId());
        mapper.applyCourseUpdateRequest(course, request);
        return populateSubject(super.update(course));
    }

    public CourseDto getOneById(String id) {
        return populateSubject(getOne(id));
    }

    public void addAuthor(String id, String userId) {
        Course course = getOne(id);
        course.getAuthorIds().add(userId);
        super.update(course);
    }

    public void removeAuthor(String id, String userId) {
        Course course = getOne(id);
        validation.validateBeforeAuthorRemoval(course);
        course.getAuthorIds().remove(userId);
        super.update(course);
    }

    public void archive(String id) {
        Course course = getOne(id);
        validation.validateBeforeArchive(course);
        course.setStatus(CourseStatus.ARCHIVED);
        super.update(course);
    }

    public void makeActive(String id) {
        Course course = getOne(id);
        validation.validateBeforeMakingActive(course);
        course.setStatus(CourseStatus.ACTIVE);
        super.update(course);
    }

    public void addGroup(String id, String groupId) {
        Course course = getOne(id);
        course.getGroupIds().add(groupId);
        super.update(course);
    }

    public void removeGroup(String id, String groupId) {
        Course course = getOne(id);
        course.getGroupIds().remove(groupId);
        super.update(course);
    }

    public List<UserDto> getAuthors(String id) {
        return userMapper.asUserDtos(repository.getAuthors(id));
    }

    public List<CourseGroupDto> getGroups(String id) {
        return groupMapper.asCourseGroupDtos(repository.getGroups(id));
    }

    //TODO 6/11/25: Refactor this sh*t when have time
    private CourseDto populateSubject(Course course) {
        LaboratoryControlSubjectSnapshot subject = JpaUtils.nullSafeRetrieve(course.getSubjectId(), subjectRepository::findById, "Subject");
        return mapper.asCourseDto(course, subject);
    }

    @Override
    protected Class<Course> getEntityType() {
        return Course.class;
    }

    @Override
    protected JpaRepository<Course, String> getRepository() {
        return repository;
    }
}
