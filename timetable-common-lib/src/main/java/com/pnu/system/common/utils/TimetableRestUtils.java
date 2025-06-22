package com.pnu.system.common.utils;

import org.springframework.web.util.UriBuilder;

import java.util.Map;
import java.util.Objects;

public class TimetableRestUtils {

    public static void addQueryParameters(UriBuilder builder, Map<String, Object> queryParameters) {
        queryParameters.forEach((key, value) -> {
            Objects.requireNonNull(value);
            builder.queryParam(key, encode(value.toString()));
        });
    }

    public static String encode(String url) {
        return url.replace("+", "%2B");
    }

    public static String decode(String url) {
        return url.replace("%2B", "+");
    }
}
