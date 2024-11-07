package com.pnu.system.identityaccess.mapper;

import com.pnu.system.identityaccess.api.dto.TeacherProfileUpdateRequest;
import com.pnu.system.identityaccess.domain.TeacherProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherProfileMapper {

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "userId", ignore = true)
    void applyToTeacherProfile(@MappingTarget TeacherProfile profile, TeacherProfileUpdateRequest request);

}
