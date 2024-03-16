package com.pnu.system.common.utils;

import com.pnu.system.common.domain.BaseEntityProvider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BeanUtils {

    public static List<String> getIds(Collection<? extends BaseEntityProvider> entities) {
        return entities.stream().map(BaseEntityProvider::getId).collect(Collectors.toList());
    }
}
