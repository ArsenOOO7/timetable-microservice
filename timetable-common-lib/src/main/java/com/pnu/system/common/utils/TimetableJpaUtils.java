package com.pnu.system.common.utils;

import com.pnu.system.common.exception.EntityNotFoundException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.function.Function;

@UtilityClass
public class TimetableJpaUtils {

    public static <T> T nullSafeRetrieve(String id, Function<String, Optional<T>> methodGet, Class<T> entityType) {
        return nullSafeRetrieve(id, methodGet, entityType.getSimpleName());
    }

    public static <T> T nullSafeRetrieve(String id, Function<String, Optional<T>> methodGet, String entityName) {
        if (StringUtils.isBlank(id)) {
            throw new EntityNotFoundException(null, entityName);
        }
        return methodGet.apply(id).orElseThrow(() -> new EntityNotFoundException(id, entityName));
    }
}
