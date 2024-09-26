package com.pnu.system.group.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupCategoryCreateRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String shortName;

}
