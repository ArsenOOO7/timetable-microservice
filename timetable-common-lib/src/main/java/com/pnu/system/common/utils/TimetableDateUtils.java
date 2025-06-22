package com.pnu.system.common.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

import static com.pnu.system.common.utils.TimetableRestUtils.decode;

@UtilityClass
public class TimetableDateUtils {

    public static ZonedDateTime asZonedDateTime(String value) {
        return ZonedDateTime.parse(decode(UriUtils.decode(value, StandardCharsets.UTF_8)));
    }

}
