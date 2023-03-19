package com.arsen.timetable.service.readonly;

import com.arsen.common.exception.EntityNotFoundException;
import com.arsen.timetable.domain.readonly.ClassroomRead;
import com.arsen.timetable.dto.readonly.ClassroomDto;
import com.arsen.timetable.exception.ClassroomBusyException;
import com.arsen.timetable.repository.ClassroomReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClassroomReadService {

    private final ClassroomReadRepository classroomReadRepository;


    public ClassroomRead readById(long id){
        return classroomReadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with id " + id + " not found!"));
    }

    public void checkBusyClassroom(long classroomId, LocalDate date, short lessonNumber){
        if(classroomReadRepository.isClassroomBusy(classroomId, date, lessonNumber)){
            throw new ClassroomBusyException("Classroom " + classroomId + " is busy at that moment!");
        }
    }

    public void create(ClassroomDto classroomDto){

        if(classroomDto == null){
            throw new NullPointerException("Classroom cannot be null!");
        }

        classroomReadRepository.save(
                new ClassroomRead(classroomDto.getId(), classroomDto.getName(), classroomDto.getAddress())
        );

    }

    public void update(ClassroomDto classroomDto){

        if(classroomDto == null){
            throw new NullPointerException("Classroom cannot be null!");
        }

        ClassroomRead classroomRead = readById(classroomDto.getId());
        classroomRead.setName(classroomDto.getName());
        classroomRead.setAddress(classroomDto.getAddress());

        classroomReadRepository.save(classroomRead);
    }


    public void delete(long id){
        classroomReadRepository.deleteById(id);
    }
    
}
