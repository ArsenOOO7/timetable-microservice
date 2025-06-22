package com.pnu.system.laboratorycontrol.mapper;

import com.pnu.system.common.utils.TimetableUserUtils;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPrivateCommentCreateRequest;
import com.pnu.system.laboratorycontrol.api.dto.comment.AssignmentPrivateCommentDto;
import com.pnu.system.laboratorycontrol.domain.comment.AssignmentPrivateComment;
import com.pnu.system.laboratorycontrol.domain.comment.AssignmentPrivateCommentSearch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = {TimetableUserUtils.class})
public interface AssignmentPrivateCommentMapper {

    @Mapping(target = "authorId", expression = "java(TimetableUserUtils.getId())")
    AssignmentPrivateComment asAssignmentPrivateComment(AssignmentPrivateCommentCreateRequest request);

    List<AssignmentPrivateCommentDto> asPrivateCommentDtos(List<AssignmentPrivateCommentSearch> comments);

}
