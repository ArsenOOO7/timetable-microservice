package com.pnu.system.common.utils;

import com.pnu.system.common.dto.UsernameProvider;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class TimetableStringUtils {

    public static String buildFullName(String firstName, String lastName) {
        return StringUtils.normalizeSpace(String.join(" ",
                StringUtils.defaultIfBlank(firstName, StringUtils.EMPTY),
                StringUtils.defaultIfBlank(lastName, StringUtils.EMPTY)
        ));
    }

    public static String buildFullName(UsernameProvider provider) {
        return buildFullName(provider.getFirstName(), provider.getLastName());
    }
}
