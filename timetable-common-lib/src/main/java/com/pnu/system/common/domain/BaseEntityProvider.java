package com.pnu.system.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

public interface BaseEntityProvider {

    String getId();

    @JsonIgnore
    default boolean isNew() {
        return StringUtils.isBlank(getId());
    }

}
