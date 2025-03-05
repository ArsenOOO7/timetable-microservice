package com.pnu.system.common.utils;

import com.pnu.system.common.domain.BaseEntityProvider;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class BeanUtils {

    public static List<String> getIds(Collection<? extends BaseEntityProvider> entities) {
        return entities.stream().map(BaseEntityProvider::getId).collect(Collectors.toList());
    }

    public List<UUID> stringIdsToUuids(Collection<String> ids) {
        return ids.stream().map(UUID::fromString).collect(Collectors.toList());
    }
}
