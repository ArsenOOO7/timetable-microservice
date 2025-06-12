package com.pnu.system.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VersionRequest extends UpdateRequest {

    @NotNull
    private Integer version;

}
