package com.arsen.timetable.service.readonly;

import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.common.exception.EntityNullReferenceException;
import com.arsen.timetable.domain.readonly.TeacherRead;
import com.arsen.timetable.dto.readonly.TeacherDto;
import com.arsen.timetable.event.TeacherUpdateEvent;
import com.arsen.timetable.exception.ClassroomBusyException;
import com.arsen.timetable.repository.TeacherReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TeacherReadService {

    private final TeacherReadRepository teacherReadRepository;


    public TeacherRead readById(long id){
        return teacherReadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " not found!"));
    }


    public void checkBusyTeacher(long teacherId, LocalDate date, short lessonNumber){
        if(teacherReadRepository.isTeacherBusy(teacherId, date, lessonNumber)){
            throw new ClassroomBusyException("Teacher " + teacherId + " is busy at that moment!");
        }
    }


    public void synchronize(TeacherUpdateEvent teacherUpdateEvent){
        switch(teacherUpdateEvent.getStatus()){
            case CREATED -> create(teacherUpdateEvent);
            case UPDATED -> update(teacherUpdateEvent);
            case DELETED -> delete(teacherUpdateEvent.getId());
        }
    }

    public void create(TeacherDto teacherDto){

        if(teacherDto == null){
            throw new EntityNullReferenceException("Teacher cannot be null!");
        }

        teacherReadRepository.save(
                new TeacherRead(teacherDto.getId(), teacherDto.getFirstName(),
                        teacherDto.getLastName(), teacherDto.getFatherName(), teacherDto.getMeetingLink())
        );

    }

    public void update(TeacherDto teacherDto){

        if(teacherDto == null){
            throw new EntityNullReferenceException("Teacher cannot be null!");
        }

        TeacherRead teacherRead = readById(teacherDto.getId());
        teacherRead.setFirstName(teacherDto.getFirstName());
        teacherRead.setLastName(teacherDto.getLastName());
        teacherRead.setFatherName(teacherDto.getFatherName());
        teacherRead.setMeetingLink(teacherDto.getMeetingLink());

        teacherReadRepository.save(teacherRead);
    }


    public void delete(long id){
        teacherReadRepository.deleteById(id);
    }

}
