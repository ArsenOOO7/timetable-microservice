package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import com.pnu.system.laboratorycontrol.domain.LaboratoryControlSubjectSnapshot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LaboratoryControlSubjectSnapshotMapper {

    LaboratoryControlSubjectSnapshot asLaboratoryControlSubjectSnapshot(SubjectSnapshotDto subjectSnapshotDto);

}
