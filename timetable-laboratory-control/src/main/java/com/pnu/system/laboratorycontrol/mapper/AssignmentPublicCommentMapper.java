package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.common.utils.TimetableUserUtils;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentCommentDto;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPublicCommentCreateRequest;
import com.pnu.system.laboratorycontrol.domain.comment.AssignmentPublicComment;
import com.pnu.system.laboratorycontrol.domain.comment.AssignmentPublicCommentSearch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = {TimetableUserUtils.class})
public interface AssignmentPublicCommentMapper {

    @Mapping(target = "authorId", expression = "java(TimetableUserUtils.getId())")
    AssignmentPublicComment asAssignmentPrivateComment(AssignmentPublicCommentCreateRequest request);

    List<AssignmentCommentDto> asCommentDtos(List<AssignmentPublicCommentSearch> comments);

}
