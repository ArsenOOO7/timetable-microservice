package com.pnu.system.common.utils;

import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

import static com.pnu.system.common.rest.TimetableRestUtils.decode;

public class DateUtils {

    public static ZonedDateTime asZonedDateTime(String value) {
        return ZonedDateTime.parse(decode(UriUtils.decode(value, StandardCharsets.UTF_8)));
    }

}
