package com.pnu.system.common.domain;

import org.apache.commons.lang3.StringUtils;

public interface BaseEntityProvider {

    String getId();

    default boolean isNew() {
        return StringUtils.isBlank(getId());
    }

}
