package com.pnu.system.group.api.dto;

import com.pnu.system.group.constant.GroupType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupUpdateRequest {

    @NotBlank
    private String id;
    private String parentId;
    private String specialtyId;
    private Integer academicYear;
    @NotNull
    private Integer number;
    @NotNull
    private GroupType type;
    @NotBlank
    private String name;

    private List<String> relatedGroupIds;
    private List<String> groupCategoryIds;

    @NotNull
    private Integer version;

}
