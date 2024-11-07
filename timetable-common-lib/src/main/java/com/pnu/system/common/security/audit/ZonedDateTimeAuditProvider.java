package com.pnu.system.common.security.audit;

import org.springframework.data.auditing.DateTimeProvider;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;
import java.util.TimeZone;

public class ZonedDateTimeAuditProvider implements DateTimeProvider {

    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(ZonedDateTime.now(TimeZone.getDefault().toZoneId()));
    }
}
